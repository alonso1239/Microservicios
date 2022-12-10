package com.banco.virtual;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping

public class OperationController {
	

	
	@PutMapping("/deposito")
	public ResponseEntity<?> deposito(@RequestParam ("account") String account , @RequestParam ("amount") Double amount)
	{
		//llamamos al servicio rest por medio del gateway
		String uri = "http://localhost:11100/account-rest/naccount?account="+account;
		Double montoFinal;
		Long id;
		
		//definimos un rest template y capturamos la respuesta de uri
		RestTemplate restTemplate = new RestTemplate();
		String cuentaActual= restTemplate.getForObject(uri,String.class);
		
		//convertimos el string a json para poder acceder a sus atributos
		JSONObject jsonObject = new JSONObject(cuentaActual);
		
		//calculamos el nuevo monto
		montoFinal = jsonObject.getDouble("amount")+ amount;
		id=jsonObject.getLong("idCustomer");
		
		//llamamos al servicio rest de update por medio del gateway
		String uriUpdate = "http://localhost:11100/account-rest/update?id="+id;

		
		//ACTUALIZAR DATA	
		/*Armamos un objecto con las acaracteristicas de account y le pasamos la misma info que devolvio
		 * la variable jsonObject pero con el monto cambiado
		*/
		
	
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("idCustomer", jsonObject.getInt("idCustomer"));
		
		result.put("type", jsonObject.getString("type"));
		
		result.put("maintenance", jsonObject.getDouble("maintenance"));
	
		result.put("movement", jsonObject.getInt("movement"));

		result.put("amount", montoFinal);
	

		//ejecuta el update por medio de la uri y el result(object)
		restTemplate.put(uriUpdate, result);


		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	
	}
	
 
	
	
	@PutMapping("/retiro")
	public ResponseEntity<?> retiro(@RequestParam ("account") String account , @RequestParam ("amount") Double amount)
	{
		
		//llamamos al servicio rest por medio del gateway
		String uri = "http://localhost:11100/account-rest/naccount?account="+account;
		
		
		Double montoFinal;
		Long id;
		
		//definimos un rest template y capturamos la respuesta de uri
		RestTemplate restTemplate = new RestTemplate();
		String cuentaActual= restTemplate.getForObject(uri,String.class);
		
		//convertimos el string a json para poder acceder a sus atributos
		JSONObject jsonObject = new JSONObject(cuentaActual);
		
		//calculamos el nuevo monto
		montoFinal = jsonObject.getDouble("amount") - amount;
		
		id=jsonObject.getLong("idCustomer");
		
		
		//llamamos al servicio rest de update por medio del gateway
		String uriUpdate = "http://localhost:11100/account-rest/update?id="+id;
		
		
		//ACTUALIZAR DATA	
		/*Armamos un objecto con las acaracteristicas de account y le pasamos la misma info que devolvio
		 * la variable jsonObject pero con el monto cambiado
		*/
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(amount <= jsonObject.getDouble("amount"))
		{
		result.put("idCustomer", jsonObject.getInt("idCustomer"));
		
		result.put("type", jsonObject.getString("type"));
		
		result.put("maintenance", jsonObject.getDouble("maintenance"));
	
		result.put("movement", jsonObject.getInt("movement"));

		result.put("amount", montoFinal);
		
		//ejecuta el update por medio de la uri y el result(object)
		restTemplate.put(uriUpdate, result);
		
		}
	
		else
		{
			result.put("amount", "No cuenta con saldo suficiente");
		}
		


		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * String uri = "http://localhost:12001/api-account/listid?id="+id;
		System.out.println("Monto actual "+ jsonObject.getDouble("amount"));
		
		System.out.println("Monto a depositar "+ amount);
		
		System.out.println("Id customer "+ jsonObject.getInt("idCustomer"));
		
		System.out.println("Monto Final "+montoFinal);
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
	
	


}
