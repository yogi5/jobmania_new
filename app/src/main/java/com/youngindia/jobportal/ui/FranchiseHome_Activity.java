package com.youngindia.jobportal.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.Model_SearchFranchise;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FranchiseHome_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
fragment_franchisebase franchisebase;
    String data;
    String data1;
    Model_SearchFranchise p;
    Toolbar toolbar;
    public static ArrayList<Model_SearchFranchise> searchfranchiseuser = new ArrayList<Model_SearchFranchise>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franchise_home_);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Intent intent = getIntent();
        data = intent.getStringExtra("keyName");
        data1 = intent.getStringExtra("keyName1");
        franchisebase=new fragment_franchisebase();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(R.id.frame_container, franchisebase);
        fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmenttransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_setting) {


        } /*else if (id == R.id.nav_newjob) {

        } else if (id == R.id.nav_recommendedjob) {

        }*/ else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_promote) {

        }else if (id == R.id.logout) {
            Intent intent = new Intent(FranchiseHome_Activity.this,LoginUser_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

        public void calljson2(String franchisename) {
        String url = AppConfig.URL_FRANCHISE+"franchisename="+franchisename;
            url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
//                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    Boolean error=jObj.getBoolean("status");
                    if (!error) {
                        JSONArray valarray=jObj.getJSONArray("result");
                        for (int n = 0; n < valarray.length(); n++) {
                            JSONObject searchData = valarray.getJSONObject(n);
                            p = new Model_SearchFranchise(searchData.getString(AppConfig.KEY_NAME),
                                    searchData.getString(AppConfig.KEY_USERNAME),
                                    searchData.getString(AppConfig.KEY_MOBILE),searchData.getString(AppConfig.KEY_LOGINSTATUS));
                            searchfranchiseuser.add(p);
                        }
                        Intent intent = new Intent(getApplicationContext(), Franchise_Activity.class);
                        startActivity(intent);
                    }
                    else
                    {

                        String errorMsg = jObj.getString("error_msg");
                     //   Toast.makeText(getApplicationContext(),"errorMsg:"+errorMsg,Toast.LENGTH_LONG).show();
                        // finish();
                    }
                } catch (JSONException e) {  e.printStackTrace();  }
                //adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        hideDialog();
            //            Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
