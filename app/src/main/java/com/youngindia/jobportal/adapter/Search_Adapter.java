package com.youngindia.jobportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.youngindia.jobportal.R;

import java.util.ArrayList;
import java.util.List;


public class Search_Adapter extends ArrayAdapter<Model_Search> {

    ArrayList<Model_Search> records =new ArrayList<Model_Search>();

    int groupid;
    Context context;

    public Search_Adapter(Context context, int vg, ArrayList<Model_Search>
            records) {

        super(context, vg, records);

        this.context = context;

        groupid = vg;

        this.records = records;



    }



    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        TextView textName = (TextView) itemView.findViewById(R.id.txt_jobname1);

        textName.setText(records.get(position).getName());

        TextView textCompany = (TextView) itemView.findViewById(R.id.txt_jobdetails1);

        textCompany.setText(records.get(position).getMobile());

        TextView textTechnology = (TextView) itemView.findViewById(R.id.txt_jobskill1);

        textTechnology.setText(records.get(position).getEmail());

        return itemView;

    }

}

