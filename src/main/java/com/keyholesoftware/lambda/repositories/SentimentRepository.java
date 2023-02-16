package com.keyholesoftware.lambda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyholesoftware.lambda.model.TickerSentiment;

public interface SentimentRepository extends JpaRepository<TickerSentiment, Integer>{
}
