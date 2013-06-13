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

import java.util.ArrayList;
import com.lucyhutcheson.data.JSON;
import com.lucyhutcheson.data.Latest;
import com.lucyhutcheson.data.Movie;
import com.lucyhutcheson.lib.LayoutItems;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	// setup Variables
	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	RadioGroup movieOptions;
	TextView result;
	TextView temp;
	ArrayList<Movie> movies;
	int id;	
	String selected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // initiate new linear layout
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        
        // initiate layout params
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        
		// setup text view and add a title
        TextView tv = new TextView(this);
        tv.setText("Latest Movies");
        // Add TextView to our layout
        ll.addView(tv);
        
		// create an array list of movies
		movies = new ArrayList<Movie>();
		movies.add(new Latest("MOVIE1", "PG-13"));
		movies.add(new Latest("MOVIE2", "G"));
		movies.add(new Latest("MOVIE3", "R"));
		movies.add(new Latest("MOVIE4", "R"));
		movies.add(new Latest("MOVIE5", "PG-13"));
		
		// String arrays in java need to have length specified when initialized
		String[] latestList = new String[movies.size()];
		for (int i= 0; i< movies.size(); i++){
			// add names of movies to productNames string array
			latestList[i] = movies.get(i).getTitle();
		}
		
		// create radio group to add to layout
		movieOptions = LayoutItems.getMovies(this, latestList);
		// add radio group to layout
		ll.addView(movieOptions);

		// Add layout with buttons
		LinearLayout movieBox = LayoutItems.singleEntryWithButton(this);
		ll.addView(movieBox);

		// Latest Movies Button
		Button latestBtn = (Button) movieBox.findViewById(1);
        latestBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				id = movieOptions.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton) findViewById(id);
				selected = rb.getText().toString();
				//Log.d("MyLog", "selected=" + selected);
				result.setText(JSON.readJSON(selected));
			}
		});        

        // Save Movie selection Button 
		Button saveBtn = (Button) movieBox.findViewById(2);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				// setup text view and add a title
		        temp.setText("Save options will come later.");

			}
		});    
        
		// Create textViews for resulting text string and add it to the view
        // Result TextView to hold calculations
        result = new TextView(this);
        ll.addView(result);
        
        // Create textViews for resulting text string and add it to the view
        // Result TextView to hold calculations
        temp = new TextView(this);
        ll.addView(temp);
		
		setContentView(ll);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
