package com.keyholesoftware.lambda.rest;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class TradingDataEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  
  public int no_of_comments;
  public String sentiment;
  public double sentiment_score;
  public String ticker;
  public Integer getId() {
	  return id;
  }
  public void setId(Integer id) {
	  this.id = id;
  }
  public int getNo_of_comments() {
	  return no_of_comments;
  }
  public void setNo_of_comments(int no_of_comments) {
	  this.no_of_comments = no_of_comments;
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