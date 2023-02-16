package com.keyholesoftware.lambda.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyholesoftware.lambda.model.TradingDataEntity;
import com.keyholesoftware.lambda.repositories.TradeRepository;

@Service
public class TradingDataService {
	
	  @Autowired
	  private TradeRepository tradeRepo;
	//TradeRepository tradeRepo = new TradeRepository();

	public void saveAll(TradingDataEntity t) {
		// TODO Auto-generated method stub
		tradeRepo.save(t);
	}

	public Iterable<TradingDataEntity> getAllProperties() {
		// TODO Auto-generated method stub
		return tradeRepo.findAll();
	}

	public Optional<TradingDataEntity> getEntryById(int id) {
		// TODO Auto-generated method stub
		return tradeRepo.findById(id);
	}

	public void saveEntry(TradingDataEntity t) {
		// TODO Auto-generated method stub
		tradeRepo.save(t);
		
	}
	
	public TradingDataEntity copyAll(int id, TradingDataEntity newData) {
    	Optional<TradingDataEntity> Tdata= tradeRepo.findById(id);

        TradingDataEntity UpdatedData = Tdata.get();
        UpdatedData.setId(newData.getId());
        UpdatedData.setNo_of_comments(newData.getNo_of_comments());
        UpdatedData.setSentiment(newData.getSentiment());
        UpdatedData.setSentiment_score(newData.getSentiment_score());
        UpdatedData.setTicker(newData.getTicker());
        
        tradeRepo.save(UpdatedData);
        
        return UpdatedData;
	}
	  
	  
}
