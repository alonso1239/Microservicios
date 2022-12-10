package com.banco.virtual.service;

import java.util.List;
import java.util.Optional;

import com.banco.virtual.customer.Customer;

public interface CustomerService {

	public List<Customer> findAll();
	
	public Customer findById(Long id);
	
	public List<Customer> findByType (String type);
	
	public Customer updateById(Customer customer,Long id);
	
	public Customer save(Customer customer);
		
	public void delete (Long id);
	
	public Customer findByDni(String dni);
}
