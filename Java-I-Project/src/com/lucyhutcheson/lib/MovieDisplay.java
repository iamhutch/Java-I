package com.lucyhutcheson.lib;


import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;
import com.lucyhutcheson.java1project.R;

public class MovieDisplay extends GridLayout {

	public TextView _name;
	public TextView _rating;
	public TextView _year;
	public TextView _mpaa;
	Context _context;

	public MovieDisplay(Context context) {
		super(context);
		
		_context = context;
		
		// Setup gridview with 2 columns for our data
		this.setColumnCount(2);

		// Setup our movie labels and text view containers for our data
		TextView nameLabel = new TextView(_context);
		nameLabel.setText(getResources().getText(R.string.name)+":");
		_name = new TextView(_context);
		
		TextView ratingLabel = new TextView(_context);
		ratingLabel.setText(getResources().getText(R.string.rating)+":");
		_rating = new TextView(_context);

		TextView yearLabel = new TextView(_context);
		yearLabel.setText(getResources().getText(R.string.year)+":");
		_year = new TextView(_context);

		TextView mpaaLabel = new TextView(_context);
		mpaaLabel.setText(getResources().getText(R.string.mpaa_rating)+":");
		_mpaa = new TextView(_context);
		
		// Add the labels and textview containers to our view
		this.addView(nameLabel);
		this.addView(_name);
		this.addView(ratingLabel);
		this.addView(_rating);
		this.addView(yearLabel);
		this.addView(_year);
		this.addView(mpaaLabel);
		this.addView(_mpaa);

		

	}

}
