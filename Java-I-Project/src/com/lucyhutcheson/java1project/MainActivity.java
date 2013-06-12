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

import com.lucyhutcheson.lib.LayoutItems;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	// setup Variables
	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	TextView result;
	TextView temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // initiate new linear layout
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        
        // initiate layout params
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        
		LinearLayout movieBox = LayoutItems.singleEntryWithButton(this);

		// setup text view and add a title
        TextView tv = new TextView(this);
        tv.setText("Latest Movies");
        // Add TextView to our layout
        ll.addView(tv);


        // Latest Movies Button
		Button latestBtn = (Button) movieBox.findViewById(1);
        latestBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// Dismiss the keyboard so we can see our text
				//InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				//imm.hideSoftInputFromWindow(carsField.getWindowToken(), 0);

				// Setup labels from our resource file of labels
				String nameLabel = getResources().getString(R.string.name);
				String ratingLabel = getResources().getString(R.string.rating);

				// setup text view and add a title
		        result.setText(nameLabel+": \r\n"+ratingLabel+": \r\n");
		
			}
		});        

        // Save Movies Button
		Button saveBtn = (Button) movieBox.findViewById(2);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				// Dismiss the keyboard so we can see our text
				//InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				//imm.hideSoftInputFromWindow(carsField.getWindowToken(), 0);

				// setup text view and add a title
		        temp.setText("Save options will come later.");

		        
			}
		});    
        

		ll.addView(movieBox);
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
