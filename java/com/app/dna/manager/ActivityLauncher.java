package com.app.dna.manager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityLauncher extends FragmentActivity {
	
	//add Transaction button
	Button dfragbutton;
	//fragment Manager
	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_launcher);
		
		// Locate the button in activity_activity_launcher.xml
		dfragbutton = (Button) findViewById(R.id.dfragbutton);
		
	}
	
	//Handles the onclick functionality for the Add Transaction
	public void dialogFragment(View view)
	{
		DFragment dFragment = new DFragment();
		// Show DialogFragment
		dFragment.show(fm, "Dialog Fragment");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_launcher, menu);
		return true;
	}

}
