package com.lucyhutcheson.data;


import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

	public static JSONObject buildJsonObject() {

		// create main JSONObject to hold entire JSON
		JSONObject movieObject = new JSONObject();

		try {

			// create query object to hold movies
			JSONObject queryObject = new JSONObject();

			// Create movie objects from enums
			for (sampleData movie : sampleData.values()) {
				// Create another object to hold movies
				JSONObject moviesObject = new JSONObject();
				// add movies to the object
				moviesObject.put("title", movie.setTitle());
				moviesObject.put("rating", movie.setRating());
				// add the movie object to our query object
				queryObject.put(movie.name().toString(), moviesObject);
			}
			// Add our queryObject 
			movieObject.put("movies", queryObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieObject;

	}

	public static String readJSON(String selected) {
		// Setup variables to hold data
		String result, movie, rating;

		// Build our JSONobject to read from
		JSONObject object = buildJsonObject();

		try {
			// Get the title and rating information for our selected movie
			movie = object.getJSONObject("movies").getJSONObject(selected).getString("title");
			rating = object.getJSONObject("movies").getJSONObject(selected).getString("rating");

			// Add this information to our string to return
			result = "Movie: " + movie + "\r\n" + "Rating: " + rating + "\r\n";
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
		}
		// Return our searched for information
		return result;
	}

}
