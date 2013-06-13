package com.lucyhutcheson.data;

public class Latest implements Movie {

	String title;
	String rating;
	
	// create constructor
	public Latest(String title, String rating) {
		setTitle(title);
		setRatings(rating);
	}

	@Override
	public boolean setTitle(String title) {
		this.title = title;
		return true;
	}

	public boolean setRatings(String rating) {
		this.rating = rating;
		return true;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getRatings() {
		return this.rating;
	}

}
