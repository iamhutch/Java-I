package com.lucyhutcheson.data;

public enum Ratings {
	RATINGG("General Audiences"),
	RATINGPG("Parental Guidance Suggested"),
	RATINGPG13("Parents Strongly Cautioned"),
	RATINGR("Restricted");
	
	private String definition;
	
	private Ratings(String s) {
		definition = s;
	}
	
	public String getRatingDefinition(){
		return definition;
	}
}
