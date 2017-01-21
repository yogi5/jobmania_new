package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.AppliedJobs_Adapter;
import com.youngindia.jobportal.adapter.CompanyPostedAdapter;
import com.youngindia.jobportal.adapter.Model_AppliedJob;
import com.youngindia.jobportal.adapter.Model_CompanyPostedJobs;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;
import com.youngindia.jobportal.ui.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompanyPostedjobslist extends AppCompatActivity {
    SessionManager session;
    ProgressDialog pDialog;
    ListView listview;
    String username,salaryyear11,salary22;
    Model_CompanyPostedJobs p;
    CompanyPostedAdapter adapter;
    Context ctx;
    int value=0;
    int value22=0;
    String jobname,jobdetails,jobskill,str_jobname,str_jobdetails,str_jobqualification,str_joblocation,str_salary,str_experience,exp,
            salaryfirst,salarysecond,salaryedited;
    EditText edt_jobname,edt_jobdetails,edt_jobqualification,edt_location,edt_exp,edt_salary;
    RelativeLayout linearLayout_preview;
    Spinner spinner_year,spinner_month;
    Button btnok;
    Dialog dialog_preview;
    private  final String TAG = CompanyPostedjobslist.class.getSimpleName();
    public static ArrayList<Model_CompanyPostedJobs> searchlist_appliedjob = new ArrayList<Model_CompanyPostedJobs>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_postedjobslist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Posted Jobs");
        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_textColor));
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        session = new SessionManager(this);
        HashMap<String, String> user1=session.getUsername();
        username = user1.get(SessionManager.KEY_NAME);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Searching ...");
        calljson();
        listview=(ListView)findViewById(R.id.companypostlist);
        dialog_preview=new Dialog(CompanyPostedjobslist.this);
        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_preview.setContentView(R.layout.dialog_preview);
        btnok=(Button) dialog_preview.findViewById(R.id.btn_nxt);
        edt_jobname=(EditText)dialog_preview.findViewById(R.id.edt_job);
        edt_jobdetails=(EditText)dialog_preview.findViewById(R.id.edt_jobDetails);
        edt_jobqualification=(EditText)dialog_preview.findViewById(R.id.edt_keySkills);
        edt_location=(EditText)dialog_preview.findViewById(R.id.lin1);
        spinner_year=(Spinner) dialog_preview.findViewById(R.id.salaryyear);
        spinner_month=(Spinner) dialog_preview.findViewById(R.id.salaryyear1);
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                LinearLayout linearLayoutParent = (LinearLayout) view;
//                LinearLayout linearLayoutChild = (LinearLayout ) linearLayoutParent.getChildAt(1);
//                final TextView tvedit = (TextView) linearLayoutChild.getChildAt(0);
//                final TextView tvpreview = (TextView) linearLayoutChild.getChildAt(1);
//                final TextView tvdelete = (TextView) linearLayoutChild.getChildAt(2);
//                jobname = ((TextView) view.findViewById(R.id.txt_jobname1)).getText().toString();
//                jobdetails = ((TextView) view.findViewById(R.id.txt_jobdetails1)).getText().toString();
//                jobskill = ((TextView) view.findViewById(R.id.txt_jobskill1)).getText().toString();
//                value=position;
//                str_jobname=searchlist_appliedjob.get(value).getJobname();
//                str_jobdetails=searchlist_appliedjob.get(value).getJobdetails();
//                str_jobqualification=searchlist_appliedjob.get(value).getJobqualification();
//                str_joblocation=searchlist_appliedjob.get(value).getLocation();
//                str_salary=searchlist_appliedjob.get(value).getSalary();
//                str_experience=searchlist_appliedjob.get(value).getExp();
//
//                tvedit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getBaseContext(), tvedit.getText().toString(), Toast.LENGTH_SHORT).show();
//                        final Dialog dialog_preview=new Dialog(CompanyPostedjobslist.this);
//                        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog_preview.setContentView(R.layout.dialog_preview);
//                        btnok=(Button) dialog_preview.findViewById(R.id.btn_nxt);
//                        edt_jobname=(EditText)dialog_preview.findViewById(R.id.edt_job);
//                        edt_jobdetails=(EditText)dialog_preview.findViewById(R.id.edt_jobDetails);
//                        edt_jobqualification=(EditText)dialog_preview.findViewById(R.id.edt_keySkills);
//                        edt_location=(EditText)dialog_preview.findViewById(R.id.lin1);
//                        setEditextMode(true);
//                        edt_jobname.setText(str_jobname);
//                        edt_jobdetails.setText(str_jobdetails);
//                        edt_jobqualification.setText(str_jobqualification);
//                        edt_location.setText(str_joblocation);
//                        spinner_year=(Spinner) dialog_preview.findViewById(R.id.salaryyear);
//                        spinner_month=(Spinner) dialog_preview.findViewById(R.id.salaryyear1);
//                        salaryfirst=spinner_year.getSelectedItem().toString().trim();
//                        salarysecond=spinner_month.getSelectedItem().toString().trim();
//                        salaryedited=salaryfirst.concat(salarysecond);
//                        salaryyear11=str_salary.substring(0, Math.min(str_salary.length(), 4));
//                        salary22=salaryyear11.toUpperCase();
//                        String[] ap=getResources().getStringArray(R.array.salary);
//                        for(int i = 0; i < ap.length; i++){
//                            if(spinner_year.getItemAtPosition(i).toString().equals(salary22)){
//                                spinner_year.setSelection(i);
//                                break;
//                            }
//                        }
//                        for(int i=0;i<ap.length;i++) {
//                            if (ap.equals(salary22))
//                            {
//                                spinner_year.setSelection(i);
//                            }
//                        }
//                        spinner_year.setSelection(value22);
//                        spinner_month=(Spinner) dialog_preview.findViewById(R.id.salaryyear1);
//                        dialog_preview.show();
//                        btnok.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                exp="fresher";
//                                dialog_preview.dismiss();
//                                jobname=edt_jobname.toString().trim();
//                                //calljsontoUpdate(edt_jobname.toString().trim(),edt_jobdetails.toString().trim(),edt_jobqualification.toString().trim(),username,edt_location.toString().trim(),salaryedited,exp);
//                            }
//                        });
//                        dialog_preview.setCancelable(true);
//                    }
//                });
//                tvpreview.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        final Dialog dialog_preview=new Dialog(CompanyPostedjobslist.this);
//                        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog_preview.setContentView(R.layout.dialog_preview);
//                        btnok=(Button) dialog_preview.findViewById(R.id.btn_nxt);
//                        edt_jobname=(EditText)dialog_preview.findViewById(R.id.edt_job);
//                        edt_jobdetails=(EditText)dialog_preview.findViewById(R.id.edt_jobDetails);
//                        edt_jobqualification=(EditText)dialog_preview.findViewById(R.id.edt_keySkills);
//                        edt_location=(EditText)dialog_preview.findViewById(R.id.lin1);
//                        setEditextMode(false);
//                        edt_jobname.setText(str_jobname);
//                        edt_jobdetails.setText(str_jobdetails);
//                        edt_jobqualification.setText(str_jobqualification);
//                        edt_location.setText(str_joblocation);
//                        spinner_year=(Spinner) dialog_preview.findViewById(R.id.salaryyear);
//                        salaryyear11=str_salary.substring(0, Math.min(str_salary.length(), 4));
//                        String[] ap=getResources().getStringArray(R.array.salary);
//                        for(int i = 0; i < ap.length; i++){
//                            if(spinner_year.getItemAtPosition(i).toString().equals(salaryyear11)){
//                                spinner_year.setSelection(i);
//                                break;
//                            }
//                        }
//                        spinner_month=(Spinner) dialog_preview.findViewById(R.id.salaryyear1);
//                        salary22=str_salary.substring(4,Math.min(str_salary.length(), 15));
//                        String[] ap1=getResources().getStringArray(R.array.salary1);
//                        for(int i = 0; i < ap1.length; i++){
//                            if(spinner_month.getItemAtPosition(i).toString().equals(salary22)){
//                                spinner_month.setSelection(i);
//                                break;
//                            }
//                        }
//                        dialog_preview.show();
//                        btnok.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog_preview.dismiss();
//                            }
//                        });
//                        dialog_preview.setCancelable(true);
//                    }
//                });
//                tvdelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getBaseContext(),"Deleted", Toast.LENGTH_SHORT).show();
//                        calljsontoDelete(value);
//                    }
//                });
//            }
//        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Company_Base.class);
                startActivity(intent);
                if(!searchlist_appliedjob.isEmpty())

                    adapter.clear();
                finish();
            }
        });
    }

    private void calljsontoUpdate(final String s1, final String trim1, final String s, final String username, final String trim, final String salaryedited, final String exp) {
        String tag_string_req = "req_register";
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_COMPANYPOST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = null;
                    try {
                        jObj = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                        builder1.setMessage( "You successfully posted a job !!!!");
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent intent= new Intent(getApplicationContext(),Company_Base.class);
                                startActivity(intent);
                            }
                        });
                        builder1.show();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        final String valueserver=jObj.getString("value");
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                        builder1.setMessage(errorMsg);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Toast.makeText(getApplicationContext(),"Please try again!!!",Toast.LENGTH_SHORT).show();
                            }
                        }); builder1.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("jobname", s1);
                params.put("jobdetails",trim1);
                params.put("jobqualification",s);
                params.put("companyname",username);
                params.put("salary",salaryedited);
                params.put("location",trim);
                params.put("exp",exp);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public void setEditextMode(int value) {
//        edt_jobname.setFocusable(b);
//        edt_jobdetails.setFocusable(b);
//        edt_jobqualification.setFocusable(b);
//        edt_location.setFocusable(b);
//        spinner_year.setEnabled(b);
//        spinner_month.setEnabled(b);
        str_jobname=searchlist_appliedjob.get(value).getJobname();
        str_jobdetails=searchlist_appliedjob.get(value).getJobdetails();
        str_jobqualification=searchlist_appliedjob.get(value).getJobqualification();
        str_joblocation=searchlist_appliedjob.get(value).getLocation();
        str_salary=searchlist_appliedjob.get(value).getSalary();
        str_experience=searchlist_appliedjob.get(value).getExp();
        edt_jobname.setText(str_jobname);
        edt_jobdetails.setText(str_jobdetails);
        edt_jobqualification.setText(str_jobqualification);
        edt_location.setText(str_joblocation);

    }

    public void setpreview(int value,boolean b)
    {
        str_jobname=searchlist_appliedjob.get(value).getJobname();
        str_jobdetails=searchlist_appliedjob.get(value).getJobdetails();
        str_jobqualification=searchlist_appliedjob.get(value).getJobqualification();
        str_joblocation=searchlist_appliedjob.get(value).getLocation();
        str_salary=searchlist_appliedjob.get(value).getSalary();
        str_experience=searchlist_appliedjob.get(value).getExp();
        edt_jobname.setFocusable(b);
        edt_jobdetails.setFocusable(b);
        edt_jobqualification.setFocusable(b);
        edt_location.setFocusable(b);
                    if(!str_salary.isEmpty() && !str_salary.equals(null) && !str_salary.equals("00")) {
                        salaryyear11 = str_salary.substring(0, Math.min(str_salary.length(), 4));
                        String[] ap = getResources().getStringArray(R.array.salary);
                        for (int i = 0; i < ap.length; i++) {
                            if (spinner_year.getItemAtPosition(i).toString().equals(salaryyear11)) {
                                spinner_year.setSelection(i);
                                break;
                            }
                        }
                        salary22 = str_salary.substring(4, Math.min(str_salary.length(), 15));
                        String[] ap1 = getResources().getStringArray(R.array.salary1);
                        for (int i = 0; i < ap1.length; i++) {
                            if (spinner_month.getItemAtPosition(i).toString().equals(salary22)) {
                                spinner_month.setSelection(i);
                                break;
                            }
                        }
                    }else {
                        spinner_year.setSelection(0);
                        spinner_month.setSelection(0);
                    }
                        dialog_preview.show();
                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog_preview.dismiss();
                            }
                        });
        dialog_preview.setCancelable(true);

    }

    public void calljsontoDelete(final int value, String string, String toString, String s) {
        setEditextMode(value);
        showDialog();
        String url = AppConfig.COMPANYDELETEPOST +"jobname="+string
                +"&jobdetails="+toString
                +"&jobqualification="+ s
                +"&companyname="+username;
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
                        Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
                        searchlist_appliedjob.remove(value);
                        adapter.notifyDataSetChanged();
                        onRestart1();
//                        public void refresh(View view){          //refresh is onClick name given to the button
//                            onRestart();
//                        }
//
//                        @Override
//                        protected void onRestart() {
//
//                            // TODO Auto-generated method stub
//                            super.onRestart();
//                            Intent i = new Intent(lala.this, lala.class);  //your class
//                            startActivity(i);
//                            finish();
//
//                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"not Successfully",Toast.LENGTH_SHORT).show();
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
        // Toast.makeText(getApplicationContext(),"sorry server is down",Toast.LENGTH_LONG).show();
        requestQueue.add(stringRequest);
    }

                        protected void onRestart1() {

                            // TODO Auto-generated method stub
                            super.onRestart();
                            Intent i = new Intent(CompanyPostedjobslist.this, CompanyPostedjobslist.class);  //your class
                            startActivity(i);
                            finish();

                        }

    private void calljson() {
        showDialog();
        String approve="true";
        String url = AppConfig.COMPANYPOSTEDJOBS+"companyname="+username
                +"&approve="+approve;

        url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    Boolean error=jObj.getBoolean("status");
                    //String approve=jObj.getString("approve");

                    if (!error) {
                        JSONArray valarray=jObj.getJSONArray("result");
                        for (int n = 0; n < valarray.length(); n++) {
                            JSONObject searchData = valarray.getJSONObject(n);
                            p = new Model_CompanyPostedJobs(searchData.getString(AppConfig.KEY_COMPANY_JOBNAME),
                                    searchData.getString("jobdetails"),
                                    searchData.getString("jobqualification"),
                                    searchData.getString("location"),
                                    searchData.getString("salary"),
                                    searchData.getString("exp"));
                            searchlist_appliedjob.add(p);
                        }
                        adapter = new CompanyPostedAdapter(CompanyPostedjobslist.this, R.layout.customcompanyposted, searchlist_appliedjob);
                        listview.setAdapter(adapter);
                    }
                    else
                    {
                        String errorMsg = jObj.getString("error_msg");

/* Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();*/
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(CompanyPostedjobslist.this);
                        builder1.setMessage( errorMsg );
                        builder1.setCancelable(false);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                hideDialog();
                                Intent intent= new Intent(CompanyPostedjobslist.this,Company_Base.class);
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
        // Toast.makeText(getApplicationContext(),"sorry server is down",Toast.LENGTH_LONG).show();
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
        if(adapter!=null) {
            adapter.clear();
        }
    }

}
