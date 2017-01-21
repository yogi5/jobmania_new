package com.youngindia.jobportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.youngindia.jobportal.R;

import java.util.ArrayList;

/**
 * Created by User on 9/8/2016.
 */
public class Companyappied_Adapter extends ArrayAdapter<Model_companyappliedcandidate> {

        ArrayList<Model_companyappliedcandidate> records =new ArrayList<Model_companyappliedcandidate>();

        int groupid;
        Context context;

public Companyappied_Adapter(Context context, int vg, ArrayList<Model_companyappliedcandidate>
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

        textName.setText(records.get(position).getJobname());

        TextView textCompany = (TextView) itemView.findViewById(R.id.txt_jobdetails1);

        textCompany.setText(records.get(position).getQualification());

        TextView textTechnology = (TextView) itemView.findViewById(R.id.txt_jobskill1);

        textTechnology.setText(records.get(position).getLocation());

        return itemView;

        }

}
