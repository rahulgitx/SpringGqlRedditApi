package com.keyholesoftware.lambda.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keyholesoftware.lambda.model.TradingDataEntity;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface TradeRepository extends JpaRepository<TradingDataEntity, Integer> {

}
