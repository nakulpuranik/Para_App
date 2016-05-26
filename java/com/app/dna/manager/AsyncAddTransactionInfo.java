package com.app.dna.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.app.dna.manager.Transaction.TransactionModel;

public class AsyncAddTransactionInfo extends AsyncTask<String, Void, Void>
{
    
    String data = "";
    BufferedReader reader = null;
    private String Content;
    Context ct;
    TransactionModel newTransaction = new TransactionModel();
    public AsyncAddTransactionInfo(Context ct, TransactionModel newTrans) {
    	this.ct = ct;
        this.newTransaction = newTrans;
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

    private static final String TAG = "AsyncAddTransactionInfo";
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(ct, "Response if "+Content, Toast.LENGTH_LONG).show();
        Log.e(TAG, "onClick: " + newTransaction.showAsString());
        TransactionDetailActivity.getInstance().dismissDialog(newTransaction);
    }
}
