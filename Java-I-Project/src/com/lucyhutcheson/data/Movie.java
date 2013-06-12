package com.lucyhutcheson.data;

public interface Movie {
	// set the name of movie
	public boolean setName(String name);
	// set the ratings of movie
	public boolean setRatings(double rating);
	
	// get the name of a movie
	public String getName();
	
	//get the ratings of a movie
	public double getRatings();

}

