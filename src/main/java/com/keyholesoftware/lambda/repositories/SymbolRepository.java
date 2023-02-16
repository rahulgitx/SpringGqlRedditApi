package com.keyholesoftware.lambda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyholesoftware.lambda.model.TickerSymbol;

public interface SymbolRepository extends JpaRepository<TickerSymbol, Integer>{

}
