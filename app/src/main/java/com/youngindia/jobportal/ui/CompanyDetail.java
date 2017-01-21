package com.youngindia.jobportal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.youngindia.jobportal.R;

import static android.widget.AdapterView.*;

public class CompanyDetail extends AppCompatActivity {
    fragment_companypostjob1 fragment_companypostjob1;
    private Spinner spinner_minExp,spinner_maxExp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Post a Job");
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        setSupportActionBar(toolbar);
        FragmentTransaction fragmenttransaction =getSupportFragmentManager().beginTransaction();
        fragment_companypostjob1=new fragment_companypostjob1();
        fragmenttransaction.replace(R.id.frame_container22, fragment_companypostjob1);
        fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmenttransaction.addToBackStack("tag88").commit();
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Company_Base.class));

            }
        });
//        spinner_minExp = (Spinner) findViewById(R.id.spinner1);
//        spinner_maxExp = (Spinner) findViewById(R.id.spinner1);
//
//        //String[] man_value = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15"};
//
//        ArrayAdapter<CharSequence> spinner_minAdapter = ArrayAdapter
//                .createFromResource(this, R.array.Min_number_arrays, android.R.layout.simple_spinner_item);
//
//        // Specify the layout to use when the list of choices appears
//        spinner_minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//       String[] items = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, items);
//
//        spinner_minExp.setAdapter(adapter);
//
//        spinner_minExp.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                Log.v("item", (String) parent.getItemAtPosition(position));
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//
//        ArrayAdapter<CharSequence> spinner_maxAdapter = ArrayAdapter
//                .createFromResource(this, R.array.number_arrays, android.R.layout.simple_spinner_item);
//
//        // Specify the layout to use when the list of choices appears
//        spinner_maxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, items);
//
//         spinner_maxExp.setAdapter(adapter);
//
//        spinner_maxExp.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                Log.v("item", (String) parent.getItemAtPosition(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//
   }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }

}
