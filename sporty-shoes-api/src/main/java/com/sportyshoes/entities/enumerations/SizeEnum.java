package com.sportyshoes.entities.enumerations;


/**
 * The Enum SizeEnum.
 */
public enum SizeEnum {

	Three("3"),
	Three_five("3.5"),
	Four("4"),
	Four_five("4.5"),
	Five("5"),
	Five_five("5.5");
	
	private String value;
	
	private SizeEnum(String val) {
		this.value = val;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
