package com.lucyhutcheson.data;

public enum sampleData {
	MOVIE1("World War Z", "PG-13"),
	MOVIE2("Monster University", "G"),
	MOVIE3("Maniac", "R"),
	MOVIE4("A Hijacking", "R"),
	MOVIE5("Unfinished Song", "PG-13");

	private final String title;
	private final String rating;
	
	private sampleData(String title, String rating){
		this.title = title;
		this.rating = rating;
	}
	
	public String setTitle(){
		return title;
	}
	
	public String setRating(){
		return rating;
	}
}
