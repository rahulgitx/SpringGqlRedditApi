package com.keyholesoftware.lambda.controllers;



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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.keyholesoftware.lambda.model.TradingDataEntity;



//import net.javaguides.springbootbackend.exception.ResourceNotFroundException;

@RestController
@RequestMapping("/api/")
public class ApiController {
	  private final RestTemplate restTemplate;
	  private final HttpHeaders headers;
	  
	  TradingDataEntity[] tasks;
	  
	  public ApiController() {
		    this.restTemplate = new RestTemplate();
		    headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
//		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	  }

	private String generateUrl(String path) {
		return "https://1c6o01ldm2.execute-api.ap-south-1.amazonaws.com/Stage/tickers" + path;	
	}

	@GetMapping("tickers")
	public ResponseEntity allTasks() {
		  HttpEntity<TradingDataEntity> request = new HttpEntity<>(headers);
		  String url = generateUrl("");
		
		  ResponseEntity<TradingDataEntity[]> result = restTemplate.exchange(url, HttpMethod.GET, request, TradingDataEntity[].class);
		  tasks = result.getBody();
//
		  assert tasks != null;

		  return ResponseEntity.ok().body(tasks);
	}
	
	@GetMapping("tickers/{tickerName}")
	public TradingDataEntity getTicker(@PathVariable String tickerName) {

		for(TradingDataEntity t: tasks) {
			if(t.getTicker().equals(tickerName)) {
				return t;
			}
		}
		return null;
	}
	
	@GetMapping("tickers/date/{date}")
	public ResponseEntity getTickerDate(@PathVariable String date) { 
		HttpEntity<TradingDataEntity> request = new HttpEntity<>(headers);
		String s = "?date="+date;String url = generateUrl(s);
		System.out.println(url);
		ResponseEntity<TradingDataEntity[]> result = restTemplate.exchange(url, HttpMethod.GET, request, TradingDataEntity[].class);
		TradingDataEntity[] dateTicker = result.getBody();
		assert tasks != null;
		return ResponseEntity.ok().body(dateTicker);
	}

	@PutMapping("tickers/{tickerName}")
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
	
	@PostMapping("tickers")
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
	
	
	

}