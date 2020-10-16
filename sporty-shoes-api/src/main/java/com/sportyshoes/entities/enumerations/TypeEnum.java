package com.sportyshoes.entities.enumerations;



/**
 * The Enumeration for type of shoes.
 */
public enum TypeEnum {

	/** The cross training. */
	CROSS_TRAINING("Cross Training"),
	
	/** The racing shoes. */
	RACING_SHOES("Racing shoes"),
	
	/** The walking shoes. */
	WALKING_SHOES("Walking shoes"),
	
	/** The walking boots. */
	WALKING_BOOTS("Walking boots");
	
	/** The value. */
	private String value;
	
	/**
	 * Instantiates a new type enum.
	 *
	 * @param val the val
	 */
	private TypeEnum(String val) {
		this.value = val;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}
}
