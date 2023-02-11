package com.keyholesoftware.lambda.rest;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



//import net.javaguides.springbootbackend.exception.ResourceNotFroundException;

@RestController
public class Controller {
	  private final RestTemplate restTemplate;
	  private final HttpHeaders headers;
	  
	  @Autowired
	  private TradeRepository tradeRepo;
	  
	  TradingDataEntity[] tasks;
	  
	  public Controller() {
		    this.restTemplate = new RestTemplate();
		    headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
//		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	  }

	private String generateUrl(String path) {
		return "https://tradestie.com/api/v1/apps/reddit" + path;	
	}

	@GetMapping("/api/tickers")
	public ResponseEntity allTasks() {
		  HttpEntity<TradingDataEntity> request = new HttpEntity<>(headers);
		  String url = generateUrl("");
		
		  ResponseEntity<TradingDataEntity[]> result = restTemplate.exchange(url, HttpMethod.GET, request, TradingDataEntity[].class);
		  tasks = result.getBody();
//
		  assert tasks != null;

		  return ResponseEntity.ok().body(tasks);
	}
	
	@GetMapping("/api/tickers/{tickerName}")
	public TradingDataEntity getTicker(@PathVariable String tickerName) {

		for(TradingDataEntity t: tasks) {
			if(t.getTicker().equals(tickerName)) {
				return t;
			}
		}
		return null;
	}
	
	@GetMapping("/api/tickers/date/{date}")
	public ResponseEntity getTickerDate(@PathVariable String date) { 
		HttpEntity<TradingDataEntity> request = new HttpEntity<>(headers);
		String s = "?date="+date;String url = generateUrl(s);
		System.out.println(url);
		ResponseEntity<TradingDataEntity[]> result = restTemplate.exchange(url, HttpMethod.GET, request, TradingDataEntity[].class);
		TradingDataEntity[] dateTicker = result.getBody();
		assert tasks != null;
		return ResponseEntity.ok().body(dateTicker);
	}

	@PutMapping("/api/tickers/{tickerName}")
	public TradingDataEntity updateTicker(@PathVariable String tickerName ,@RequestBody TradingDataEntity trade) {
		List<TradingDataEntity> tickerData = new ArrayList<>();
		for(TradingDataEntity t: tasks) {
			if(t.getTicker().equals(tickerName)) {
				t = trade;return t;
				}
			}
		assert tasks != null;
		return null;
	}
	
	@PostMapping("/api/tickers")
	public List<TradingDataEntity> addTicker(@RequestBody TradingDataEntity trade) {
		List<TradingDataEntity> n = new ArrayList<>();
		for(int i=0 ; i<tasks.length ; i++) {
			n.add(tasks[i]);
			}
		System.out.println(n);
		n.add(trade);
		assert tasks != null;
		return n;
	}
	
	
	// ***********************DATABASE***************************
	
	@GetMapping("/db/tickers")
	public @ResponseBody Iterable<TradingDataEntity> getAllUsers() {
		System.out.println("Trying to reach db");
		System.out.println("SAving data from api into db");
		
//		tradeRepo.deleteAll();
		
		 HttpEntity<TradingDataEntity> request = new HttpEntity<>(headers);
		 String url = generateUrl("");
		
		 ResponseEntity<TradingDataEntity[]> result = restTemplate.exchange(url, HttpMethod.GET, request, TradingDataEntity[].class);
		 tasks = result.getBody();
		 
		 for(TradingDataEntity t:tasks) {
			 tradeRepo.save(t);
		 }
		

		return tradeRepo.findAll();

	}
	
	@GetMapping("/db/tickers/id/{id}")
	public Optional<TradingDataEntity> getData(@PathVariable int id){
		return tradeRepo.findById(id);
	}
	
	
	// insert a new entry
	@PostMapping("/db/tickers")
	public ResponseEntity<Void> addTradingData(@RequestBody TradingDataEntity[] data){
		System.out.println("Trying to add: ");
		System.out.println(data);
		
		
		for(TradingDataEntity t:data) {
			tradeRepo.save(t);
		}
//		tradeRepo.save(data);
		ResponseEntity<Void> re = new ResponseEntity<>(HttpStatus.CREATED);
		return re;
	}
	
	
    @PutMapping("/db/tickers/update/{id}")
    public ResponseEntity<TradingDataEntity> updateData(@PathVariable int id, @RequestBody TradingDataEntity newData){
        Optional<TradingDataEntity> Tdata= tradeRepo.findById(id);
        TradingDataEntity UpdatedData = Tdata.get();
        UpdatedData.setId(newData.getId());
        UpdatedData.setNo_of_comments(newData.getNo_of_comments());
        UpdatedData.setSentiment(newData.getSentiment());
        UpdatedData.setSentiment_score(newData.getSentiment_score());
        UpdatedData.setTicker(newData.getTicker());

        tradeRepo.save(UpdatedData);

        return ResponseEntity.ok(UpdatedData);
    }

}