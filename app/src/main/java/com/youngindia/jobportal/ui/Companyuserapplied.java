package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.Companyappied_Adapter;
import com.youngindia.jobportal.adapter.Model_companyappliedcandidate;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Companyuserapplied extends AppCompatActivity {
Context ctx;
ProgressDialog pDialog;
ListView listview;
    Companyappied_Adapter adapter;
    Model_companyappliedcandidate p;
String companyusername;
public static ArrayList<Model_companyappliedcandidate> applied_candidate = new ArrayList<Model_companyappliedcandidate>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyuserapplied);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Candidates");
        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_textColor));
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        setSupportActionBar(toolbar);
        ctx=this;
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Searching ...");
        listview=(ListView)findViewById(R.id.jobappliedcandidate);
        Intent intent = getIntent();
        companyusername = intent.getStringExtra("companyname");
        calljson();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                finish();
            }
        });
    }

    private void calljson() {
        showDialog();
        String url = AppConfig.COMPANYUSERAPPLIED+"companyusername="+"Mask Technology";
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
                            p = new Model_companyappliedcandidate(searchData.getString("username"),
                                    searchData.getString("jobname"),
                                    searchData.getString("companylocation"));
                            applied_candidate.add(p);
                        }
                        adapter = new Companyappied_Adapter(Companyuserapplied.this, R.layout.customsearch_layout,applied_candidate);
                        listview.setAdapter(adapter);
                    }
                    else
                    {
                        String errorMsg = jObj.getString("error_msg");
/* Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();*/
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(Companyuserapplied.this);
                        builder1.setMessage( errorMsg );
                        builder1.setCancelable(false);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                hideDialog();
                                Intent intent= new Intent(Companyuserapplied.this,Company_Base.class);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!applied_candidate.isEmpty())
        adapter.clear();
        finish();
    }
}
