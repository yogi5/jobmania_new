package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

import static android.view.View.*;

public class fragment_employee_registration extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Employee_RegistrationActivity employee_registrationActivity;
    int year, month, day;
    TextView tvDisplayDate;
    Calendar cal;
    EditText editText_Dob;
    DatePicker dpResult;
    Button btnChangeDate;
    Button nxtbtn;
    static final int DATE_DIALOG_ID = 999;
    EditText current_address,currentcity,currentpincode,permanent_address,permanentcity,permanentpincode;
    TextView  dob;
    RadioButton male_radiobtn,female_radiobtn,radioButton_yes,radioButton_no;
    RadioGroup radioGroup_gender, radioGroup_passport;
    fragment_qualification fragment_qualification;
    fragment_emp_regitin fragment_emp_regitin;
    fragmnet_emp_reg2 fragmnet_emp_reg2;
    private fragment_emp_regitin.OnFragmentInteractionListener mListener;
    RelativeLayout permanentaddresslayout;
    CheckBox checkbox;
    int count=0;
    Spinner currentstate,permanentstate;
    SessionManager session;
    employee_registration1 empo1;
    boolean checked=false;
    String per_address,per_city,per_pin,per_state,cur_address,cur_city,cur_state,cur_pin;
    TextInputLayout inputLayout;
    public fragment_employee_registration() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_employee_registration, container, false);
        empo1=new employee_registration1();
        nxtbtn= (Button) rootview.findViewById(R.id.nxtbtn);
        inputLayout=(TextInputLayout) rootview.findViewById(R.id.input_layout);
        session=new SessionManager(getActivity());
        fragment_emp_regitin=new fragment_emp_regitin();
        permanentaddresslayout=(RelativeLayout)rootview.findViewById(R.id.permanentaddresslayout);
        checkbox=(CheckBox)rootview.findViewById(R.id.checkbox);
        current_address=(EditText) rootview.findViewById(R.id.edt_current_Address);
        current_address.addTextChangedListener(new MyTextWatcher(current_address));
        currentcity=(EditText) rootview.findViewById(R.id.edt_current_city);
        currentpincode=(EditText) rootview.findViewById(R.id.edt_current_pincode);
        permanent_address=(EditText) rootview.findViewById(R.id.edt_parmanent_Address);
        permanentcity=(EditText) rootview.findViewById(R.id.edt_parmnt_city);
        permanentpincode=(EditText) rootview.findViewById(R.id.edt_parmnt_pincode);
        currentstate=(Spinner)rootview.findViewById(R.id.spinner_current_state);
        permanentstate=(Spinner)rootview.findViewById(R.id.spinner_parmantAddress_State);

//      permanentaddresslayout.setVisibility(VISIBLE);
        checkbox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClicked(checkbox);
            }
        });
        session = new SessionManager(getActivity().getApplicationContext());

      /*  if(session.getflag()==true)
        {

            HashMap<String, String> user1=session.getstoreaddress();
            per_address = user1.get(SessionManager.KEY_PERMANENTADDRESS);
            per_city=user1.get(SessionManager.KEY_PERMANENTCITY);
            per_pin=user1.get(SessionManager.KEY_PERMANENTPIN);
            per_state=user1.get(SessionManager.KEY_PERMANENTSTATE);
            cur_address=user1.get(SessionManager.KEY_CURRENTADDRESS);
            cur_city=user1.get(SessionManager.KEY_CURRENTCITY);
            cur_pin=user1.get(SessionManager.KEY_CURRENTPIN);
            cur_state=user1.get(SessionManager.KEY_CURRENTSTATE);
            permanent_address.setText(per_address);
            permanentcity.setText(per_city);
            permanentpincode.setText(per_pin);
            // permanentstate.setText(per_city);
            current_address.setText(cur_address);
            currentcity.setText(cur_city);
            currentpincode.setText(cur_pin);
            // currentstate.setText(cur_state);



        }*/
        nxtbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                per_address=permanent_address.getText().toString().trim();
                per_city=permanentcity.getText().toString().trim();
                per_pin=permanentpincode.getText().toString().trim();
                per_state=permanentstate.getSelectedItem().toString().trim();
                cur_address=current_address.getText().toString().trim();
                cur_city=currentcity.getText().toString().trim();
                cur_pin=currentpincode.getText().toString().trim();
                cur_state=currentstate.getSelectedItem().toString().trim();
                session = new SessionManager(getActivity().getApplicationContext());
                session.storeaddress(cur_address,
                        cur_city,cur_pin,cur_state,per_address,per_city,per_pin,per_state);
                fragment_qualification=new fragment_qualification();
                fragmnet_emp_reg2=new fragmnet_emp_reg2();
                //if(!cur_address.isEmpty()&& !cur_city.isEmpty()&& !cur_pin.isEmpty()&&!cur_state.isEmpty()) {
                    FragmentTransaction fragmenttransaction = employee_registrationActivity.getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.replace(R.id.frame_container1, fragmnet_emp_reg2);
                    fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmenttransaction.addToBackStack("tag1").commit();
                //}else{
                   // if(current_address.getText().toString().isEmpty()) {validateEmail1();}
                   // else{
                       // Toast.makeText(getContext(),"Please Fillup your Address",Toast.LENGTH_SHORT).show();}
                }
            //}
        });
        return rootview;
    }
    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            permanentaddresslayout.setVisibility(INVISIBLE);
        }else{ permanentaddresslayout.setVisibility(VISIBLE);}
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        employee_registrationActivity=(Employee_RegistrationActivity) activity;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Address Details");
        employee_registrationActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;

        }


        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edt_current_Address:
                    validateEmail();
                    break;
            }
        }

    }
    public  boolean validateEmail1() {
        String email_id = current_address.getText().toString().trim();

        if ( email_id.isEmpty()) {
            inputLayout.setError("*");
            inputLayout.setErrorEnabled(true);
            return false;
        } else {
            inputLayout.setError(" ");
            inputLayout.setErrorEnabled(false);
            return true;
        }

    }


    private boolean validateEmail() {
        String email_id = current_address.getText().toString().trim();

        if ( email_id.isEmpty()) {
            inputLayout.setError("Please fillup this field!!!!");
            current_address.requestFocus();
            return false;
        } else {
            inputLayout.setError(" ");
            inputLayout.setErrorEnabled(false);
            return true;
        }

    }

}
