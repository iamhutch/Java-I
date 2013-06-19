package com.lucyhutcheson.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

@SuppressLint("ViewConstructor")
public class SearchForm extends LinearLayout {
	// SETUP VARIABLES FOR THIS CLASS
	EditText _searchField;
	Button _searchButton;
	
	// CONSTRUCTOR
	public SearchForm(Context context, String hint, String buttonText) {
		super(context);
		LayoutParams lp;
		
		// CREATE AND SETUP MY SEARCH FIELD
		_searchField = new EditText(context);
		lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		_searchField.setLayoutParams(lp);
		_searchField.setHint(hint);
		
		// CREATE AND SETUP MY SEARCH BUTTON
		_searchButton = new Button(context);
		_searchButton.setText(buttonText);

		// ADD MY FIELD AND BUTTONS TO THIS SEARCHFORM CLASS
		this.addView(_searchField);
		this.addView(_searchButton);
		
		// SETUP THIS SEARCHFORM CLASS TO SPAN THE WIDTH OF THE PARENT LAYOUT
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(lp);
	}
	
	// RETURNS THE SEARCH FIELD
	public EditText getField(){
		return _searchField;
	}
	// RETURNS MY SEARCH BUTTON
	public Button getButton(){
		return _searchButton;
	}
}
