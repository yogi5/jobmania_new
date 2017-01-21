package com.youngindia.jobportal.ui;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class Employee_HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Integer[] mThumbIds = {
            R.drawable.job_status, R.drawable.job_recommended,
            R.drawable.job_update, R.drawable.job_find,
    };
    ProgressBar pb;
    TextView tv;
    int prg = 0;
    String path;
    public String[]jobtype={"Job Search","Job Recommended","New Jobs","Applied Jobs"};
    fragment_base fragment_detail;
    fragment_profile fragment_profile;
    fragment_faq fragment_faq;
    fragment_newjob fragment_newjob;
    fragment_inbox fragment_inbox;
    fragment_recommendedjob fragment_recommendedjob;
    frgmnt_setting frgmnt_setting;
    public static Employee_HomeActivity instance;
    SessionManager sessionManager;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_employee__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        sessionManager=new SessionManager(this);
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        setSupportActionBar(toolbar);
        fragment_detail =new fragment_base();
        fragment_profile=new fragment_profile();
        fragment_faq=new fragment_faq();

        fragment_newjob=new fragment_newjob();
        fragment_inbox=new fragment_inbox();
        frgmnt_setting=new frgmnt_setting();
        fragment_recommendedjob=new fragment_recommendedjob();

        HashMap<String, String> user2=sessionManager.getstoreedu();
        String qualification = user2.get(SessionManager.KEY_Highereducation1);
        //checknewjobdata(qualification);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragment_detail = new fragment_base();
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        //   sScreen = getResources().getString(R.string.title_wall);
        fragmenttransaction.replace(R.id.frame_container, fragment_detail);
        fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmenttransaction.commit();



    }
    public static Employee_HomeActivity getInstance() {
        return instance;
    }

    @Override
    public void onBackPressed() {
        sessionManager.setLogin(false);
        Intent i=new Intent(Employee_HomeActivity.this,LoginUser_Activity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("EXIT", true);
        startActivity(i);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employee__home, menu);
        return true;
    }*/
//   protected void onStart(){
//
//       super.onStart();
//           if(count==0)
//           {
//               Intent i1=new Intent(Employee_HomeActivity.this,BannerActivity.class);
//               startActivity(i1);
//               count++;
//           }
//           else
//           {
//               Intent i2=new Intent(Employee_HomeActivity.this,Employee_HomeActivity.class);
//               startActivity(i2);
//           }
//
//       //Toast.makeText(MainActivity.this,"inside onStart() method", Toast.LENGTH_SHORT).show();
//   //Intent i=new Intent(Employee_HomeActivity.this,BannerActivity.class);
//       //startActivity(i);
//   }

    private void checknewjobdata(String highereducation) { 
        String url = AppConfig.NOTIFICATIONTEST+"jobqualification="+highereducation;
        url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jObj = new JSONObject(response);
                    String error=jObj.getString("notify");
                    if(error.equals("valid"))
                    {
                        Intent alarmIntent = new Intent(Employee_HomeActivity.this, AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        Calendar firingCal= Calendar.getInstance();
                        Calendar currentCal = Calendar.getInstance();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, 12);
                        calendar.set(Calendar.MINUTE, 35);
                        calendar.set(Calendar.SECOND, 00);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);

                        /*Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        Intent intent1 = new Intent(LoginUser_Activity.this,User_jobdetails.class);
                        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        PendingIntent pendingIntent = PendingIntent.getService(LoginUser_Activity.this, 0, intent1, 0);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, 17);
                        calendar.set(Calendar.MINUTE, 18);
                        calendar.set(Calendar.SECOND, 00);

                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000 , pendingIntent);  //set repeating every 24 h
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
                        Notification n  = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Notification by Jobportal")
                                .setContentText("jobs recommend for you!!!!")
                                .setSmallIcon(R.drawable.app_icon)
                                .setContentIntent(pendingIntent).getNotification();
                        n.flags=Notification.FLAG_AUTO_CANCEL;
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0, n);*/
                     /*   final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                r.play();
                                Intent intent1 = new Intent(LoginUser_Activity.this,User_jobdetails.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
                                Notification n  = new Notification.Builder(getApplicationContext())
                                        .setContentTitle("Notification by Jobportal")
                                        .setContentText("jobs recommend for you!!!!")
                                        .setSmallIcon(R.drawable.app_icon)
                                        .setContentIntent(pIntent).getNotification();
                                n.flags=Notification.FLAG_AUTO_CANCEL;
                                NotificationManager notificationManager =
                                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                notificationManager.notify(0, n);

                            }
                        }, 10000);*/

                    }
                    else
                    {
                        String errorMsg = jObj.getString("error_msg");
                    }
                } catch (JSONException e) {  e.printStackTrace();  }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }



    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //   sScreen = getResources().getString(R.string.title_wall);
                ft.replace(R.id.frame_container, fragment_detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack("tag").commitAllowingStateLoss();//commit();
            // Handle the camera action
        } /*else if (id == R.id.nav_setting) {
            FragmentTransaction ft1= getSupportFragmentManager().beginTransaction();
            //   sScreen = getResources().getString(R.string.title_wall);
            ft1.replace(R.id.frame_container, frgmnt_setting);
            ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft1.addToBackStack("tag").commitAllowingStateLoss();//commit();

        }*/
        else if (id == R.id.nav_inbox) {
           /* FragmentTransaction ft1= getSupportFragmentManager().beginTransaction();
            //   sScreen = getResources().getString(R.string.title_wall);
            ft1.replace(R.id.frame_container, frgmnt_setting);
            ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft1.addToBackStack("tag").commitAllowingStateLoss();//commit();*/
            Intent intent=new Intent(getApplicationContext(),Employee_RegistrationActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_changepassword) {
           /* FragmentTransaction ft1= getSupportFragmentManager().beginTransaction();
            //   sScreen = getResources().getString(R.string.title_wall);
            ft1.replace(R.id.frame_container, frgmnt_setting);
            ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft1.addToBackStack("tag").commitAllowingStateLoss();//commit();*/
            Intent intent=new Intent(getApplicationContext(),Employee_HomeActivity_ChangePassword.class);
            startActivity(intent);

        }/*else if (id == R.id.nav_newjob) {
            Intent intent =new Intent(Employee_HomeActivity.this,User_jobdetails.class);
            startActivity(intent);
        } else if (id == R.id.nav_recommendedjob) {
            Intent intent =new Intent(Employee_HomeActivity.this,User_jobdetails.class);
            startActivity(intent);
        }*/ else if (id == R.id.nav_faq) {
            Intent intent =new Intent(Employee_HomeActivity.this,App_Faq.class);
            startActivity(intent);
        } else if (id == R.id.nav_promote) {
            shareAppurl(path);

        }else if (id == R.id.logout) {
//            Intent intent =new Intent(Employee_HomeActivity.this,LoginUser_Activity.class);
//            startActivity(intent);
            sessionManager.setLogin(false);
            Intent intent = new Intent(Employee_HomeActivity.this,LoginUser_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void shareAppurl(String path){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Shared App details");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.youngindia.jobportal&hl=en");
        startActivity(Intent.createChooser(share, "Share link!"));

    }

}
