package com.lucyhutcheson.lib;

import java.util.ArrayList;

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
	Button _add;
	Button _remove;
	Spinner _list; 
	Context _context;
	ArrayList<String> _movies = new ArrayList<String>();

	// CONSTRUCTOR
	public SavedDisplay(Context context) {
		super(context);
		_context = context;
		LayoutParams lp;
		
		// CREATE SPINNER (DROPDOWN)
		_movies.add("Select Movie");
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
				Log.i("MOVIE SELECTED", parent.getItemAtPosition(pos).toString());
			}
			// IF NO MOVIE IS SELECTED
			@Override
			public void onNothingSelected(AdapterView<?> parent){
				Log.i("MOVIE SELECTED", "NONE");
				
			}
		});
		
		// UPDATE OUR SPINNER WITH MOVIES
		updateSaved();
		
		// CREATE + AND - BUTTONS TO ADD MOVIES TO OUR SELECTED DATA
		_add = new Button(_context);
		_add.setText("+");
		_remove = new Button(_context);
		_remove.setText("-");
		
		// ADD THE SPINNER ADN BUTTONS TO OUR LAYOUT
		this.addView(_list);
		this.addView(_add);
		this.addView(_remove);
		
		// SETUP LAYOUT PARAMETER FOR THIS SAVED DISPLAY TO BE AS WIDE AS PARENT
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(lp);
		
	}
	// UPDATE OUR SAVED ARRAYLIST WITH MOVIES
	private void updateSaved(){
		_movies.add("Toy Story");
		_movies.add("Snitch");
		_movies.add("Cars 2");
	}


	
}
