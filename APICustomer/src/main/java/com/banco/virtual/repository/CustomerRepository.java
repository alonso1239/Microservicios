package com.banco.virtual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.virtual.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	List<Customer> findByType (String type);
	
	Customer findByDni (String dni);
}
