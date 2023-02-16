package com.keyholesoftware.lambda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyholesoftware.lambda.model.TickerSentiment;
import com.keyholesoftware.lambda.model.TickerSymbol;
import com.keyholesoftware.lambda.model.TradingDataEntity;
import com.keyholesoftware.lambda.repositories.SymbolRepository;

@Service
public class SymbolService{
	private TickerSymbol SymbObj = new TickerSymbol();
	private TickerSentiment temp = new TickerSentiment();
	
	@Autowired
	private SymbolRepository symbolRepo;
	
	public void saveAll(TradingDataEntity t) {
		 SymbObj.setTicker(t.getTicker());
		 SymbObj.setId(t.getId());

		 temp.setSentiment(t.getSentiment());
		 temp.setID(t.getId());
		 temp.setSentiment_score(t.getSentiment_score());

		 symbolRepo.save(SymbObj);
//		 return SymbObj;
	}
	
	public List<TickerSymbol> findIdAndName(){
		List<TickerSymbol> list = symbolRepo.findAll();
		return list;
	}

	public List<TickerSymbol> getAll() {
		// TODO Auto-generated method stub
		return symbolRepo.findAll();
	}

	public Optional<TickerSymbol> getById(int id) {
		// TODO Auto-generated method stub
		return symbolRepo.findById(id);
	}


}
