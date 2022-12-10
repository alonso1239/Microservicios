package com.banco.virtual.service;

import java.util.List;

import com.banco.virtual.account.Account;


public interface AccountService {

	public List<Account> findAll();
	
	public Account findById(Long id);
	
	public Account findByAccount(String account);
	
	public List<Account> findByType (String type);
	
	public List<Account> findByIdCustomer (Long id);
	
	public Account updateByIdCustomer(Account account,Long id_customer);
	
	public Account save(Account account);
		
	public void delete (Long id);
}
