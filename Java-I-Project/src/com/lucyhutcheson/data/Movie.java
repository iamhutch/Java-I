package com.lucyhutcheson.data;

public interface Movie {
	// set the name of movie
	public boolean setTitle(String title);
	// set the ratings of movie
	public boolean setRatings(String rating);
	
	// get the name of a movie
	public String getTitle();
	
	//get the ratings of a movie
	public String getRatings();

}

