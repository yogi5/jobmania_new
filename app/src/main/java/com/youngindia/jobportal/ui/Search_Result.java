package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.ListAdapter;
import com.youngindia.jobportal.adapter.Model_Search;
import com.youngindia.jobportal.adapter.Search_Adapter;
import com.youngindia.jobportal.adapter.Search_Adapteruser;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_Result extends AppCompatActivity {
    private ProgressDialog loading,pd;
    String keyword,Qualification,location,salary;
    Search_Adapteruser adapter;
    ListView search_list;
    Context context;
    Search_Activity search_activity;
    private int m_counter = 0;
    private int _xDelta,deviceWidth,deviceHeight;
    private int _yDelta;
    ImageView fitersearch;
    ViewGroup mRrootLayout;
    RelativeLayout.LayoutParams layoutParams;
    SessionManager session;
    String str_jobname1,str_jobname, str_jobdetails, str_jobqualification,str_joblocation, str_salary,str_companyname;
    float m_lastTouchX, m_lastTouchY, m_posX, m_posY, m_prevX, m_prevY, m_imgXB, m_imgYB, m_imgXC, m_imgYC, m_dx, m_dy;

    // BackTask bt;
    public static ArrayList<Model_Search> searchlist = new ArrayList<Model_Search>();
    //  Model_Search p;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adapter.clear();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search__result);
        mRrootLayout = (ViewGroup)findViewById(R.id.relativelayout1);
        //final FloatingActionButton fb=(FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        toolbar.setTitle("Company's List");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                finish();
            }
        });
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels ;
        layoutParams = new RelativeLayout.LayoutParams(100, 100);
        layoutParams.leftMargin=deviceWidth-150;
        layoutParams.topMargin=deviceHeight-400;
        layoutParams.rightMargin=10;
        layoutParams.bottomMargin=10;
        //fb.setLayoutParams(layoutParams);
        session=new SessionManager(getApplicationContext());
        fitersearch=(ImageView)findViewById(R.id.filteroption);
        fitersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setfilter(true);
                adapter.clear();
                Intent intent=new Intent(Search_Result.this,Search_Activity.class);
                startActivity(intent);
            }
        });
        search_activity= new Search_Activity();
        context=this;
        search_list=(ListView)findViewById(R.id.listView2);
     /*   fb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(9000);
                                    }
                                } catch (InterruptedException ex) {
                                }

                                // TODO
                            }
                        };

                        thread.start();
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(Search_Result.this);
                        builder1.setMessage("You have successfully applied for this Job.");
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                builder1.setCancelable(true);
                              *//*  Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
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
                                builder1.setCancelable(true);*//*
                            }
                        });
                        builder1.show();
                        layoutParams.leftMargin = deviceWidth - 150;
                        layoutParams.topMargin = deviceHeight - 400;
                        layoutParams.rightMargin = 10;
                        layoutParams.bottomMargin = 10;
                        fb.setLayoutParams(layoutParams);
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
                                .getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = -250;
                        layoutParams.bottomMargin = -250;
                        v.setLayoutParams(layoutParams);
                        break;
                }
                mRrootLayout.invalidate();
                return true;
            }
        });*/

        adapter=new Search_Adapteruser(this,R.layout.customsearch_layout,search_activity.searchlist);
        search_list.setAdapter(adapter);
        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_jobname1=search_activity.searchlist.get(position).getJobname();
                str_jobdetails=search_activity.searchlist.get(position).getJobdetails();
                str_jobqualification=search_activity.searchlist.get(position).getQualification();
                str_joblocation=search_activity.searchlist.get(position).getLocation();
                str_salary=search_activity.searchlist.get(position).getSalary();
                str_companyname=search_activity.searchlist.get(position).getCompanyname();
                String jobname = ((TextView) view.findViewById(R.id.txt_jobname1)).getText().toString();
                String jobdetails = ((TextView) view.findViewById(R.id.txt_jobdetails1)).getText().toString();
                String jobskill = ((TextView) view.findViewById(R.id.txt_jobskill1)).getText().toString();
                Intent intent=new Intent(Search_Result.this,DetailsPage_Apply.class);
                Bundle b = new Bundle();
                b.putString("jobname", str_jobname1);
                b.putString("jobdetails", str_jobdetails);
                b.putString("jobqualification", str_jobqualification);
                b.putString("joblocation", str_joblocation);
                b.putString("jobsalary", str_salary);
                b.putString("companyname", str_companyname);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}