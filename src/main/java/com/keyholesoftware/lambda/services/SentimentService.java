package com.keyholesoftware.lambda.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyholesoftware.lambda.model.TickerSentiment;
import com.keyholesoftware.lambda.repositories.SentimentRepository;


@Service
public class SentimentService {
	  
	  @Autowired
	  private SentimentRepository sentimentRepo;

	public Optional<TickerSentiment> getById(int id) {
		// TODO Auto-generated method stub
		return sentimentRepo.findById(id);
	}

}
