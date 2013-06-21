package com.lucyhutcheson.lib;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SavedDisplay extends LinearLayout {

	// SETUP VARIABLES
	Button _remove;
	Spinner _list; 
	Context _context;
	ArrayList<String> _movies = new ArrayList<String>();
	HashMap<String, String> favList = new HashMap<String, String>();
	MovieDisplay _movie;
	
	// CONSTRUCTOR
	public SavedDisplay(Context context) {
		super(context);
		_context = context;
		LayoutParams lp;
		
		// CREATE SPINNER (DROPDOWN)
		_movies.add("View Favorites");
		_list = new Spinner(_context);
		lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		_list.setLayoutParams(lp);
		
		//CREATE ADAPTER FOR MY DROPDOWN
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_item, _movies);
		listAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		_list.setAdapter(listAdapter);
		_list.setOnItemSelectedListener(new OnItemSelectedListener() {
			// IF A MOVIE IS SELECTED
			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int pos, long id){
				String str = parent.getItemAtPosition(pos).toString();
				if(!str.equals("View Favorites")){
					Log.i("MOVIE SELECTED", str);
					String selected = favList.get(str);
					Log.i("FAVORITE SELECTED", selected);
					JSONObject json;
					try {
						json = new JSONObject(selected);
						Log.i("FAVORITES SELECTED", json.getString("title"));
						// GET DATA AND DISPLAY ON SCREEN
						_movie._name.setText("hello");
						//_movie._rating.setText(json.getJSONObject("ratings").getString("critics_score"));
						//_movie._year.setText(json.getString("year"));
						//_movie._mpaa.setText(json.getString("mpaa_rating"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// IF NO MOVIE IS SELECTED
			@Override
			public void onNothingSelected(AdapterView<?> parent){
				Log.i("MOVIE SELECTED", "NONE");
				
			}
		});
		
		// UPDATE OUR SPINNER WITH MOVIES
		updateSaved();
		
		// CREATE - BUTTON TO REMOVE MOVIE FROM FAVORITE DATA
		_remove = new Button(_context);
		_remove.setText("-");
		
		// ADD THE SPINNER AND BUTTON TO OUR LAYOUT
		this.addView(_list);
		this.addView(_remove);
		
		// SETUP LAYOUT PARAMETER FOR THIS SAVED DISPLAY TO BE AS WIDE AS PARENT
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(lp);
		
	}
	// UPDATE OUR SAVED ARRAYLIST WITH MOVIES
	public void updateSaved(){
		favList = getFavorites();
		_movies.addAll(favList.keySet());
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

	
}
