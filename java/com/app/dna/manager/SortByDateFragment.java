package com.app.dna.manager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.app.dna.manager.Utils.Constants;

import java.util.Calendar;

/**
 * Created by dhawal on 20/4/16.
 */
public class SortByDateFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "OverflowFragment";
    private TextView tvStartDate;
    private TextView tvEndDate;
    private Button btnShow;
    private String startDate, endDate;
    private String userId;
    android.app.FragmentManager fm;


    TransactionDetailActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.sort_by_date_layout, container,
                false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        fm = getActivity().getFragmentManager();
        activity = TransactionDetailActivity.getInstance();
        tvStartDate = (TextView) rootView.findViewById(R.id.tvStartDate);
        tvEndDate = (TextView) rootView.findViewById(R.id.tvEndDate);
        btnShow = (Button) rootView.findViewById(R.id.btnShowTrans);

        tvStartDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        return rootView;
    }

    SortByDateFragment(String userID)
    {
        userId = userID;
    }

    public void showStartDatePicker(android.app.FragmentManager fm) {
        DatePickerFragment date = new DatePickerFragment();
        //DialogFragment frag = new DialogFragment();
        Calendar calendar = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("month", calendar.get(Calendar.MONTH));
        args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        //Toast.makeText(getApplicationContext(),"Date set",Toast.LENGTH_SHORT).show();
        date.setCallBack(onStartDate);
        date.show(fm, "date picker");

    }

    public void showEndDatePicker(android.app.FragmentManager fm) {
        DatePickerFragment date = new DatePickerFragment();
        //DialogFragment frag = new DialogFragment();
        Calendar calendar = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("month", calendar.get(Calendar.MONTH));
        args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        //Toast.makeText(getApplicationContext(),"Date set",Toast.LENGTH_SHORT).show();
        date.setCallBack(onEndDate);
        date.show(fm, "date picker");

    }

    DatePickerDialog.OnDateSetListener onStartDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            startDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year);
            tvStartDate.setText(startDate);

        }
    };


    DatePickerDialog.OnDateSetListener onEndDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            endDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year);
            tvEndDate.setText(endDate);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStartDate:
                showStartDatePicker(fm);
                break;

            case R.id.tvEndDate:
                showEndDatePicker(fm);
                break;

            case R.id.btnShowTrans:

                String url = "getAllTransactionInfoByDate&tran_emp_id="+userId+"&tran_date_from="+startDate+"&tran_date_to="+endDate;
                 TransactionDetailActivity.getInstance().new AsyncGetTransactionInfo(Constants.SORTED_TRANSCATIONS)
                                            .execute(Constants.baseUrl + url);
                Log.e("URL!!",Constants.baseUrl + url);



                break;

        }
    }
}
