package com.app.dna.manager;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.app.dna.manager.Employee.EmployeeModel;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private UserListAdapter userListAdapter;

    private String nameList[];

    private boolean statusList[];

    private ListView userDetailsListView;

    private ArrayList<EmployeeModel> empList = new ArrayList<>();

    private EditText searchUserEditText = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchUserEditText = (EditText) findViewById(R.id.showSearchEditText);

        userDetailsListView = (ListView)findViewById(R.id.lvUserList);
        empList = ActivitySplash.passList();

        Log.e("EMP",""+empList.size());
        setUserList();

        //Floating Button search.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.listViewSearch);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                //TODO show the EditText to search from the listView
                searchUserEditText.setVisibility(View.VISIBLE);
            }
        });

        //Adding listener for TextChange

        searchUserEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                userListAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void setUserList()
    {
        userListAdapter = new UserListAdapter(MainActivity.this, empList);
        userDetailsListView.setAdapter(userListAdapter);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
