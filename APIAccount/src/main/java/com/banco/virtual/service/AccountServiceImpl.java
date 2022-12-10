package com.banco.virtual.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banco.virtual.account.Account;

import com.banco.virtual.repository.AccountRepository;



@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository repository;
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Account findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Account> findByType(String type) {
		// TODO Auto-generated method stub
		return repository.findByType(type);
	}



	@Override
	public Account save(Account account) {
		/*String uri = "http://localhost:12001/api-account/listid?id="+id;
		RestTemplate restTemplate = new RestTemplate();

		String account= restTemplate.getForObject(uri,String.class);
		JSONObject jsonObject = new JSONObject(account);
		
		List<?> customers = repository.findByIdCustomer(id);
		
		for(Object str : customers)
		{
		    //imprimimos el objeto pivote
			JSONObject jsonObject2 = new JSONObject(str);
			
			 System.out.println("OBJ "+ jsonObject2.getString("type"));
		}
		 /*System.out.println("Tipo "+ jsonObject.getString("type"));
		 
		 if( jsonObject.getString("type").equals("Personal"))
		 {
			 return repository.save(account);
		 }
		 
		 else
		 {
			 
			 return null;
		 }


		*/
	
	return repository.save(account);
	}
	
	@Override
	public List<Account> findByIdCustomer(Long id) {
		// TODO Auto-generated method stub
		return repository.findByIdCustomer(id);
		// @formatter:on

	}

	@Override
	public void delete(Long id) {
	 repository.deleteById(id);;
		
	}

	@Override
	public Account updateByIdCustomer(Account account, Long id) {

		
		Account listAccountActual=repository.findById(id).orElse(null);
	
		if(listAccountActual == null)
		{
			System.out.println("Error : El account con ID:"+ id + " no se puede editar, no existe en la base de datos");
		}
		else
		//account serial = new account();
		{
		listAccountActual.setIdCustomer(account.getIdCustomer());
		listAccountActual.setType(account.getType());
		listAccountActual.setMaintenance(account.getMaintenance());
		listAccountActual.setMovement(account.getMovement());
		listAccountActual.setAmount(account.getAmount());

		
		}
		
		
		return repository.save(listAccountActual);
		
	}

	@Override
	public Account findByAccount(String account) {
		// TODO Auto-generated method stub
		return repository.findByAccount(account);
	}



}
