package com.banco.virtual.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banco.virtual.credit.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
	
	//con listas es flux y con 1 elemento es mono
	Flux <Credit> findByType(String type);
	

}
