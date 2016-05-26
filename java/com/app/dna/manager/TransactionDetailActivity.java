package com.app.dna.manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dna.manager.Transaction.TransactionDetailsListAdapter;
import com.app.dna.manager.Transaction.TransactionModel;
import com.app.dna.manager.Utils.Constants;
import com.app.dna.manager.Utils.JSONParser;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TransactionDetailActivity extends AppCompatActivity {

    private TransactionDetailsListAdapter transactionDetailsListAdapter;
    private ListView lvDetails;
    private TextView etName, tvDueAmt, tvPaidAmt;
    private TextView etSalary;
    private ArrayList<TransactionModel> transactionList;
    public String userName, userId;
    private ImageView ivMenu;
    private Button dfragbutton;
   public FragmentManager fm = getSupportFragmentManager();
    private static DFragment dFragment;
    private int salary;
    private static final String TAG = "TransactionDetailActivity";
    private OverflowFragment overflowFragment;
    public static TransactionDetailActivity transactionDetailActivity;

private int totalTransactionAmount = 0;

    public enum Type {
        UNSORTED,
        SORTED
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        transactionDetailActivity = this;
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        userId = String.valueOf(intent.getIntExtra("userId", 0));
        salary = intent.getIntExtra("salary", 0);
        Log.e(TAG, "Salary " + salary);
        Button dfragbutton;

        init();

        new AsyncGetTransactionInfo(Constants.UNSORTED_TRANSCATIONS).execute(Constants.baseUrl + "getAllTransactionInfo&id=" + userId);
        Log.e("TRANS::",Constants.baseUrl+"getAllTransactionInfo&id=" + userId);
    }

    private void init() {

        lvDetails = (ListView) findViewById(R.id.lvTransactions);
        etName = (TextView) findViewById(R.id.etName);
        etName.setText(userName);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        etSalary = (TextView) findViewById(R.id.etSalary);
        etSalary.setText(String.valueOf(salary));
        tvDueAmt = (TextView) findViewById(R.id.etDueAmt);
        tvPaidAmt = (TextView) findViewById(R.id.etPaidAmt);
    }

    public static TransactionDetailActivity getInstance()
    {
        return transactionDetailActivity;
    }

    public void overflowMenu(View view)
    {
        Toast.makeText(TransactionDetailActivity.this, "overflow", Toast.LENGTH_SHORT).show();
        overflowFragment = new OverflowFragment(userId);
        // Show DialogFragment
        overflowFragment.show(fm, "Dialog Fragment");
    }

    public void dialogFragment(View view) {
        dFragment = new DFragment();
        Bundle data = new Bundle();
        data.putString("empId", userId);
        data.putString("ownerId", "1");
        dFragment.setArguments(data);
        // Show DialogFragment
        dFragment.show(fm, "Dialog Fragment");
    }

    public void dismissDialog(TransactionModel newTrans) {
        transactionList.add(newTrans);
        transactionDetailsListAdapter.notifyDataSetChanged();
        dFragment.dismiss();
    }

    public class AsyncGetTransactionInfo extends AsyncTask<String, Void, Void> {


        int UNSORTED;
        String data = "";
        BufferedReader reader = null;
        private String Content;
        private ProgressDialog progressDialog = new ProgressDialog(TransactionDetailActivity.this);
        int TYPE;

        AsyncGetTransactionInfo()
        {

        }

        AsyncGetTransactionInfo(int Type)
        {
            TYPE = Type;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... urls) {

            switch (TYPE)
            {
                case 0:
                    try {
                        URL url = new URL(urls[0]);
                        URLConnection con = url.openConnection();
                        con.setDoOutput(true);

                        // Read Response from server
                        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;

                        // Read Server response
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + " ");
                        }
                        Content = sb.toString();
                        Log.e("Employee Info ", Content);

                        JSONArray dataArray = new JSONArray(Content);

                        transactionList = JSONParser.parseUsersAllTransactions(dataArray);
                        Log.e("Emp Info", "@@SIZE::" + transactionList.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("TRANS Info", "SIZE::" + e.toString());
                    } finally {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        catch(Exception e)
                        {
                            Log.e(TransactionDetailActivity.class.getSimpleName(),"Error",e);
                        }
                    }

                    break;

                case 1:

                    try {
                        URL url = new URL(urls[0]);
                        URLConnection con = url.openConnection();
                        con.setDoOutput(true);

                        // Read Response from server
                        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;

                        // Read Server response
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + " ");
                        }
                        Content = sb.toString();
                        Log.e("Employee Info1 ", Content);

                        JSONArray dataArray = new JSONArray(Content);

                        transactionList = JSONParser.parseUsersAllTransactions(dataArray);
                        Log.e("Emp Info", "@@SIZE::" + transactionList.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("TRANS Info", "SIZE::" + e.toString());
                    } finally {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            setTransactionList();
            progressDialog.dismiss();
        }
    }

    public void setTransactionList() {

        if(transactionList == null)
        {
            Toast.makeText(getApplicationContext(),"No records found.",Toast.LENGTH_SHORT).show();
            Log.e("Response::", "No records found.");
            return;
        }
        else
        {
            for(int i = 0; i< transactionList.size(); i++)
            {
                totalTransactionAmount = totalTransactionAmount + transactionList.get(i).getAmount();
            }
            Log.e("Sorting::","Date wise sorted");
            tvPaidAmt.setText(String.valueOf(totalTransactionAmount));
            tvDueAmt.setText(String.valueOf(salary - totalTransactionAmount ));
            transactionDetailsListAdapter = new TransactionDetailsListAdapter(TransactionDetailActivity.this, transactionList);
            lvDetails.setAdapter(transactionDetailsListAdapter);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public static class OverflowFragment extends DialogFragment {


        private static final String TAG = "OverflowFragment";
        TextView tvSortByDate, tvPaymentDone;
        private String usrId;
        FragmentManager fragmentManager;

        OverflowFragment(String userID)
        {
            usrId = userID;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.overflow_menu_layout, container,
                    false);
            getDialog().getWindow().setGravity(Gravity.RIGHT | Gravity.TOP);
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

            tvPaymentDone = (TextView) rootView.findViewById(R.id.tvPaymentDone);
            tvSortByDate = (TextView) rootView.findViewById(R.id.tvSortByDate);

            tvSortByDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SortByDateFragment sortByDateFragment = new SortByDateFragment(usrId);

                    sortByDateFragment.show(TransactionDetailActivity.getInstance().fm,"Filter fragment");
                }
            });

            return rootView;
        }

    }

/*   public static class SortByDateFragment extends DialogFragment implements View.OnClickListener {

        private static final String TAG = "OverflowFragment";
        private TextView tvStartDate;
        private TextView tvEndDate;
        private Button btnShow;
        android.app.FragmentManager fm;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.sort_by_date_layout, container,
                    false);
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

            fm = getActivity().getFragmentManager();
            tvStartDate = (TextView) rootView.findViewById(R.id.tvStartDate);
            tvEndDate = (TextView) rootView.findViewById(R.id.tvEndDate);
            btnShow = (Button) rootView.findViewById(R.id.btnShowTrans);

            tvStartDate.setOnClickListener(this);
            tvEndDate.setOnClickListener(this);
            return rootView;
        }

        public void showStartDatePicker(android.app.FragmentManager fm)
        {
            DatePickerFragment date = new DatePickerFragment();
            //DialogFragment frag = new DialogFragment();
            Calendar calendar = Calendar.getInstance();
            Bundle args = new Bundle();
            args.putInt("year",calendar.get(Calendar.YEAR));
            args.putInt("month",calendar.get(Calendar.MONTH));
            args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
            date.setArguments(args);
            //Toast.makeText(getApplicationContext(),"Date set",Toast.LENGTH_SHORT).show();
            date.setCallBack(onStartDate);
            date.show(fm, "date picker");

        }

        public void showEndDatePicker(android.app.FragmentManager fm)
        {
            DatePickerFragment date = new DatePickerFragment();
            //DialogFragment frag = new DialogFragment();
            Calendar calendar = Calendar.getInstance();
            Bundle args = new Bundle();
            args.putInt("year",calendar.get(Calendar.YEAR));
            args.putInt("month",calendar.get(Calendar.MONTH));
            args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
            date.setArguments(args);
            //Toast.makeText(getApplicationContext(),"Date set",Toast.LENGTH_SHORT).show();
            date.setCallBack(onEndDate);
            date.show(fm, "date picker");

        }

        DatePickerDialog.OnDateSetListener onStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                tvStartDate.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1) + "/" + String.valueOf(year));
            }
        };


        DatePickerDialog.OnDateSetListener onEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                tvEndDate.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1) + "/" + String.valueOf(year));
            }
        };

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.tvStartDate:
                    showStartDatePicker(fm);
                    break;

                case R.id.tvEndDate:
                    showEndDatePicker(fm);
                    break;

                case R.id.btnShowTrans:
                    new AsyncGetTransactionInfo(Constants.SORTED_TRANSCATIONS).execute(Constants.baseUrl+"getAllTransactionInfo&id=" + userId);
                    break;

            }
        }
    }*/



}

