package com.sportyshoes.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items_purchased")
@Data @NoArgsConstructor
public class ItemsPurchased implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "shoe")
	private Shoe shoe;	
	
	private LocalDateTime purchasedDate;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "customer")
	private Customer customer;
}
