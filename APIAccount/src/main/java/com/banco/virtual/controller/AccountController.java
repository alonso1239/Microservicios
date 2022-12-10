package com.banco.virtual.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.banco.virtual.account.Account;
import com.banco.virtual.service.AccountService;



@RestController
@RequestMapping("/api-account")
public class AccountController {

	@Autowired
	private AccountService service;
	
	/*@GetMapping(value="/callcustomer")
	public  ResponseEntity<?> getList()
	{
		String uri = "http://localhost:12001/api-Account/list";
		RestTemplate restTemplate = new RestTemplate();
			
		
		
		List<Object> Account= restTemplate.getForObject(uri,List.class);
		//usamos un mapa para capturar el error 
		//map: interfaz  hashmap: implementacion

		return new ResponseEntity<>(Account,HttpStatus.OK); 
	}*/
	
	@GetMapping(path="/list")
	public  List<Account> listar()
	{
		return service.findAll();
	}
	
	@GetMapping(path="/listid")
	public List<Account> listarId(@RequestParam ("id") Long id)
	{
		return service.findByIdCustomer(id);
	}
	
	@GetMapping(path="/naccount")
	public Account listarId(@RequestParam ("account") String account)
	{
		return service.findByAccount(account);
	}
	
	
	
	@PostMapping("/save")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account, BindingResult result)
	{
		Account saveAccount=null;
		Map<String,Object>response= new HashMap<>();
		if(result.hasErrors())//si encuentra error
		{
			//creamos un list de tipo string q contiene los mensajes de error
			
			List<String> errors= result.getFieldErrors() //por cada elemento de fieldError lo convertimos a string
					.stream().map(err ->"El campo '"+ err.getField()+ "'" +err.getDefaultMessage()) //como es 1 sola linea no necesita el return
					.collect(Collectors.toList());
			
					
			response.put("Errors",errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			saveAccount = this.service.save(account);
		}
		catch(DataAccessException e)
		{
			response.put("Mensaje","Error al ingresar informacion en la base de datos" );
			response.put("Error",e.getMessage()+" :" +e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje","La cuenta fue registrado con exito" );
		response.put("Account: ",saveAccount);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCustomer( @RequestBody Account Account, @RequestParam("id") Long id)
	{
		Map<String, Object> response = new HashMap<>();
		Account updateCustomer =null;

		try {
		updateCustomer = this.service.updateByIdCustomer(Account, id);

		}
		catch(DataAccessException e)
		{
			response.put("mensaje",
					"Error : El Account con ID:" + id + " no se puede editar, no existe en la base de datos");
			
			response.put("Error",e.getMessage()+" :" +e.getMostSpecificCause().getMessage());
			// retornamos el mapa
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		

			response.put("Mensaje", "El Account fue actualizado con exito");
			response.put("Account", updateCustomer);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
		
	}
	
	@DeleteMapping ("/delete")
	public ResponseEntity<?> deleteStudent(@RequestParam("id") Long id)
	{
		
		//el map guarda el contenido q se envia en el reponse entity
		Map<String,Object>response= new HashMap<>();
		try
		{
			service.delete(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje",
					"Error : El Account con ID:" + id + " no se puede eliminar, no existe en la base de datos");
			response.put("error",e.getMessage()+" :" +e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El Account fue eliminado con exito" );
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	
	
	
	
	/*
	 * 	
	@GetMapping(value="/callcustomer")
	public  ResponseEntity<?> getList()
	{
		String uri = "http://localhost:12001/api-Account/list";
		RestTemplate restTemplate = new RestTemplate();
		//String result = restTemplate.getForObject(uri, String.class);
			
		
		
		Object[] Account= restTemplate.getForObject(uri, Object[].class);
		//usamos un mapa para capturar el error 
		//map: interfaz  hashmap: implementacion

		return new ResponseEntity<>(Account,HttpStatus.OK); 
	}
	 * */
}
