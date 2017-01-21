package com.youngindia.jobportal.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;

import info.hoang8f.android.segmented.SegmentedGroup;

public class DailyWagesRegstr extends AppCompatActivity implements frgment_dailywages_register.OnFragmentInteractionListener{

    frgment_dailywages_register dailywages_register;
    frgmentd_dailywages_search dailywages_search;
    Toolbar toolbar;
    SegmentedGroup segmented2;
    RadioButton radiobtn_register,radiobtn_search;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_daily_wages_regstr);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        session=new SessionManager(getApplicationContext());
        toolbar.setTitle("Daily Wager");
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DailyWagesRegstr.this,LoginUser_Activity.class);
                startActivity(intent);
            }
        });
       // setSupportActionBar(toolbar);

        radiobtn_register= (RadioButton) findViewById(R.id.radiobutn_register);
        radiobtn_search= (RadioButton) findViewById(R.id.radiobutn_search);

        segmented2 = (SegmentedGroup)findViewById(R.id.segmented2);

        dailywages_register = new frgment_dailywages_register();
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        //   sScreen = getResources().getString(R.string.title_wall);
        fragmenttransaction.replace(R.id.frame_container, dailywages_register);
        fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmenttransaction.commit();

        if(session.getdailyfilter()==true)
        {
            segmented2.check(R.id.radiobutn_search);
            dailywages_search = new frgmentd_dailywages_search();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //   sScreen = getResources().getString(R.string.title_wall);
            fragmentTransaction.replace(R.id.frame_container, dailywages_search);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
            session.setdailyfilter(false);
        }

        segmented2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (checkedId == R.id.radiobutn_register) {
                    dailywages_register = new frgment_dailywages_register();
                    FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    //   sScreen = getResources().getString(R.string.title_wall);
                    fragmenttransaction.replace(R.id.frame_container, dailywages_register);
                    fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmenttransaction.commit();

                }if (checkedId == R.id.radiobutn_search)  {
                    dailywages_search = new frgmentd_dailywages_search();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    //   sScreen = getResources().getString(R.string.title_wall);
                    fragmentTransaction.replace(R.id.frame_container, dailywages_search);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction.commit();
                }

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
