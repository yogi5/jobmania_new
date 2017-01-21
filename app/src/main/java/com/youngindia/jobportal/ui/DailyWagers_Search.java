package com.youngindia.jobportal.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.ListAdapter;
import com.youngindia.jobportal.adapter.Model_Search;
import com.youngindia.jobportal.adapter.SearchDaily_Adapter;
import com.youngindia.jobportal.adapter.Search_Adapter;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DailyWagers_Search extends AppCompatActivity {
    frgmentd_dailywages_search frgmentd_dailywages_search;
    ListAdapter listAdapter;
    SearchDaily_Adapter adapter;
    ImageView fitersearch;
    Toolbar toolbar;
    SessionManager session;
    public String[]job_name={"Plamber", "Tailor", "Barber", "Cobbler", "Hair Stylist", "Designer"};
    public String[]job_wages={"Rs.1000", "Rs. 2000", "Rs. 3000", "Rs 4000","Rs.5000", "Rs.6000"};
    public String[]job_hr={"1 hr","2 hr","3 hr","4 hr","5 hr","6 hr","7 hr","8 hr"};

    ListView listView_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list_);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Daily wagers");
        setSupportActionBar(toolbar);
        /*getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Daily Wager");*/

        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        setSupportActionBar(toolbar);
        session=new SessionManager(getApplicationContext());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                Intent intent =new Intent(DailyWagers_Search.this,DailyWagesRegstr.class);
                startActivity(intent);
            }
        });
        fitersearch=(ImageView)findViewById(R.id.filteroption);
        fitersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setdailyfilter(true);
                adapter.clear();
                Intent intent =new Intent(DailyWagers_Search.this,DailyWagesRegstr.class);
                startActivity(intent);
            }
        });

        frgmentd_dailywages_search=new frgmentd_dailywages_search();
        listView_search= (ListView) findViewById(R.id.listView_serachitems);
        adapter=new SearchDaily_Adapter(this,R.layout.customsearch_daily,frgmentd_dailywages_search.searchlist_daily);
        listView_search.setAdapter(adapter);
//        listView_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i=new Intent(DailyWagers_Search.this, Details_Page.class);
////                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(i);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adapter.clear();
        Intent intent=new Intent(DailyWagers_Search.this,DailyWagesRegstr.class);
        startActivity(intent);
    }
}
