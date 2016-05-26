package com.app.dna.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.app.dna.manager.Employee.EmployeeModel;
import com.app.dna.manager.Utils.JSONParser;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ActivitySplash extends Activity
{

    private static int SPLASH_TIME_OUT = 3000;
    private static ArrayList<EmployeeModel> mList;
    private String Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, SPLASH_TIME_OUT);
        AsyncGetEmployeeInfo getEmployeeInfo = new AsyncGetEmployeeInfo();
        getEmployeeInfo.execute("http://forksystems.com/para_app/InitialGateway.php?op=getAllEmployeeInfo");
    }

    private class AsyncGetEmployeeInfo extends AsyncTask<String, Void, Void>
    {
        String data = "";
        BufferedReader reader = null;


        ArrayList<EmployeeModel> empList = new ArrayList<EmployeeModel>();

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

                JSONArray dataArray = new JSONArray(Content);
                empList = JSONParser.parseAllEmployee(dataArray);
                Log.e("Emp Info","@@SIZE::"+empList.size());

            }
            catch (Exception e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            getEmpList(empList);
            Intent intentToMainActivity = new Intent(ActivitySplash.this, ActivityLogin.class);
            intentToMainActivity.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intentToMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intentToMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentToMainActivity.putExtra("Json_Parsed_Data", Content);
            startActivity(intentToMainActivity);
        }

    }

    public void getEmpList( ArrayList<EmployeeModel> list)
    {
        mList = list;
        Log.i("Splas",""+mList.size());
    }

    public static ArrayList<EmployeeModel> passList()
    {
        return mList;
    }

    /*@Override
    protected void onResume()
    {
        super.onResume();
        if(Content == null)
        {
            AsyncGetEmployeeInfo getEmployeeInfo = new AsyncGetEmployeeInfo();
            getEmployeeInfo.execute("http://forksystems.com/para_app/InitialGateway.php?op=getAllEmployeeInfo");
        }
        else
        {
            Intent intentToMainActivity = new Intent(ActivitySplash.this, MainActivity.class);
            intentToMainActivity.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intentToMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intentToMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentToMainActivity.putExtra("Json_Parsed_Data", Content);
            startActivity(intentToMainActivity);
        }

    }*/
}
