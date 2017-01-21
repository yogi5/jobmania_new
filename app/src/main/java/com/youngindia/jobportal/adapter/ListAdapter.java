package com.youngindia.jobportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngindia.jobportal.R;

import java.sql.Array;
import java.util.ArrayList;


public class ListAdapter extends BaseAdapter {
    private Context mContext;
    public String[]jobname;
    public String[]specification;
    public String[]skills;

    public ListAdapter(Context mContext, String[] jobname, String[] specification, String[] skills) {
        this.mContext = mContext;
        this.jobname = jobname;
        this.specification = specification;
        this.skills = skills;
    }

    @Override
    public int getCount() {
        return jobname.length;
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
        View view;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            view = new View(mContext);
            view = inflater.inflate(R.layout.custom_listcell, null);
            TextView textView = (TextView) view.findViewById(R.id.txt_jobname);
            TextView textView1 = (TextView) view.findViewById(R.id.txt_jobdetails);
            TextView textView2 = (TextView) view.findViewById(R.id.txt_jobskill);
            textView.setText(jobname[position]);
            textView1.setText(specification[position]);
            textView2.setText(skills[position]);

        } else {
            view = (View) convertView;
        }

        return view;
    }
}
