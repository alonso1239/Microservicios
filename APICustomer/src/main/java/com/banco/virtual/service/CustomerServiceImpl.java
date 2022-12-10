package com.banco.virtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.banco.virtual.customer.Customer;
import com.banco.virtual.repository.CustomerRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired 
	CustomerRepository repository;
	
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Customer findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}
	
	@Override
	public Customer findByDni(String dni) {
		// TODO Auto-generated method stub
		return repository.findByDni(dni);
	}

	@Override
	public Customer updateById(Customer customer,Long id) {

	
		Customer listcustomerActual=repository.findById(id).orElse(null);
	
		if(listcustomerActual == null)
		{
			System.out.println("Error : El customer con ID:"+ id + " no se puede editar, no existe en la base de datos");
		}
		else
		//customer serial = new customer();
		{
		listcustomerActual.setFirstName(customer.getFirstName());
		listcustomerActual.setLastName(customer.getLastName());
		listcustomerActual.setDni(customer.getDni());
		listcustomerActual.setEmail(customer.getEmail());
		listcustomerActual.setDepartment(customer.getDepartment());
		listcustomerActual.setProvince(customer.getProvince());
		listcustomerActual.setAddress(customer.getAddress());
		
		listcustomerActual.setCellphone(customer.getCellphone());
		listcustomerActual.setType(customer.getType());
		listcustomerActual.setFechaCreacion(customer.getFechaCreacion());
		
		}
		
		
		return repository.save(listcustomerActual);
	
	}

	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		customer.setFechaCreacion(LocalDate.now());
		return repository.save(customer);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<Customer> findByType(String type) {
		// TODO Auto-generated method stub
		return repository.findByType(type);
	}
	
	

}
