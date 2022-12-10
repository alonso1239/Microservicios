package com.banco.virtual.credit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection="credit")
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
	@Id
	private String id;
	
	private Long idCustomer;
	
	private Double amount;
	
	private Double consumed;
	
	private String type;
}
