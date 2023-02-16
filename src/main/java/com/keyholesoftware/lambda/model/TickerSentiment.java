package com.keyholesoftware.lambda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
public class TickerSentiment {
	
	@Id
	public int id;
	
	public String sentiment;

//	public TickerSymbol getTickerSymbol() {
//		return tickerSymbol;
//	}
//	public void setTickerSymbol(TickerSymbol tickerSymbol) {
//		this.tickerSymbol = tickerSymbol;
//	}
	public double sentiment_score;
	
//	public String ticker;
	
//	@OneToOne
//	@MapsId
//	@JoinColumn(name="tickerSymbol_id")
//	public TickerSymbol tickerSymbol;
	
	
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
//	public String getTicker(){
//		return ticker;
//	}
//	public void setTicker(String ticker) {
//		this.ticker = ticker;
//	} 
	public int getID() {
		return id;
	}
	public void setID(int iD) {
		id = iD;
	}
	public TickerSentiment() {
		super();
	}
//	public TickerSentiment(int id, String sentiment, double sentiment_score, String ticker) {
//		super();
//		this.id = id;
//		this.sentiment = sentiment;
//		this.sentiment_score = sentiment_score;
//		this.ticker = ticker;
//	}
	  
}
