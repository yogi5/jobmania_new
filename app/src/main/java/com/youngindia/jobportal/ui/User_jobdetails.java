package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.Model_Search;
import com.youngindia.jobportal.adapter.Model_Search_newJObs;
import com.youngindia.jobportal.adapter.NewJob_Adapter;
import com.youngindia.jobportal.adapter.PageAdapter;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class User_jobdetails extends AppCompatActivity {
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter viewPagerAdapter;
    ProgressDialog pDialog,pd;
    Context ctx;
    Model_Search_newJObs p;
    String keyword1;
    String keyword2="";
    Tab1 tab1;
    SessionManager session;
    ListView listview;
    NewJob_Adapter adapter;
    Employee_HomeActivity employee_homeActivity;
    public static ArrayList<Model_Search_newJObs> searchlist_newjob = new ArrayList<Model_Search_newJObs>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_jobdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Jobs");
        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_textColor));
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        setSupportActionBar(toolbar);
        session=new SessionManager(this);
        pDialog=new ProgressDialog(this);
        ctx=this;
        tab1=new Tab1();
        employee_homeActivity=new Employee_HomeActivity();
        HashMap<String, String> user2=session.getstoreedu();
        keyword1= user2.get(SessionManager.KEY_Highereducation1);
        //keyword2 = user2.get(SessionManager.KEY_Highereducation2);
       /* if(keyword1.equals(""))
        {
            keyword1=null;
        }*//*else if(keyword2.equals(""))
        {
            keyword2=null;
        }*/
//        adapter = new NewJob_Adapter(User_jobdetails.this, R.layout.customsearch_layout, searchlist_newjob);
        pDialog.setMessage("Searching ...");
        listview=(ListView)findViewById(R.id.listView);
        /*BackTask bt = new BackTask();
        bt.execute();*/
        pDialog.setMessage("Searching ...");
       /* showDialog();*/
        calljson3();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                Intent intent=new Intent(getApplicationContext(),Employee_HomeActivity.class);
                startActivity(intent);

            }
        });
       /* adapter = new NewJob_Adapter(User_jobdetails.this, R.layout.customsearch_layout, searchlist_newjob);
        listview.setAdapter(adapter);*/

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("New Jobs"));
////        tabLayout.addTab(tabLayout.newTab().setText("Recommended Jobs"));
//        tabLayout.addTab(tabLayout.newTab().setText("Applied Jobs"));
//
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        final PageAdapter adapter = new PageAdapter
//                (getSupportFragmentManager(),tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    private void calljson3() {
        showDialog();
        String url = AppConfig.NEW_JOB+"jobqualification="+keyword1;
        url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    Boolean error=jObj.getBoolean("status");
                    if (!error) {
                        JSONArray valarray=jObj.getJSONArray("result");
                        for (int n = 0; n < valarray.length(); n++) {
                            JSONObject searchData = valarray.getJSONObject(n);
                            p = new Model_Search_newJObs(searchData.getString(AppConfig.KEY_COMPANY_JOBNAME),
                                    searchData.getString(AppConfig.KEY_COMPANY_NAME),
                                    searchData.getString(AppConfig.KEY_POSITION_JOBDETAILS));
                            searchlist_newjob.add(p);
                        }
                        adapter = new NewJob_Adapter(User_jobdetails.this, R.layout.customnewlist, searchlist_newjob);
                        listview.setAdapter(adapter);
                    }
                    else
                    {
                        String errorMsg = jObj.getString("error_msg");

/* Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();*/
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(User_jobdetails.this);
                        builder1.setMessage( errorMsg );
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                hideDialog();
                                Intent intent= new Intent(User_jobdetails.this,Employee_HomeActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder1.show();
                        // finish();
                    }
                } catch (JSONException e) {  e.printStackTrace();  }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();

                        Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
       // Toast.makeText(getApplicationContext(),"sorry server is down",Toast.LENGTH_LONG).show();
        requestQueue.add(stringRequest);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adapter.clear();

    }
    public class BackTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String url = AppConfig.NEW_JOB+"jobqualification="+keyword1;
            url=url.replaceAll(" ", "%20");
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {
                    hideDialog();
                    try {
                        JSONObject jObj = new JSONObject(response);
                        Boolean error=jObj.getBoolean("status");
                        if (!error) {
                            JSONArray valarray=jObj.getJSONArray("result");
                            for (int n = 0; n < valarray.length(); n++) {
                                JSONObject searchData = valarray.getJSONObject(n);
                                p = new Model_Search_newJObs(searchData.getString(AppConfig.KEY_COMPANY_JOBNAME),
                                        searchData.getString(AppConfig.KEY_COMPANY_NAME),
                                        searchData.getString(AppConfig.KEY_POSITION_JOBDETAILS));
                                searchlist_newjob.add(p);
                            }
                        }
                        else
                        {

                            String errorMsg = jObj.getString("error_msg");
/* Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();*/
                            final AlertDialog.Builder builder1 = new AlertDialog.Builder(User_jobdetails.this);


                            builder1.setMessage( errorMsg );
                            builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    hideDialog();
                                    Intent intent= new Intent(User_jobdetails.this,Employee_HomeActivity.class);
                                    startActivity(intent);
                                }
                            });
                            builder1.show();
                            // finish();
                        }
                    } catch (JSONException e) {  e.printStackTrace();  }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            hideDialog();
                            Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
            requestQueue.add(stringRequest);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ctx);

            pd.setTitle("Retrieving data");

            pd.setMessage("Please wait.");

            pd.setCancelable(true);

            pd.setIndeterminate(true);

            pd.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pd!=null) pd.dismiss();
//            Log.e("size", searchlist.size() + "");
//            adapter.notifyDataSetChanged();
        }
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}