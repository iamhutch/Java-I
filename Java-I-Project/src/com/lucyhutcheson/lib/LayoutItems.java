package com.lucyhutcheson.lib;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.LinearLayout.LayoutParams;

public class LayoutItems {

	public static LinearLayout singleEntryWithButton(Context context){
		LinearLayout ll = new LinearLayout(context);
		// return the type of data we declared
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(lp);
				
		Button latestBtn = new Button(context);
        lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.25f);
		latestBtn.setText("Get Details for Selected Movie");
		latestBtn.setLayoutParams(lp);
		latestBtn.setId(1);
		
		Button saveBtn = new Button(context);
        lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.25f);
		saveBtn.setText("Save Movie Selection");
		saveBtn.setLayoutParams(lp);
		saveBtn.setId(2);

		ll.addView(latestBtn);
		ll.addView(saveBtn);
		
		return ll;
	}

	public static RadioGroup getMovies(Context context, String[] options) {
		RadioGroup radios = new RadioGroup(context);
		
		for (int i = 0; i< options.length; i++){
			RadioButton rb = new RadioButton(context);
			rb.setText(options[i]);
			rb.setId(i+1);
			radios.addView(rb);
		}
		
		return radios;
		
	}

}
