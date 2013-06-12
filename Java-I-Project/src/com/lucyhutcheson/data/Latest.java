package com.lucyhutcheson.data;

public class Latest implements Movie {

	String name;
	double rating;
	
	// create constructor
	public Latest(String name, double rating) {
		setName(name);
		setRatings(rating);
	}

	@Override
	public boolean setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
		return true;
	}

	@Override
	public boolean setRatings(double rating) {
		// TODO Auto-generated method stub
		this.rating = rating;
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public double getRatings() {
		// TODO Auto-generated method stub
		return this.rating;
	}

}
