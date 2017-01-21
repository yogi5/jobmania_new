package com.youngindia.jobportal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;

public class Company_Base extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    public static Company_Base instance;
    fragment_companybase fragment_companybase;

    fragment_companypostajob fragmentsetting;
    Toolbar toolbar;
    String path;
    fragment_companysetting fragment_companysetting;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_company__base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        sessionManager=new SessionManager(this);
//        setSupportActionBar(toolbar);Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");

        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment_companybase = new fragment_companybase();
        fragment_companysetting=new fragment_companysetting();
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        //   sScreen = getResources().getString(R.string.title_wall);
        fragmenttransaction.replace(R.id.frame_container, fragment_companybase);
        fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmenttransaction.commit();
    }
    public static Company_Base getInstance() {
        return instance;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //   sScreen = getResources().getString(R.string.title_wall);
            ft.replace(R.id.frame_container, fragment_companybase);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack("tag").commitAllowingStateLoss();//commit();
            // Handle the camera action
        } else if (id == R.id.nav_setting) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //   sScreen = getResources().getString(R.string.title_wall);
            ft.replace(R.id.frame_container, fragment_companysetting);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack("tag").commitAllowingStateLoss();//commit();
        } else if (id == R.id.nav_companypostlist) {
            Intent intent =new Intent(Company_Base.this,CompanyPostedjobslist.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } /*else if (id == R.id.nav_shortlisted) {
            Intent intent =new Intent(Company_Base.this,Company_candidatelist.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }  else if (id == R.id.nav_received) {
            Intent intent =new Intent(Company_Base.this,Company_candidatelist.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }*/
        else if (id == R.id.nav_faq) {
            Intent intent =new Intent(Company_Base.this,App_Faq.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (id == R.id.nav_promote) {
            shareAppurl(path);
        }else if (id == R.id.logout) {
            sessionManager.setLogin(false);
            Intent intent = new Intent(Company_Base.this,LoginUser_Activity.class);
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
        share.putExtra(Intent.EXTRA_SUBJECT, "Shared App details");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.youngindia.jobportal&hl=en");
        startActivity(Intent.createChooser(share, "Share link!"));
    }
}