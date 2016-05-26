package com.app.dna.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.app.dna.manager.Utils.UTIL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AsyncCheckLogin extends AsyncTask<String, Void, Void>
{
    
    String data = "";
    BufferedReader reader = null;
    public String Content;
    Context ct;
    public AsyncCheckLogin(Context ct) {
    	this.ct = ct;
	}

    @Override
    protected Void doInBackground(String... urls)
    {
        try
        {
            URL url = new URL(urls[0]);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);

            // Read Response from server
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server response
            while ((line = reader.readLine())!= null)
            {
                sb.append(line + " ");
            }
            Content = sb.toString();
            Log.e("Employee Info ", Content);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("Emp Info", "SIZE::"+ e.toString() );
        }

        finally {
            //reader.close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
       
        //TODO change this when LoginWebserive is written
        ActivityLogin.getInstance().loginStatusResp = Content;
        Toast.makeText(ActivityLogin.getInstance(), "Response if "+Content, Toast.LENGTH_LONG).show();
        if(Content.isEmpty())
        {
            Toast.makeText(ct.getApplicationContext(), "Problem In Login", Toast.LENGTH_LONG).show();
            return;
        }
        //check the response from the web-service
        if(Content.trim().equalsIgnoreCase("false"))
        {
            Toast.makeText(ct.getApplicationContext(), "Incorrect credentials", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            //set the value into SharedPreferences
            UTIL.setLoginToSharedPref(ct.getApplicationContext());
            ActivityLogin.getInstance().proceedToNextActivity();
        }
    }
}

