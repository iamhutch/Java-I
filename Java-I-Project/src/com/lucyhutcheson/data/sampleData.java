package com.lucyhutcheson.data;

public enum sampleData {
	MOVIE1("World War Z", "PG-13"),
	MOVIE2("Monster University", "G"),
	MOVIE3("Maniac", "R"),
	MOVIE4("A Hijacking", "R"),
	MOVIE5("Unfinished Song", "PG-13");

	private final String name;
	private final String rating;
	
	private sampleData(String name, String rating){
		this.name = name;
		this.rating = rating;
	}
	
	public String setName(){
		return name;
	}
	
	public String setRating(){
		return rating;
	}
}
