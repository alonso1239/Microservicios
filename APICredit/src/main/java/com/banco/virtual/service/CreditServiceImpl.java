package com.banco.virtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.virtual.credit.Credit;
import com.banco.virtual.repository.CreditRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl  implements CreditService{

	@Autowired
	private CreditRepository repository;
	
	@Override
	public Flux<Credit> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}



	@Override
	public Mono<Credit> save(Credit credit) {
		// TODO Auto-generated method stub
		return repository.save(credit);
	}

	@Override
	public Mono<Void> delete(Credit credit) {
		// TODO Auto-generated method stub
		return repository.delete(credit);
	}



	@Override
	public Flux<Credit> findByType(String type) {
		// TODO Auto-generated method stub
		return repository.findByType(type);
	}
	
	

}
