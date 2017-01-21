package com.youngindia.jobportal.ui;

import android.app.Fragment;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.youngindia.jobportal.R;

public class Employee_RegistrationActivity extends AppCompatActivity {
    Button btn_save;
    fragment_emp_regitin fragment_emp_regitin1;
    fragmnet_emp_reg2 fragmnet_emp_reg2;
    fragment_employee_registration fragment_employee_registration;
    employee_registration1 employee_registration1;
    Toolbar toolbar;
    private final static String FRAGMENT_TAG = "FRAGMENTB_TAG";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_employee__registration);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_textColor));
        setSupportActionBar(toolbar);

       /* toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext()  , "Back clicked!",     Toast.LENGTH_SHORT).show();
            }
        });*/

//        btn_save=(Button)findViewById(R.id.btn_save);
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(Employee_RegistrationActivity.this,Employee_HomeActivity.class);
//                startActivity(intent);
//            }
//        });
        fragmnet_emp_reg2 = new fragmnet_emp_reg2();
        fragment_emp_regitin1 = new fragment_emp_regitin();
        employee_registration1=new employee_registration1();
        fragment_employee_registration=new fragment_employee_registration();


        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        //   sScreen = getResources().getString(R.string.title_wall);
        fragmenttransaction.replace(R.id.frame_container1,fragment_emp_regitin1, FRAGMENT_TAG);
     /*   MyFragment myFragment = (MyFragment)getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (myFragment != null && myFragment.isVisible()) {
            // add your code here
        }*/
        fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
       // fragmenttransaction.addToBackStack(null);

        //Fragment currentFragment = getActivity().getFragmentManager().findFragmentById(R.id.fragment_container);
        // fragmenttransaction.addToBackStack(FRAGMENT_TAG);
        /*android.support.v4.app.FragmentManager fm = Employee_RegistrationActivity.this.getSupportFragmentManager();
String currentFragmentTag = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName();
        getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);*/
        //   android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        //      String currentFragmentTag = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName();*/
        //  Toast.makeText(getApplicationContext(),"currentFragment" +S1,Toast.LENGTH_SHORT).show();
        //List Fragments = getSupportFragmentManager().getFragments();
        // mCurrentFragment = Fragments.get(Fragments.size() - 1);
//        if()
//        {
//            toolbar.setNavigationOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext()  , "Back clicked!",     Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
        fragmenttransaction.commit();

       /*android.support.v4.app.Fragment S1 =getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        getSupportFragmentManager().findFragmentByTag("firstfragment");
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.frame_container1);*/
      /*  getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment currentFragment = fragmentManager.findFragmentById(R.id.frame_container1);*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registermenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_skip)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/



  

}
