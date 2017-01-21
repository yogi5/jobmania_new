package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;
import com.youngindia.jobportal.ui.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DetailsPage_Apply extends AppCompatActivity {
    String Companyname,JobDetails,CompanyLocation,CompanySalaryoffered,Jobqualification,Jobname;
    SessionManager session;
    String username1;
    private static final String TAG = DetailsPage_Apply.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page__apply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Details");
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        final TextView txt=(TextView)findViewById(R.id.txt_name);
        TextView txt1=(TextView)findViewById(R.id.txt_details);
        final TextView txt2=(TextView)findViewById(R.id.txt_skill);
        TextView txt11=(TextView)findViewById(R.id.txt_companyname);
        TextView txt12=(TextView)findViewById(R.id.txt_qualification);
        TextView txt13=(TextView)findViewById(R.id.txt_salary);
        Button btnapply=(Button) findViewById(R.id.btnApply);
        session=new SessionManager(getApplicationContext());
        HashMap<String, String> user3=session.getUsername1();
        username1 = user3.get(SessionManager.KEY_NAME2);
        Intent i = getIntent();
        Companyname=i.getStringExtra("companyname");
        Jobname=i.getStringExtra("jobname");
        JobDetails=i.getStringExtra("jobdetails");
        Jobqualification=i.getStringExtra("jobqualification");
        CompanyLocation=i.getStringExtra("joblocation");
        CompanySalaryoffered=i.getStringExtra("jobsalary");
        if(CompanySalaryoffered.equals("00"))
        {
            CompanySalaryoffered="Not Disclosed.";
        }
        txt.setText( "Job Name:  "+Jobname);
        txt1.setText("Job Details:  "+JobDetails);
        txt2.setText("Job Qualification Required:  "+Jobqualification);
        txt11.setText("Company Location:  "+CompanyLocation);
        txt12.setText("Company Name:  "+Companyname);
        txt13.setText("Salary Offerd:  "+CompanySalaryoffered);
        btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppliedJobsdatainsert(Companyname,JobDetails,CompanyLocation,username1);
            }
        });
    }

    private void AppliedJobsdatainsert(final String companyname, final String companydetails, final String companyLocation, final String username1) {
        String tag_string_req = "req_register";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.APPLIED_JOBS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                try {
                    JSONObject jObj = null;
                    try {
                        jObj = new JSONObject(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(DetailsPage_Apply.this);
                builder1.setMessage("You have successfully applied for this Job.");
                builder1.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        Intent intent1 = new Intent(getApplicationContext(),Employee_HomeActivity.class);
                        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
                        Notification n  = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Notification by Jobportal")
                                .setContentText("You have successfuly Applied for this job!!!!")
                                .setSmallIcon(R.drawable.app_icon)
                                .setContentIntent(pIntent).getNotification();
                        n.flags=Notification.FLAG_AUTO_CANCEL;
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0, n);
                        builder1.setCancelable(true);
                        finish();
                    }
                });
                builder1.show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Sorry server is down.Please try after some time",Toast.LENGTH_SHORT).show();
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("companyname", companydetails);
                params.put("jobname",  companyname);
                params.put("companylocation",companyLocation);
                params.put("username",username1);
                return checkParams(params);
            }
            private Map<String, String> checkParams(Map<String, String> map){
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                    if(pairs.getValue()==null){
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}