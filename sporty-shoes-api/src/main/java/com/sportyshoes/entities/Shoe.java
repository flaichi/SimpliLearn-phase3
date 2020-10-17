package com.sportyshoes.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sportyshoes.entities.enumerations.BrandEnum;
import com.sportyshoes.entities.enumerations.ColorEnum;
import com.sportyshoes.entities.enumerations.GenderEnum;
import com.sportyshoes.entities.enumerations.SizeEnum;
import com.sportyshoes.entities.enumerations.SportEnum;
import com.sportyshoes.entities.enumerations.TerrainEnum;
import com.sportyshoes.entities.enumerations.TypeEnum;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shoes")
@Data @NoArgsConstructor
@Getter @Setter  
public class Shoe implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	//this annotation specificies that the value of the enum should be persisted as String
	@Enumerated(EnumType.STRING)
	private ColorEnum color;
	
	@Enumerated(EnumType.STRING)
	private BrandEnum brand;


	
	
	
	private GenderEnum gender;
	
	@Enumerated(EnumType.STRING)
	private SizeEnum size;
	
	@Enumerated(EnumType.STRING)
	private SportEnum sport;
	
	@Enumerated(EnumType.STRING)
	private TerrainEnum terrain;
	
	@Enumerated(EnumType.STRING)
	private TypeEnum type;

	public void setBrand(BrandEnum adidas) {
		// TODO Auto-generated method stub
		
	}
}
