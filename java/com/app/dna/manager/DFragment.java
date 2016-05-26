package com.app.dna.manager;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.dna.manager.Transaction.TransactionModel;

public class DFragment extends DialogFragment {
	
	Button btnSubmitTransInfo;
	String transEmpId = "";
	String transOwnId = "";
	String transAmt = "";
	String transDesc = "";
	String transDate = "";
    private static final String TAG = "DFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.dialogfragment, container,
				false);
		getDialog().setTitle("DialogFragment Tutorial");

		transEmpId = getArguments().getString("empId");
		transOwnId = getArguments().getString("ownerId");

        Spinner reasonSpinner = (Spinner) rootView.findViewById(R.id.spReason);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.reasons, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reasonSpinner.setAdapter(adapter);

        Spinner waySpinner = (Spinner) rootView.findViewById(R.id.spWay);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.way, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waySpinner.setAdapter(adapter1);

		btnSubmitTransInfo=(Button) rootView.findViewById(R.id.addTransSubmitBtn);
		btnSubmitTransInfo.setOnClickListener(new View.OnClickListener() {
			 
            @Override 
            public void onClick(View v) {
                
            	//TODO Validation for valid Data Insertion.
            	


            	transDate = ((EditText)rootView.findViewById(R.id.tranDateEditText)).getText().toString();
            	//transDesc = ((EditText)rootView.findViewById(R.id.spReason)).getText().toString();
            	transAmt = ((EditText)rootView.findViewById(R.id.tranAmtEditText)).getText().toString();
            	
            	//String url = "http://www.forksystems.com/para_app/InitialGateway.php?op=setAllTransactionInfo&tran_emp_id="+transEmpId+"&tran_own_id="+transOwnId+"&tran_amt="+transAmt+"&tran_description="+transDesc+"&tran_date="+transDate+"&tran_status="+transStatus;
            	
            	String url = Uri.parse("http://www.forksystems.com/para_app/InitialGateway.php")
                        .buildUpon()
                        .appendQueryParameter("op","setAllTransactionInfo")
                		.appendQueryParameter("tran_emp_id",transEmpId)
                        .appendQueryParameter("tran_own_id",transOwnId)
                        .appendQueryParameter("tran_amt",transAmt)
                        .appendQueryParameter("tran_description",transDesc)
                        .appendQueryParameter("tran_date",transDate)
						.appendQueryParameter("tran_status","1")
                		.build().toString();

				Log.e("URL@@",url);

				TransactionModel newTransaction = new TransactionModel();
				newTransaction.setTransEmpId(Integer.parseInt(transEmpId));
				newTransaction.setTransOwnerId(Integer.parseInt(transOwnId));
				newTransaction.setAmount(Integer.parseInt(transAmt));
				newTransaction.setTransDescription(transDesc);
				newTransaction.setDate(transDate);
				newTransaction.setTransStatus(1);

                Log.e(TAG, "onClick: " + newTransaction.showAsString());

            	AsyncAddTransactionInfo addInfoTrans = new AsyncAddTransactionInfo(v.getContext(),newTransaction);
            	addInfoTrans.execute(url);
            } 
        }); 
 

		return rootView;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		String selected = parent.getItemAtPosition(pos).toString();
	}

}
