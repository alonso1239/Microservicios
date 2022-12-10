package com.banco.virtual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.banco.virtual.credit.Credit;
import com.banco.virtual.repository.CreditRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class ApiCreditApplication implements CommandLineRunner{
	
	@Autowired
	private CreditRepository repository;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ApiCreditApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("credit").subscribe();
		
		Flux.just(
				new Credit("abcd001",(long)1,2000.00,1520.60,"Personal"),
				new Credit("abcd002",(long)2,1000.00,520.00,"Personal"),
				new Credit("abcd003",(long)3,5000.00,2520.00,"Empresarial")
				).flatMap(p->{
					return repository.save(p);
				}).subscribe(p-> log.info("Insercion exitosa"+p.toString()));
	}
}
