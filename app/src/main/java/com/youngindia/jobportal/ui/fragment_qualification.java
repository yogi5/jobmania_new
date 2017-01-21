package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_qualification.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_qualification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_qualification extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Employee_RegistrationActivity employee_registrationActivity;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button nxtbtn;
    SessionManager session;
    fragment_emp_regitin  fragment_emp_regitin;
    RadioGroup radioGroup_degree;
    RadioButton radioButton_master,radioButton_graduation;
    fragmnet_emp_reg2 fragmnet_emp_reg2;
    fragment_employee_registration fragment_employee_registration;
    private OnFragmentInteractionListener mListener;
    EditText university,yearofpassing,percentage,university1,yearofpassing1,percentage1;
    AutoCompleteTextView course,course1;
    EditText edt_higherEducation,edt_higherEducation_university,edt_higherEducation_yearofpassing,
            edt_higherEducation_percentage,edt_higherEducation1,edt_higherEducation_university1,edt_higherEducation_yearofpassing1,
            edt_higherEducation_percentage1,edt_secondry_yearofpassing,edt_secondry_percentage,edt_tenth_percentage,
            edt_tenth_yearofpassing, edt_otherEducation_name,edt_otherEducation_percentage,edt_otherEducation_yearOfPassing,edt_otherEducation_university;
    TextInputLayout inputLayout;
    LinearLayout bachelordegreelayout,masterdegreelayout;

    String a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,q;
    Boolean degreevalue=false;
    public fragment_qualification() {
        // Required empty public constructor
    }

    public static fragment_qualification newInstance(String param1, String param2) {
        fragment_qualification fragment = new fragment_qualification();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_qualification, container, false);
        session = new SessionManager(getActivity().getApplicationContext());
        fragment_employee_registration=new fragment_employee_registration();
        // radioGroup_degree= (RadioGroup)rootview.findViewById(R.id.radio_btn_group_higher_education);
        // radioButton_master= (RadioButton)rootview.findViewById(R.id.radio_btn_master_degree);
        //radioButton_graduation= (RadioButton)rootview.findViewById(R.id.radioButton_bachlor_degree);
        // course=(AutoCompleteTextView) rootview.findViewById(R.id.edt_course);
        final String[] masterdegree = {"M.TECH","MCA","M.Phill","MSC","MBA","MCOM"};
        final String[] bachelordegree = {"B.TECH","BCA","BSc","LLB","BCOM","BBA"};
        university=(EditText) rootview.findViewById(R.id.edt_graducation_university);
        yearofpassing=(EditText) rootview.findViewById(R.id.edt_graducation_year);
        percentage=(EditText) rootview.findViewById(R.id.edt_graducation_perctage);
        inputLayout=(TextInputLayout) rootview.findViewById(R.id.input_layout);
        bachelordegreelayout=(LinearLayout) rootview.findViewById(R.id.linearlayoutbachelordegree);
        masterdegreelayout=(LinearLayout) rootview.findViewById(R.id.linearlayoutdegree);
        course1=(AutoCompleteTextView) rootview.findViewById(R.id.edt_course1);
        university1=(EditText) rootview.findViewById(R.id.edt_graducation_university1);
        yearofpassing1=(EditText) rootview.findViewById(R.id.edt_graducation_year1);
        percentage1=(EditText) rootview.findViewById(R.id.edt_graducation_perctage1);

//        radioGroup_degree.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId)
//                {
//                    case R.id.radio_btn_master_degree:
//                        masterdegreelayout.setVisibility(View.VISIBLE);
//                        bachelordegreelayout.setVisibility(View.GONE);
//                        degreevalue=true;
//                        radioButton_master.isChecked();
////                        course.setText("");
////                        university.setText("");
////                        percentage.setText("");
////                        yearofpassing.setText("");
//                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
//                                android.R.layout.select_dialog_item, masterdegree);
//                        course.setThreshold(1);
//                        course.setAdapter(arrayAdapter);
//
//                        break;
//                    case R.id.radioButton_bachlor_degree:
//                        masterdegreelayout.setVisibility(View.INVISIBLE);
//                        bachelordegreelayout.setVisibility(View.VISIBLE);
//                        radioButton_graduation.isChecked();
//                        degreevalue=false;
////                        course.setText("");
////                        university.setText("");
////                        percentage.setText("");
////                        yearofpassing.setText("");
//                        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(),
//                                android.R.layout.select_dialog_item, bachelordegree);
//                        course.setThreshold(1);
//                        course.setAdapter(arrayAdapter1);
//                        break;
//                }
//            }
//        });
        edt_higherEducation=(EditText) rootview.findViewById(R.id.edt_course);
        edt_higherEducation_university=(EditText) rootview.findViewById(R.id.edt_graducation_university);
        //edt_higherEducation_university.addTextChangedListener(new MyTextWatcher3(edt_higherEducation_university));
        edt_higherEducation_yearofpassing=(EditText) rootview.findViewById(R.id.edt_graducation_year);
        edt_higherEducation_percentage=(EditText) rootview.findViewById(R.id.edt_graducation_perctage);
// +2 Id's
        edt_secondry_yearofpassing=(EditText) rootview.findViewById(R.id.edt_12th_year);
        edt_secondry_percentage=(EditText) rootview.findViewById(R.id.edt_12th_percntage);

// 10th Id's
        edt_tenth_percentage=(EditText) rootview.findViewById(R.id.edt_10th_percentage);
        edt_tenth_yearofpassing=(EditText) rootview.findViewById(R.id.edt_10th_year);
// Other Id's
        edt_otherEducation_name=(EditText) rootview.findViewById(R.id.edt_other_education);
        edt_otherEducation_percentage=(EditText) rootview.findViewById(R.id.edt_other_education_perctage);
        edt_otherEducation_yearOfPassing=(EditText) rootview.findViewById(R.id.edt_other_education_year);
        edt_otherEducation_yearOfPassing=(EditText) rootview.findViewById(R.id.edt_other_education_year);
        edt_otherEducation_university=(EditText) rootview.findViewById(R.id.edt_other_education_university);

       /* if(session.getflag()==true)
        {
            HashMap<String, String> user1=session.getmasterqualificationdegree();
            a = user1.get(SessionManager.SET_Masterdegname);
            b=user1.get(SessionManager.SET_Masterdeguniversity);
            c=user1.get(SessionManager.SET_Masterdegyearofpassing);
            d=user1.get(SessionManager.SET_Masterdegpercentage);
            e=user1.get(SessionManager.KEY_secondry_percentage);
            f=user1.get(SessionManager.KEY_secondry_yearPassing);
            g=user1.get(SessionManager.KEY_tenth_percentage);
            h=user1.get(SessionManager.KEY_tenth_yearPassing);
            i=user1.get(SessionManager.KEY_otherEducation_name);
            j=user1.get(SessionManager.KEY_otherEducation_percentage);
            k=user1.get(SessionManager.KEY_otherEducation_yearPassing);
            l=user1.get(SessionManager.KEY_otherEducation_university);

            HashMap<String, String> user13=session.getbachelorqualificationdegree();
            m= user13.get(SessionManager.SET_Bachelerdegname);
            n= user13.get(SessionManager.SET_Bachelerdeguniversity);
            o= user13.get(SessionManager.SET_Bachelerdegyearofpassing);
            q= user13.get(SessionManager.SET_Bachelerdegpercentage);

            course1.setText(m);
            university1.setText(n);
            yearofpassing1.setText(o);
            percentage1.setText(q);
            edt_higherEducation.setText(a);
            edt_higherEducation_university.setText(b);
            edt_higherEducation_yearofpassing.setText(c);
            edt_higherEducation_percentage.setText(d);
            edt_secondry_yearofpassing.setText(e);
            edt_secondry_percentage.setText(f);
            edt_tenth_percentage.setText(g);
            edt_tenth_yearofpassing.setText(h);
            edt_otherEducation_name.setText(i);
            edt_otherEducation_percentage.setText(j);
            edt_otherEducation_yearOfPassing.setText(k);
            edt_otherEducation_university.setText(l);


        }*/

        // Inflate the layout for this fragment
        nxtbtn = (Button) rootview.findViewById(R.id.nxtbtn_qualification);
        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String higherEducationName=edt_higherEducation.getText().toString().trim();
                String higherEducationUniversity=edt_higherEducation_university.getText().toString().trim();
                String higherEducationPercentage=edt_higherEducation_percentage.getText().toString().trim();
                String higherEducationYearPassing=edt_higherEducation_yearofpassing.getText().toString().trim();
                String secondry_Percentage=edt_secondry_percentage.getText().toString().trim();
                String secondry_YearPassing=edt_secondry_yearofpassing.getText().toString().trim();
                String tenth_Percentage=edt_tenth_percentage.getText().toString().trim();
                String tenth_YearPassing=edt_tenth_yearofpassing.getText().toString().trim();
                String otherEducation_name=edt_otherEducation_name.getText().toString().trim();
                String otherEducation_percentage=edt_otherEducation_percentage.getText().toString().trim();
                String otherEducation_yearPassing=edt_otherEducation_yearOfPassing.getText().toString().trim();
                String otherEducation_university=edt_otherEducation_university.getText().toString().trim();
                String higherEducationName1 =course1.getText().toString().trim();
                String higherEducationUniversity1=university1.getText().toString().trim();
                String higherEducationPercentage1=yearofpassing1.getText().toString().trim();
                String higherEducationYearPassing1=percentage1.getText().toString().trim();
//                if(!course1.getText().toString().isEmpty()|| !university1.getText().toString().isEmpty()||!yearofpassing1.getText().toString().isEmpty()
//                        ||!percentage1.getText().toString().isEmpty()||!course1.getText().toString().isEmpty())
//                {
                //session.setbachelorqualificationdegree(higherEducationName,higherEducationUniversity1,higherEducationPercentage1,higherEducationYearPassing1);
                //    }

//                if(!edt_higherEducation.getText().toString().isEmpty()|| !edt_higherEducation_university.getText().toString().isEmpty()||
//                        !edt_higherEducation_percentage.getText().toString().isEmpty()||!edt_higherEducation_yearofpassing.getText().toString().isEmpty()
//                        || !edt_secondry_percentage.getText().toString().isEmpty())
//                {
                session.seteducation(higherEducationName,higherEducationUniversity,higherEducationPercentage,higherEducationYearPassing);
                //session.setmasterqualificationdegree(higherEducationName,higherEducationUniversity,higherEducationPercentage,higherEducationYearPassing);
                    /*session.storeeducationadetails(higherEducationName,higherEducationUniversity,higherEducationPercentage,higherEducationYearPassing,
                            secondry_Percentage,secondry_YearPassing, tenth_Percentage,tenth_YearPassing,otherEducation_name,otherEducation_percentage
                            ,otherEducation_yearPassing,otherEducation_university );*/
                session.storeeducationadetails(secondry_Percentage,secondry_YearPassing,tenth_Percentage,tenth_YearPassing,otherEducation_name,
                        otherEducation_percentage,otherEducation_yearPassing,otherEducation_university);
                FragmentTransaction fragmenttransaction = employee_registrationActivity.getSupportFragmentManager().beginTransaction();
                //   sScreen = getResources().getString(R.string.title_wall);
                fragmenttransaction.replace(R.id.frame_container1, fragment_employee_registration);
                fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmenttransaction.addToBackStack("tag2").commit();
            }
// else{
//                    Toast.makeText(getContext(),"Please Fillup your qualification",Toast.LENGTH_SHORT).show();
//                }
            //  }
        });
        return rootview;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        employee_registrationActivity=(Employee_RegistrationActivity) activity;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private class MyTextWatcher3 implements TextWatcher {
        private View view;

        private MyTextWatcher3(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            validateEmail();
        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edt_graducation_university:
                    validateEmail();
                    break;
            }
        }
    }

    private boolean validateEmail() {
        String email_id = edt_higherEducation.getText().toString().trim();
        if (email_id.isEmpty()) {
            inputLayout.setError("Please fillup here!!");
            course.requestFocus();
            return false;
        } else {
            inputLayout.setError(" ");
            inputLayout.setErrorEnabled(false);
            return true;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Educational Details");
        employee_registrationActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }
}