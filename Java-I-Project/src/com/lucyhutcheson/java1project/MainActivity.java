/*
 * project		java1project
 * 
 * package		com.lucyhutcheson.java1project
 * 
 * @author		Lucy Hutcheson
 * 
 * date			Jun 11, 2013
 * 
 */

package com.lucyhutcheson.java1project;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lucyhutcheson.lib.FileFunctions;
import com.lucyhutcheson.lib.MovieDisplay;
import com.lucyhutcheson.lib.SavedDisplay;
import com.lucyhutcheson.lib.SearchForm;
import com.lucyhutcheson.lib.WebConnections;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	// SETUP VARIABLES FOR CLASS
	Context _context;
	LinearLayout _appLayout;
	SearchForm _search;
	MovieDisplay _movie;
	SavedDisplay _saved;
	Boolean _connected = false;
	HashMap<String, String> _favorites;
	String _temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_context = this;
		_appLayout = new LinearLayout(this);
		_favorites = getFavorites();
		Log.i("HISTORY READ", _favorites.toString());
		_temp = getTemp();
		Log.i("TEMP READ", _temp.toString());

		_search = new SearchForm(_context, "Enter Movie Name", "Go");
		
		// SEARCH HANDLER
		Button searchButton = _search.getButton();
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// GET MOVIE INFORMATION
				getMovie(_search.getField().getText().toString());
				Log.i("CLICK HANDLER", _search.getField().getText().toString());
			}
		});
		
		
        // ADD TO FAVORITES
        Button addButton = new Button(this);
        addButton.setText("Add to Favorites");
        addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Dismiss the keyboard so we can see our text
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(_search.getField().getWindowToken(), 0);
				// EMPTY OUT OUR FIELDS
				clearFields();
				
				_temp = getTemp();
				Log.i("TEMP READ", _temp);
				try {
					
					JSONObject results = new JSONObject(_temp);
					_favorites.put(results.getString("title"), results.toString());
					FileFunctions.storeObjectFile(_context, "favorites", _favorites, false);
					Toast toast = Toast.makeText(_context, "Movie successfully added to favorites.", Toast.LENGTH_SHORT);
					toast.show();
					
				} catch (JSONException e) {
					Log.e("JSON", "JSON OBJECT EXCEPTION");
					e.printStackTrace();
				}
				_saved.updateSaved();

			}
		});

		// DETECTED NETWORK CONNECTION
		_connected = WebConnections.getcConnectionStatus(_context);
		if (_connected){
			Log.i("NETWORK CONNECTION", WebConnections.getConnectionType(_context));
		} 
		
		// ADD MOVIE DISPLAY
		_movie = new MovieDisplay(_context);
		
		// ADD FAVORITES DISPLAY
		_saved = new SavedDisplay(_context);
		
		// ADD VIEWS TO MAIN LAYOUT
		_appLayout.addView(_search);
		_appLayout.addView(_movie);
		_appLayout.addView(addButton);
		_appLayout.addView(_saved);
		
		// NEED TO TELL LAYOUT TO SHOW UP UNDERNEATH
		_appLayout.setOrientation(LinearLayout.VERTICAL);
		
		setContentView(_appLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// CLEAR OUT OUR FIELDS
	private void clearFields(){
		_search.getField().setText("");
		_movie._name.setText("");
		_movie._rating.setText("");
		_movie._year.setText("");
		_movie._mpaa.setText("");
		
	}
	
	// SETUP URL AND GET MOVIE DATA
	private void getMovie(String movie){
		String baseURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=bcqq9h5yxut6nm9qz77h3w3h&page_limit=5&q=";
		String mqs = movie; // MAKE SURE THAT MY MOVIE STRING IS ENCODED
		String qs;
		try {
			// ENCODE MY MOVIE STRING
			qs = URLEncoder.encode(mqs, "UTF-8");
		} catch (Exception e) {
			Log.e("BAD URL", "ENCODING PROBLEM");
			qs = "";
		}
		URL finalURL;
		try {
			// SETUP MY STRING AND REQUEST IT
			finalURL = new URL(baseURL+qs); 
			MovieRequest qr = new MovieRequest();
			qr.execute(finalURL); 
		} catch (MalformedURLException e){
			Log.e("BAD URL", "MALFORMED URL");
			finalURL = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private HashMap<String, String> getFavorites(){
		Object stored = FileFunctions.readObjectFile(_context, "favorites", false);
		HashMap<String, String> favorites;
		
		// CHECK IF OBJECT EXISTS
		if (stored == null){
			Log.i("FAVORITES", "NO FAVORITES FILE FOUND");
			favorites = new HashMap<String, String>();
		} 
		// IF OBJECT EXISTS, BRING IN DATA AND ADD TO HASHMAP
		else {
			//CAST HASHMAP
			Log.i("FAVORITES", "FAVORITES FILE FOUND");
			favorites = (HashMap<String, String>) stored;
		}
		return favorites;
	}
	
	private String getTemp(){
		Object tempStored = FileFunctions.readStringFile(_context, "temp", true);
		String temp = null;
		
		// CHECK IF OBJECT EXISTS
		if (tempStored == null){
			Log.i("TEMP", "NO TEMP FILE FOUND");
		} 
		// IF OBJECT EXISTS, BRING IN DATA AND ADD TO HASHMAP
		else {
			//CAST HASHMAP
			Log.i("TEMP", "TEMP FILE FOUND");
			temp =  (String) tempStored;
		}
		return temp;
	}
	
	// SETUP MOVIEREQUEST TO HANDLE URL
	private class MovieRequest extends AsyncTask<URL,Void, String> {

		@Override
		protected String doInBackground(URL... urls){ 
			String response = "";
			for(URL url: urls){
				response = WebConnections.getURLStringResponse(url);
			}
			return response;
		}
		
		@Override
		protected void onPostExecute(String result){
			Log.i("URL RESPONSE", result);
			try {
				JSONObject json = new JSONObject(result);
				JSONArray results = json.getJSONArray("movies");
				if(json.getString("total").compareTo("0")==0){
					Toast toast = Toast.makeText(_context, "Movie Not Found", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					Log.i("RESULT", "RESULT: "+results.getJSONObject(0).getString("title"));		
					FileFunctions.storeStringFile(_context, "temp", results.getJSONObject(0).toString(), true);
					
					// GET DATA AND DISPLAY ON SCREEN
					_movie._name.setText(results.getJSONObject(0).getString("title"));
					_movie._rating.setText(results.getJSONObject(0).getJSONObject("ratings").getString("critics_score"));
					_movie._year.setText(results.getJSONObject(0).getString("year"));
					_movie._mpaa.setText(results.getJSONObject(0).getString("mpaa_rating"));
				}
			} catch (JSONException e) {
				Log.e("JSON", "JSON OBJECT EXCEPTION");
				e.printStackTrace();
			}
		}
	}
	
}
