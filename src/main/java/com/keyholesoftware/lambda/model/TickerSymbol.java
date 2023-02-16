package com.keyholesoftware.lambda.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="TickerSymbols")
public class TickerSymbol {
	  @Id
	  private Integer ID;
	  
	  public String ticker;
	  
	  
//	  @OneToOne(cascade=CascadeType.ALL)
//	  @JoinColumn(name="ID", referencedColumnName = "id")
//	  public TickerSentiment tickerSentiment;
	  
	  

//	public TickerSentiment getTickerSentiment() {
//		return tickerSentiment;
//	}
//
//	public void setTickerSentiment(TickerSentiment tickerSentiment) {
//		this.tickerSentiment = tickerSentiment;
//	}

	public Integer getId() {
		return ID;
	}

	public void setId(Integer id) {
		this.ID = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public TickerSymbol() {
		super();
		ID = null;
		this.ticker = null;
		//this.tickerSentiment = null;
	}
	 
}
