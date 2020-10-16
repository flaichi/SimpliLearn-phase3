package com.sportyshoes.entities.enumerations;

/**
 * The Enumeration for the Terrain.
 */
public enum TerrainEnum {

	LIGHT_TRAIL("Light Trail"),
	ROAD("Road"),
	TRACK_AND_FIELD("Track and fiedl"),
	SEVERE_TRAIL("Severe Trail");
	
	private String value;
	
	private TerrainEnum(String val) {
		this.value = val;
	}
	
	public String getValue() {
		return this.value;
	}
}
