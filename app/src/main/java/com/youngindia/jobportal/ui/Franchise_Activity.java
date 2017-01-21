package com.youngindia.jobportal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.ListAdapter;
import com.youngindia.jobportal.adapter.SearchFranchiseAdapter;
import com.youngindia.jobportal.adapter.Search_Adapter;

public class Franchise_Activity extends AppCompatActivity {
ListView listView;
    SearchFranchiseAdapter adapter;
    LoginUser_Activity loginUser_activity;
    FranchiseHome_Activity franchiseHome_activity;
    Toolbar toolbar;
//    public String[]job_name={"Fundraiser", "Game Industry", "Genealogist", "Government Jobs", "Hair Stylist", "Human Resources"};
//    public String[]job_specification={"Android List View", "Adapter implementation", "Simple List View In Android", "Create List View Android","Android Example", "List View Source Code"};
//    public String[]job_skills={"Job views","Job Recommended","New Jobs","Latest Jobs","New Jobs","Latest Jobs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franchise_);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("User Details");
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        loginUser_activity=new LoginUser_Activity();
        setSupportActionBar(toolbar);
        franchiseHome_activity=new FranchiseHome_Activity();
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FranchiseHome_Activity.class));
                finish();
            }
        });
        listView=(ListView)findViewById(R.id.listview_idslist);
        adapter=new SearchFranchiseAdapter(this,R.layout.customfranchisesearch,franchiseHome_activity.searchfranchiseuser);
        listView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        adapter.clear();

        finish();

    }
}
