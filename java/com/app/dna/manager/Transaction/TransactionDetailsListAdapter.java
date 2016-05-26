package com.app.dna.manager.Transaction;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.dna.manager.DFragment;
import com.app.dna.manager.R;
import com.app.dna.manager.SortByDateFragment;
import com.app.dna.manager.TransactionDetailActivity;

import java.util.ArrayList;

/**
 * Created by dhawal on 31/3/16.
 */
public class TransactionDetailsListAdapter extends BaseAdapter {private Context mContext;

    private ArrayList<TransactionModel> mTransactionDetailsList;
    private TransactionDetailActivity activity;

    public TransactionDetailsListAdapter(Context context, ArrayList<TransactionModel> userDetailsList) {
        this.mContext = context;
        this.mTransactionDetailsList = userDetailsList;
        activity = (TransactionDetailActivity)mContext;


    }

    @Override
    public int getCount() {
        if(mTransactionDetailsList == null)
        {
            return 0;
        }
        return mTransactionDetailsList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.transaction_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
            viewHolder.tvUser = (TextView) convertView.findViewById(R.id.tvUser);
            viewHolder.tvReason = (TextView) convertView.findViewById(R.id.tvReason);


            /*convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    FragmentManager fm = activity.getSupportFragmentManager();
                    SortByDateFragment sortByDateFragment = new SortByDateFragment();
                    sortByDateFragment.show(TransactionDetailActivity.getInstance().fm,"Filter fragment");
                    return true;
                }
            });*/

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String date = mTransactionDetailsList.get(position).getDate();
        if (date == null) {
            date = "";
        }
        viewHolder.tvDate.setText(date);

        int amount = mTransactionDetailsList.get(position).getAmount();
        viewHolder.tvAmount.setText(String.valueOf(amount));

        /**
         * Owner name depends on the ownerId. Code is to be added.
         * */

        String name = "Suvrat";
        viewHolder.tvUser.setText(name);

        String reason = mTransactionDetailsList.get(position).getTransDescription();
        if (reason == null) {
            reason = "";
        }
        viewHolder.tvReason.setText(reason);

        return convertView;
    }

    class ViewHolder {
        TextView tvDate, tvAmount, tvUser, tvReason;
    }



}
