package com.banco.virtual.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.banco.virtual.credit.Credit;
import com.banco.virtual.service.CreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api-credit")
public class CreditController {
	
	@Autowired
	private CreditService service;

	@GetMapping("/listar")
	public Flux<Credit> findAll() {
		
		return service.findAll();
	}
	
	@GetMapping("/listid")
	public Flux<Credit> findId(@RequestParam ("type") String type) {
		
		return service.findByType(type);
	}
	
	
	@PostMapping("/save")
	public Mono<ResponseEntity<Map<String, Object>>> save(@RequestBody Credit credit) {
		Map<String, Object> result = new HashMap<String, Object>();

		return service.save(credit).map(p -> {
			result.put("Credit", credit);

			result.put("Mensaje", "Credito guardado correctamente");
			result.put("status", true);
			return ResponseEntity.created(URI.create("/empleado/savings".concat(p.getId())))
					.contentType(MediaType.APPLICATION_JSON).body(result);
		}).onErrorResume(err -> {
			return Mono.just(err).cast(WebExchangeBindException.class).flatMap(e -> Mono.just(e.getFieldErrors()))
					.flatMapMany(errs -> Flux.fromIterable(errs))
					.map(fieldError -> "Campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
					.collectList().flatMap(list -> {
						result.put("Errors", list);
						result.put("status", HttpStatus.BAD_REQUEST);
						return Mono.just(ResponseEntity.badRequest().body(result));

					});
		});
	}
	
}
