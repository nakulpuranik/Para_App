package com.app.dna.manager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.app.dna.manager.Employee.EmployeeModel;

import java.util.ArrayList;

/**
 * Created by dhawal on 21/11/15.
 */
public class UserListAdapter extends BaseAdapter implements Filterable {
    private Context mContext;

    private ArrayList<EmployeeModel> mOriginalValues; // Original Values
    private ArrayList<EmployeeModel> mUserDetailsList; // Values to be displayed
    private static final String TAG = "UserListAdapter";
    private String LOG_TAG = UserListAdapter.class.getSimpleName();

    public UserListAdapter(Context context, ArrayList<EmployeeModel> userDetailsList) {
        this.mContext = context;
        this.mUserDetailsList = userDetailsList;
    }

    @Override
    public int getCount() {
        return mUserDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.user_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.tvUserName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try
                    {
                        Intent intent = new Intent(mContext,TransactionDetailActivity.class);
                        intent.putExtra("userName", mUserDetailsList.get(position).getEmpName());
                        intent.putExtra("userId",mUserDetailsList.get(position).getEmpId());
                        intent.putExtra("salary", mUserDetailsList.get(position).getEmpSalary());
                        mContext.startActivity(intent);
                        Log.e(TAG, "Salary "+ mUserDetailsList.get(position).getEmpSalary());
                    }catch(Exception ex)
                    {
                        Log.e(LOG_TAG,"Adding IntentExtraUserListAdapter",ex);
                    }

                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String userName = mUserDetailsList.get(position).getEmpName();
        if (userName == null) {
            userName = "";
        }
        viewHolder.tvUserName.setText(userName);

        return convertView;

     }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mUserDetailsList = (ArrayList<EmployeeModel>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<EmployeeModel> FilteredArrList = new ArrayList<EmployeeModel>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<EmployeeModel>(mUserDetailsList); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getEmpName();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new EmployeeModel(mOriginalValues.get(i).getEmpName()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    class ViewHolder {
        TextView tvUserName;
    }

    }
