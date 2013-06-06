/*
 * project		java1project1
 * 
 * package		com.lucyhutcheson.java1project1
 * 
 * @author		Lucy Hutcheson
 * 
 * date			Jun 5, 2013
 * 
 */
package com.lucyhutcheson.java1project1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

	// setup Variables
	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	EditText carsField;
	TextView result;
	EditText nameField;
	EditText tiresField;
	
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
        tv.setText("Auto Tire Calculator");
        ll.addView(tv);
        
        // text fields
        carsField = new EditText(this);
        carsField.setHint("Enter Number of Cars");
        
        nameField = new EditText(this);
        nameField.setHint("Enter Your Name");

        tiresField = new EditText(this);
        tiresField.setHint("Do you need new tires?");
        
        
        // Button
        Button b = new Button(this);
        b.setText("Run App");
        //ll.addView(b);
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int numTires = getResources().getInteger(R.integer.numTires);
				
				int numCars = Integer.parseInt(carsField.getText().toString());
				
				if (tiresField.getText().toString() = "Yes")
				{
					
				}
				int totalTires = numTires*numCars;
				
				result.setText("Total Tires: " + totalTires + "\r\n");
			}
		});
        
        // Create the Layout for our app and setup the parameters
        LinearLayout form = new LinearLayout(this);
        form.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        form.setLayoutParams(lp);
        
        // Add text field and button to new layout
        form.addView(nameField);
        form.addView(carsField);
        form.addView(tiresField);
        form.addView(b);
        
        // Add new form layout to original view
        ll.addView(form);
        
        // Create textView for resulting text string and add it to the view
        result = new TextView(this);
        ll.addView(result);
        
        
        setContentView(ll);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
