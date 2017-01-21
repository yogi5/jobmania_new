package com.youngindia.jobportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.ui.CompanyPostedjobslist;

import java.util.ArrayList;

/**
 * Created by User on 10/6/2016.
 */
public class CompanyPostedAdapter extends ArrayAdapter<Model_CompanyPostedJobs> {
    CompanyPostedjobslist companyPostedjobslist=new CompanyPostedjobslist();

    ArrayList<Model_CompanyPostedJobs> records =new ArrayList<Model_CompanyPostedJobs>();
    int groupid;
    Context context;
    private int value;
    String str_jobname,str_jobdetails,str_jobqualification,str_joblocation,str_salary,str_experience;

    public CompanyPostedAdapter(Context context, int vg, ArrayList<Model_CompanyPostedJobs>
            records) {

        super(context, vg, records);

        this.context = context;

        groupid = vg;

        this.records = records;
    }



    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        final TextView textName = (TextView) itemView.findViewById(R.id.txt_jobname1);

        textName.setText(records.get(position).getJobname());

        final TextView textCompany = (TextView) itemView.findViewById(R.id.txt_jobdetails1);

        textCompany.setText(records.get(position).getJobdetails());

        final TextView textTechnology = (TextView) itemView.findViewById(R.id.txt_jobskill1);

        textTechnology.setText(records.get(position).getJobqualification());

        TextView txtedit = (TextView) itemView.findViewById(R.id.txt_edit11);

        txtedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"First clicked",Toast.LENGTH_SHORT).show();
                ((CompanyPostedjobslist)context).setEditextMode(position);
                ((CompanyPostedjobslist)context).setpreview(position,true);
            }
        });
        TextView txtpreview = (TextView) itemView.findViewById(R.id.txt_preview);

        txtpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Second clicked",Toast.LENGTH_SHORT).show();
                ((CompanyPostedjobslist)context).setEditextMode(position);
                ((CompanyPostedjobslist)context).setpreview(position,false);
            }
        });
        TextView txtdelete = (TextView) itemView.findViewById(R.id.txt_delete);

        txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Third clicked value:"+textName.getText().toString(),Toast.LENGTH_SHORT).show();
                ((CompanyPostedjobslist)context).calljsontoDelete(position,textName.getText().toString(),textCompany.getText().toString(),textTechnology.getText().toString());
            }
        });

        return itemView;
    }
}
