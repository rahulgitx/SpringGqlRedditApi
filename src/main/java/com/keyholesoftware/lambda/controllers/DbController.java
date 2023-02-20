package com.keyholesoftware.lambda.controllers;


import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.keyholesoftware.lambda.model.SymbolAndSentiment;
import com.keyholesoftware.lambda.model.TickerSentiment;
import com.keyholesoftware.lambda.model.TickerSymbol;
import com.keyholesoftware.lambda.model.TradingDataEntity;
import com.keyholesoftware.lambda.repositories.SentimentRepository;
import com.keyholesoftware.lambda.repositories.SymbolRepository;
import com.keyholesoftware.lambda.repositories.TradeRepository;
import com.keyholesoftware.lambda.services.SentimentService;
//import com.keyholesoftware.lambda.services.ServiceSymbols;
//import com.keyholesoftware.lambda.services.ServiceTrade;
import com.keyholesoftware.lambda.services.SymbolService;
import com.keyholesoftware.lambda.services.TradingDataService;


@RestController
@RequestMapping("/db/")
public class DbController {
	// ***********************DATABASE***************************
	  private final RestTemplate restTemplate;
	  private final HttpHeaders headers;
	  
	  @Autowired	
	  TradingDataService tradeServ;
	  
	  @Autowired
	  SymbolService symbServ;
	  
	  @Autowired
	  SentimentService sentServ;
	  
	  TradingDataEntity[] tasks;
	  
	  public DbController() {
		    this.restTemplate = new RestTemplate();
		    headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	  }
	  
		private String generateUrl(String path) {
			return "https://1c6o01ldm2.execute-api.ap-south-1.amazonaws.com/Stage/tickers" + path;	
		}
	
		@GetMapping("tickers")
		public @ResponseBody Iterable<TradingDataEntity> getAllUsers() {
			
//			tradeRepo.deleteAll();
			
			 HttpEntity<TradingDataEntity> request = new HttpEntity<>(headers);
			 String url = generateUrl("");
			
			 ResponseEntity<TradingDataEntity[]> result = restTemplate.exchange(url, HttpMethod.GET, request, TradingDataEntity[].class);
			 tasks = result.getBody();

			 for(TradingDataEntity t:tasks) {
				 
				 tradeServ.saveAll(t);    // saving in TradingDataEntity table

				 symbServ.saveAll(t);  // saving in ticker symbol as well as ticker sentiment
				 
				 sentServ.saveAll(t);
			 }
			
			 return tradeServ.getAllProperties();
		}
		
		@GetMapping("tickers/id/{id}")
		public Optional<TradingDataEntity> getData(@PathVariable int id){
//			TradingDataService tradeServ = new TradingDataService();
			return tradeServ.getEntryById(id);
		}
		
		
		// insert a new entry
		@PostMapping("/db/tickers")
		public ResponseEntity<Void> addTradingData(@RequestBody TradingDataEntity[] data){	
//			TradingDataService tradeServ = new TradingDataService();
			for(TradingDataEntity t:data) {
				tradeServ.saveEntry(t);
			}
			
			ResponseEntity<Void> re = new ResponseEntity<>(HttpStatus.CREATED);
			return re;
		}
		
		
	    @PutMapping("tickers/update/{id}")
	    public ResponseEntity<Void> updateData(@PathVariable int id, @RequestBody TradingDataEntity newData){
	    	
//	    	TradingDataService tradeServ = new TradingDataService();
	    	tradeServ.copyAll(id, newData);

	        ResponseEntity<Void> re = new ResponseEntity<>(HttpStatus.CREATED);
			return re;
	    }
	    
//	    ******************* Foreign Key ***********************
	    
	    @GetMapping("/tickers/ticker_symbol")
		public @ResponseBody List<TickerSymbol> getAllTickerSymbol() {
//	    	SymbolService symbServ = new SymbolService();
	    	return symbServ.getAll();
	    }
	    
		@GetMapping("tickers/ticker_sentiment/{id}")
		public SymbolAndSentiment getSentiment(@PathVariable int id){
//			SentimentService sentServ = new SentimentService();
//			System.out.println("trying");
			Optional<TickerSentiment> sentObj = sentServ.getById(id);
//			System.out.println("sentiment found");
			Optional<TickerSymbol> symbObj = symbServ.getById(id);
			SymbolAndSentiment combined = new SymbolAndSentiment();
			combined.setIdAndName(symbObj);
			combined.setSentNameAndScore(sentObj);
			return combined;
		}

}

