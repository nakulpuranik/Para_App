package com.app.dna.manager;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
/**
 * Created by tnex on 21/10/15.
 * Class name under ToDo App:
 * Description:
 */
public class DatePickerFragment extends DialogFragment{


    private DatePickerDialog.OnDateSetListener mOnDateSet;
    private int year,month,day;

    public DatePickerFragment(){}

    public void setCallBack(DatePickerDialog.OnDateSetListener onDateSet)
    {
        mOnDateSet = onDateSet;
    }
    public void setArguments(Bundle args)
    {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new DatePickerDialog(getActivity(),mOnDateSet,year,month,day);

    }

}
