package com.banco.virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.virtual.account.Account;


public interface AccountRepository  extends JpaRepository<Account, Long>{
	
	List<Account> findByType (String type);
	
	List<Account> findByIdCustomer(Long id);
	
	Account findByAccount(String account);

}
