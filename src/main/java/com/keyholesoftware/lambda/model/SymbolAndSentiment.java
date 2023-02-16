package com.keyholesoftware.lambda.model;

import java.util.Optional;

public class SymbolAndSentiment {
	private int id;
	private String sentiment;
	private double sentiment_score;
	private String ticker;
	
	public void setIdAndName(Optional<TickerSymbol> symb) {
		TickerSymbol t = symb.get();
		this.id = t.getId();
		this.ticker = t.getTicker();
	}
	public void setSentNameAndScore(Optional<TickerSentiment> sent) {
		TickerSentiment t = sent.get();
		this.sentiment = t.getSentiment();
		this.sentiment_score = t.getSentiment_score();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public double getSentiment_score() {
		return sentiment_score;
	}
	public void setSentiment_score(double sentiment_score) {
		this.sentiment_score = sentiment_score;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	
}
