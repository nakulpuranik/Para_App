package com.app.dna.manager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dna.manager.Utils.UTIL;

import java.util.concurrent.ExecutionException;

public class ActivityLogin extends Activity {

	public static ActivityLogin loginObj = null;
	public String loginStatusResp = "";
	
	static ActivityLogin getInstance()
	{
		return loginObj;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_login);
		loginObj = this;
		
		//TODO check if the values are available in shared preferences.
		
		if(UTIL.checkLoginFromSharedPref(getApplicationContext()))
		{
			proceedToNextActivity();
		}
		
		//Otherwise force user to login.
		
		TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

		// Listening to register new account link
		registerScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Switching to Register screen
				Toast.makeText(getBaseContext(), "Contact Developer Team",
						Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	public void proceedToNextActivity()
	{
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	public void checkLoginValue(View view) throws ExecutionException, InterruptedException {
		// check the credentials
		String userEmail = "";
		String userPassword = "";
		EditText etUserEmail = (EditText) findViewById(R.id.etUserEmail_ActivityLogin);
		EditText etUserPassword = (EditText) findViewById(R.id.etUserPassword_ActivityLogin);

		if (etUserEmail.getText().toString().isEmpty()) {
			etUserEmail.setError("Email is empty");
			return;
		} else {
			userEmail = etUserEmail.getText().toString();
			etUserEmail.setError(null);
		}

		if (etUserPassword.getText().toString().isEmpty()) {
			etUserPassword.setError("Password is empty");
			return;
		} else {
			userPassword = etUserPassword.getText().toString();
			etUserPassword.setError(null);
		}

		// TODO call HTTP Request to fetch the user email and password.
		Toast.makeText(this, "Call the Webservice",
				Toast.LENGTH_LONG).show();

		String url = Uri
				.parse("http://www.forksystems.com/para_app/InitialGateway.php")
				.buildUpon()
				.appendQueryParameter("op", "checkLogin")
				.appendQueryParameter("user_email", userEmail)
				.appendQueryParameter("user_pass", userPassword).build()
				.toString();

		AsyncCheckLogin checkLogin = new AsyncCheckLogin(ActivityLogin.this);
		checkLogin.execute(url).get();

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_activity_login, menu);
		return true;
	}*/

	public void setLoginStatus(String string) {
		// TODO Auto-generated method stub
		this.loginStatusResp = string;
	}
	public String getLoginStatus() {
		// TODO Auto-generated method stub
		return this.loginStatusResp;
	}
}
