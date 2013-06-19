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
	HashMap<String, String> _history;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_context = this;
		_appLayout = new LinearLayout(this);
		//_history = getHistory();
		//Log.i("HISTORY READ", _history.toString());

		_search = new SearchForm(_context, "Enter Movie Name", "Go");
		
		// ADD SEARCH HANDLER
		Button searchButton = _search.getButton();
		
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// GET MOVIE INFORMATION
				getMovie(_search.getField().getText().toString());
				//Log.i("CLICK HANDLER", _search.getField().getText().toString());
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
	private HashMap<String, String> getHistory(){
		Object stored = FileFunctions.readObjectFile(_context, "history", false);
		HashMap<String, String> history;
		
		// CHECK IF OBJECT EXISTS
		if (stored == null){
			Log.i("HISTORY", "NO HISTORY FILE FOUND");
			history = new HashMap<String, String>();
		} else {
			//CAST HASHMAP
			history = (HashMap<String, String>) stored;
		}
		return history;
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
					Toast toast = Toast.makeText(_context, "Great Movie: " + results.getJSONObject(0).getString("title"), Toast.LENGTH_SHORT);  
					toast.show();
					_history.put(results.getJSONObject(0).getString("title"), results.toString());
					FileFunctions.storeObjectFile(_context, "history", _history, false);
					FileFunctions.storeStringFile(_context, "temp", results.toString(), true);
				}
			} catch (JSONException e) {
				Log.e("JSON", "JSON OBJECT EXCEPTION");
				e.printStackTrace();
			}
		}
	}
	
}
