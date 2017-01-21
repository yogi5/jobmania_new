package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Franchiseuser extends AppCompatActivity implements View.OnClickListener {
    private SessionManager session;
    Employee_RegistrationActivity employee_registrationActivity;
    int year, month, day;
    EditText tvDisplayDate,edt_username;
    Calendar cal;
    EditText editText_Dob;
    TextInputLayout inputLayout_Email,inputLayout_Name,inputLayout_UserName,
            inputLayout_password,inputLayout_confirmPassword,inputLayout_contact,inputLayout_highereducation,inputLayout_entercode;
    String name,mobile,username,password,tokenvalue, confirmpassword,status, userstatus, franchisevalue1, loginstatus,email1,dob1,gender,lastname,
            valuseserver1="",valuseserver2="";
    DatePicker dpResult;
    Button btnChangeDate;
    Dialog addWordRemainderDialog;
    Button nxtbtn;
    static final int DATE_DIALOG_ID = 999;
    EditText user_firstName,user_name,contact_no,email,current_address,permanent_address,passwordedt,confirmpsw,edt_code,edt_higheredu;
    TextView  dob;
    RadioButton male_radiobtn,female_radiobtn,radioButton_user,radioButton_franchise_user;
    RadioGroup radioGroup_gender, radioGroup_user;
    Button btnRegister;
    String Gender="",Userinfo="";
    ProgressDialog pDialog;
    Spinner franchisename;
    String franchise="";
    String franchisevalue="Singleuser";
    private static final String TAG = UserRegistration.class.getSimpleName();
    LoginUser_Activity loginUser_activity;
    LinearLayout linearLayoutfranchisename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franchiseuser2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registration");
        pDialog=new ProgressDialog(this);
        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_textColor));
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session=null;
                Intent intent = new Intent(Franchiseuser.this, LoginUser_Activity.class);
                startActivity(intent);

            }
        });
        setSupportActionBar(toolbar);
        inputLayout_Name=(TextInputLayout)findViewById(R.id.input_layout_name);
        inputLayout_UserName=(TextInputLayout)findViewById(R.id.input_layout_user_name);
        inputLayout_confirmPassword=(TextInputLayout)findViewById(R.id.input_layout_confirm_password);
        inputLayout_password = (TextInputLayout)findViewById(R.id.input_layout_password);
        inputLayout_contact = (TextInputLayout)findViewById(R.id.input_layout_contact_no);
        inputLayout_highereducation=(TextInputLayout) findViewById(R.id.input_layouthighredu);
        // inputLayout_entercode=(TextInputLayout) findViewById(R.id.input_layouentercode);
        inputLayout_Email=(TextInputLayout)findViewById(R.id.input_layout_email);
        loginUser_activity=new LoginUser_Activity();
        btnRegister=(Button)findViewById(R.id.registerbtn);
        dpResult = (DatePicker)findViewById(R.id.dpResult);
        //edt_code=(EditText)findViewById(R.id.edt_code);
        dob= (TextView)findViewById(R.id.tvDate);              // tv dob
        email= (EditText)findViewById(R.id.edt_email);
        email.addTextChangedListener(new MyTextWatcher(email));
        user_firstName= (EditText)findViewById(R.id.Ed_first_name);  // first name
        user_firstName.addTextChangedListener(new MyTextWatcher(user_firstName));
        user_name= (EditText)findViewById(R.id.Ed_user_name);
        user_name.addTextChangedListener(new MyTextWatcher(user_name));
        passwordedt=(EditText) findViewById(R.id.Ed_password);
        passwordedt.addTextChangedListener(new MyTextWatcher(passwordedt));
        confirmpsw=(EditText) findViewById(R.id.Ed_confirm_password1);
        confirmpsw.addTextChangedListener(new MyTextWatcher(confirmpsw));
        contact_no= (EditText)findViewById(R.id.Ed_contact);    // contact no
        contact_no.addTextChangedListener(new MyTextWatcher(contact_no));
        edt_higheredu=(EditText)findViewById(R.id.Ed_higheredu);
        edt_higheredu.addTextChangedListener(new MyTextWatcher(edt_higheredu));

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user2=session.gettoken();
        tokenvalue=user2.get(SessionManager.KEY_TOKEN);
        pDialog=new ProgressDialog(this);
        addWordRemainderDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        addWordRemainderDialog.setContentView(R.layout.add_dialog);

        ImageView addWordRemainderImage = (ImageView) addWordRemainderDialog.findViewById(R.id.addwordremainderImage);
        addWordRemainderImage.setImageResource(R.drawable.register);

        addWordRemainderDialog.show();

        addWordRemainderImage.setOnClickListener(this);



        // franchisename=(Spinner)rootview.findViewById(R.id.franchisename);
      /*  radioGroup_user= (RadioGroup)rootview.findViewById(R.id.radio_btn_group);
        radioButton_user= (RadioButton)rootview.findViewById(R.id.radioButton_singleuser);
        radioButton_franchise_user= (RadioButton)rootview.findViewById(R.id.radioButton_franchiseuser);*/
       /* radioGroup_user.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton_franchiseuser:
                        radioButton_franchise_user.isChecked();
                        Userinfo="Isfranchiseuser";
                        linearLayoutfranchisename.setVisibility(View.VISIBLE);
//                        franchise=franchisename.getSelectedItem().toString().trim();
                        break;
                    case R.id.radioButton_singleuser:
                        radioButton_user.isChecked();
                        Userinfo="IsSingleuser";
                        linearLayoutfranchisename.setVisibility(View.INVISIBLE );
                        break;
                }
            }
        });*/

        radioGroup_gender= (RadioGroup)findViewById(R.id.radio_btn_group_gender);
        male_radiobtn = (RadioButton)findViewById(R.id.radioButton_male);
        female_radiobtn = (RadioButton)findViewById(R.id.radioButton_female);

        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton_male:
                        male_radiobtn.isChecked();
                        Gender="male";
                        break;
                    case R.id.radioButton_female:
                        female_radiobtn.isChecked();
                        Gender="female";
                        break;
                }
            }
        });

        tvDisplayDate = (EditText)findViewById(R.id.tvDate);


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


//        // set current date into textview
       /* tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));*/
        btnChangeDate = (Button)findViewById(R.id.btnChangeDate);
        tvDisplayDate.addTextChangedListener(new MyTextWatcher1(tvDisplayDate));
//        btnChangeDate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datepicker1 = new DatePickerDialog(employee_registrationActivity, datePickerListener,
//                        year, month, day);
//                datepicker1.show();
//            }
//
//        });
        session = new SessionManager(getApplicationContext());

        if(session.getflag()==true) {
            HashMap<String, String> user1 = session.getstoredetails();
            HashMap<String, String> user3 = session.getserverdetails();
            valuseserver2 = user3.get(SessionManager.KEY_VALUESERVER);
            if(valuseserver2==null)
            {
                valuseserver1="";
            }else
            {valuseserver1=valuseserver2;}
            name = user1.get(SessionManager.KEY_NAME1);
            mobile = user1.get(SessionManager.KEY_MOBILE);
            email1 = user1.get(SessionManager.KEY_EMAIL);
            dob1 = user1.get(SessionManager.KEY_DOB);
            lastname = user1.get(SessionManager.KEY_LASTNAME);
            gender = user1.get(SessionManager.KEY_GENDER);
            status = user1.get(SessionManager.KEY_STATUS);
            userstatus = user1.get(SessionManager.KEY_USERSTATUS);
            username = user1.get(SessionManager.KEY_USERNAME1);
            password = user1.get(SessionManager.KEY_PASSWORD);
            confirmpassword= user1.get(SessionManager.KEY_CONFORMPASSWORD);
            //  franchisevalue1 = user1.get(SessionManager.KEY_FRANCHISEVALUE);
            loginstatus = user1.get(SessionManager.KEY_LOGIN);
            passwordedt.setText(password);
            confirmpsw.setText(confirmpassword);
            if (gender.equals("male")) {
                male_radiobtn.setChecked(true);
            } else {
                female_radiobtn.setChecked(true);
            }
          /*  if (franchisevalue1.equals("Singleuser")) {
                radioButton_user.setChecked(true);
            } else {
                radioButton_franchise_user.setChecked(true);
            }*/
            user_firstName.setText(name);
            if (valuseserver1.equals("mobile")) {
                contact_no.setText("");
                //contact_no.setBackgroundColor(Color.rgb(231,155,155));
            } else {
                contact_no.setText(mobile);
            }
            if (valuseserver1.equals("email")) {
                email.setText("");
                // email.setBackgroundColor(Color.rgb(231,155,155));
            } else {
                email.setText(email1);
            }
            if (valuseserver1.equals("username")) {
                user_name.setText("");
                user_name.setBackgroundColor(Color.rgb(231,155,155));

            } else {
                user_name.setText(username);
            }
            tvDisplayDate.setText(dob1);
        }
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean value= validateEmail();
                String number=contact_no.getText().toString();
                String psw=passwordedt.getText().toString().trim();
                String confirmpsw1=confirmpsw.getText().toString().trim();
                String highereducation=edt_higheredu.getText().toString().trim();
                if(!user_firstName.getText().toString().isEmpty() && !contact_no.getText().toString().isEmpty()&& !Gender.isEmpty() && !email.getText().toString().isEmpty()
                        && !user_name.getText().toString().isEmpty() && !passwordedt.getText().toString().isEmpty() && value==true && !edt_higheredu.getText().toString().isEmpty() ) {
                    if(number.length() ==10 ) {
                        if( psw.equals(confirmpsw1)) {
                            String name = user_firstName.getText().toString().trim();
                            String mobileno = contact_no.getText().toString().trim();
                            String username = user_name.getText().toString().trim();
                            String password = passwordedt.getText().toString().trim();
                            String conformpassword=confirmpsw.getText().toString().trim();
                            String email1 = email.getText().toString().trim();
                            String dob1 = tvDisplayDate.getText().toString().trim();
                            String status = "IsCandidat";
                            // String userstatus = Userinfo;
                            String loginstatus = "deactivated";
                          /*  if (!franchisename.getSelectedItem().toString().trim().equals("Select Your Franchise") || franchisename.getSelectedItem().toString().isEmpty()) {
                                franchisevalue = franchisename.getSelectedItem().toString().trim();
                            }*/
                            session = new SessionManager(getApplicationContext());
                            session.storedetails(name, mobileno, email1, dob1, Gender, status, username, password,conformpassword, loginstatus);
                            //String edtcode=edt_code.getText().toString().trim();
                            String s="abc128ed205";
                            // if(edtcode.equals(s))
                            // {
                            registerUser(name,mobileno,email1,username,password,status,loginstatus,tokenvalue,highereducation);
                            // calljson2(data);
                            // }
                            // else
                            // {
                            //    Toast.makeText(getApplicationContext(),"Code not match",Toast.LENGTH_LONG).show();
                            // }
                        }else{Toast.makeText(getApplicationContext(),"Password does't match!!",Toast.LENGTH_SHORT).show();}
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please check Mobile no",Toast.LENGTH_SHORT).show();
                    }

                }else
                {

                    if (email.getText().toString().isEmpty()|| edt_higheredu.getText().toString().isEmpty()||edt_username.getText().toString().isEmpty()
                            ||edt_code.getText().toString().isEmpty()||passwordedt.getText().toString().isEmpty()||confirmpsw.getText().toString().isEmpty()
                            ||contact_no.getText().toString().isEmpty()) {
                        validateEmail1();
                        validateHigherEdu();
                        validateUserName();
                        validatecode();
                        validateName();
                        validatePassword();
                        validateConfirmPassword();
                        validateContactNo();
                    }
//                  else if(edt_higheredu.getText().toString().isEmpty())
//                    { validateHigherEdu();}
//                    else if (edt_username.getText().toString().isEmpty())
//                    { validateUserName();}
//                    else if (edt_code.getText().toString().isEmpty())
//                    { validatecode();}
//                    else if (passwordedt.getText().toString().isEmpty())
//                    { validatePassword();}
//                    else if (confirmpsw.getText().toString().isEmpty())
//                    {  validateConfirmPassword();}
//                    else if (contact_no.getText().toString().isEmpty())
//                    {  validateContactNo();}
                }


            }
        });

    }




    private void registerUser(final String name, final String mobileno, final String email1, final String username, final String password, final String status, final String loginstatus
            , final String tokenvalue, final String highereducation) {
        //   , final String highereducation, final String highereducation1
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_USERREGISTER, new Response.Listener<String>() {

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
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
//                        String uid = jObj.getString("uid");
//
//                        JSONObject user = jObj.getJSONObject("user");
//                        String name = user.getString("name");
//                        String email = user.getString("email");
//                        String created_at = user
//                                .getString("created_at");

                        // Inserting row in users table
                        //db.addUser(name, email, uid, created_at);

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Franchiseuser.this);
                        String alert1 = "username: " + username;
                        String alert2 = "password: " + password;


                        builder1.setMessage( "You successfully registered!!!!" + "\n" + alert1 +"\n" +alert2);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent intent= new Intent(getApplicationContext(),LoginUser_Activity.class);
                                intent.putExtra("Username",username);
                                intent.putExtra("Password",password);
                                intent.putExtra("status",status);
                                startActivity(intent);
                            }
                        });
                        builder1.show();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        final String valueserver=jObj.getString("value");
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Franchiseuser.this);
                        builder1.setMessage(errorMsg);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                               /* FragmentTransaction fragmenttransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                //   sScreen = getResources().getString(R.string.title_wall);
                                fragmenttransaction.replace(R.id.frame_container1,employee_registration1, FRAGMENT_TAG);

                                fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                fragment_employee_registration frg=new fragment_employee_registration();*/
                                if (valueserver.equals("mobile")) {
                                    contact_no.setText("");
                                    //contact_no.setBackgroundColor(Color.rgb(231,155,155));
                                } else {
                                    contact_no.setText(mobile);
                                }
                                if (valueserver.equals("email")) {
                                    email.setText("");
                                    // email.setBackgroundColor(Color.rgb(231,155,155));
                                } else {
                                    email.setText(email1);
                                }
                                if (valueserver.equals("username")) {
                                    user_name.setText("");
                                    user_name.setBackgroundColor(Color.rgb(231,155,155));

                                } else {
                                    user_name.setText(username);
                                }
                                session.storevalue(valueserver);
                                session.setflag(true);
//                                frg.checked=true;
//                                employee_registration1.checked=true;
                                // fragmenttransaction.commit();
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
                params.put("name", name);
                params.put("mobileno",mobileno);
                params.put("email",email1);
                params.put("username",username);
                params.put("password",password);
                params.put("status",status);
                params.put("loginstatus",loginstatus);
                params.put("token",tokenvalue);
                params.put("higheredu",highereducation);
                return checkParams(params);
//                params.put("highereducation", highereducation);
//                params.put("highereducationuniversity",highereducationuniversity);
//                params.put("higherEducationPercentage",higherEducationPercentage);
//                params.put("higherEducationYearPassing",higherEducationYearPassing);
//                params.put("secondry_percentage",secondry_percentage);
//                params.put("secondry_yearPassing",secondry_yearPassing);
//                params.put("tenth_percentage",tenth_percentage);
//                params.put("tenth_yearPassing",tenth_yearPassing);
//                params.put("otherEducation_name",otherEducation_name);
//                //
//                params.put("otherEducation_percentage", otherEducation_percentage);
//                params.put("otherEducation_yearPassing",otherEducation_yearPassing);
//                params.put("otherEducation_university",otherEducation_university);
//                params.put("per_address",per_address);
//                params.put("per_pin",per_pin);
//                params.put("per_state",per_state);
//                params.put("per_city",per_city);
//                params.put("cur_city",cur_city);
//                params.put("cur_pin",cur_pin);
//                // params.put("name", name);
//                params.put("cur_state",cur_state);
//                params.put("keyskill1",keyskill1);
//                params.put("locationprfer",locationprfer);
//                params.put("locationspinnerval",locationspinnerval);
//                params.put("industyprefer",industyprefer);
//                params.put("industyspinner",industyspinner);
//                params.put("locationother",locationother);
//                params.put("candidatesttus1",candidatesttus1);
//
//                params.put("industryother",industryother);
//                params.put("currentcom",currentcom);
//                params.put("currentdes",currentdes);
//                params.put("currentauto",currentauto);
//                params.put("expectedauto",expectedauto);

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


    public  boolean validateEmail1() {
        String email_id = email.getText().toString().trim();
        if ( email_id.isEmpty()) {
            inputLayout_Email.setError("*");
            inputLayout_Email.setErrorEnabled(true);
            return false;
        } else {
            inputLayout_Email.setError(" ");
            inputLayout_Email.setErrorEnabled(false);
            return true;
        }

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



    private void submitForm() {

        if (!validateEmail()) {
            return ;
        }else{Toast.makeText(getApplicationContext(),"Please check",Toast.LENGTH_SHORT).show();}

    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        addWordRemainderDialog.dismiss();
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
                case R.id.edt_email:
                    validateEmail();
                    break;
                case R.id.Ed_first_name:
                    validateName();
                    break;
                case R.id.Ed_user_name:
                    validateUserName();
                    break;
                case R.id.Ed_password:
                    validatePassword();
                    break;
                case R.id.Ed_confirm_password1:
                    validateConfirmPassword();
                    break;
                case R.id.Ed_contact:
                    validateContactNo();
                    break;
                case R.id.Ed_higheredu:
                    validateHigherEdu();
                    break;
                case R.id.edt_code:
                    validatecode();
                    break;
            }
        }

    }


    private boolean validateHigherEdu() {
        String higheredu = edt_higheredu.getText().toString().trim();

        if (higheredu.isEmpty()) {
            inputLayout_highereducation.setError("*");
            requestFocus(edt_higheredu);
            return false;
        } else {
            inputLayout_highereducation.setError(" ");
            inputLayout_highereducation.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatecode() {
        String validatecode = edt_code.getText().toString().trim();

        if (validatecode.isEmpty()) {
            inputLayout_entercode.setError("*");
            requestFocus(edt_code);
            return false;
        } else {
            inputLayout_entercode.setError(" ");
            inputLayout_entercode.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String email_id = email.getText().toString().trim();

        if ( !isValidEmail(email_id) && !email_id.isEmpty()) {
            inputLayout_Email.setError("Invalid email");
            requestFocus(email);
            return false;
        } else if (email_id.isEmpty()){
            inputLayout_Email.setError("*");
            requestFocus(email);
            return false;

        }else {
            inputLayout_Email.setError(" ");
            inputLayout_Email.setErrorEnabled(false);
            return true;
        }

    }

    private Integer generatepassword(String mobile) {
        Integer password =0;
        Random rnd =new Random();
        for(int i=1;i<8;i++)
        {

            password=Math.abs(rnd.nextInt());
        }
        return password;
    }

    private String generateusername(String name) {
        String usernameval="abc";
        char[] chars =name.toCharArray();
        StringBuilder sb= new StringBuilder();
        Random random =new Random();
        for(int i=0;i<6;i++)
        {
            char c=chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        usernameval=sb.toString();
        return usernameval;
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }




    private class MyTextWatcher1 implements TextWatcher {
        private String current = "";
        private String ddmmyyyy = "DDMMYYYY";
        private Calendar cal = Calendar.getInstance();
        public MyTextWatcher1(EditText tvDisplayDate) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals(current)) {
                String clean = s.toString().replaceAll("[^\\d.]", "");
                String cleanC = current.replaceAll("[^\\d.]", "");

                int cl = clean.length();
                int sel = cl;
                for (int i = 2; i <= cl && i < 6; i += 2) {
                    sel++;
                }
                //Fix for pressing delete next to a forward slash
                if (clean.equals(cleanC)) sel--;

                if (clean.length() < 8){
                    clean = clean + ddmmyyyy.substring(clean.length());
                }else{
                    //This part makes sure that when we finish entering numbers
                    //the date is correct, fixing it otherwise
                    int day  = Integer.parseInt(clean.substring(0,2));
                    int mon  = Integer.parseInt(clean.substring(2,4));
                    int year = Integer.parseInt(clean.substring(4,8));

                    if(mon > 12)
                    {
                        mon = 12;

                        //Toast.makeText(getActivity().getApplicationContext(),"Please enter valid months",Toast.LENGTH_SHORT).show();

                    }
                    cal.set(Calendar.MONTH, mon-1);
                    year = (year<1950)?1950:(year>2016)?2016:year;
                    Calendar calendar = Calendar.getInstance();
                    int year1 = calendar.get(Calendar.YEAR);
                   /* if((year<1950) ||(year>year1))
                    {
                        Toast.makeText(getActivity().getApplicationContext(),"Please enter valid year",Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        year=year;
                    }*/
                    cal.set(Calendar.YEAR, year);
                    // ^ first set year for the line below to work correctly
                    //with leap years - otherwise, date e.g. 29/02/2012
                    //would be automatically corrected to 28/02/2012

                    day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                    clean = String.format("%02d%02d%02d",day, mon, year);
                }

                clean = String.format("%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8));

                sel = sel < 0 ? 0 : sel;
                current = clean;
                tvDisplayDate.setText(current);

                tvDisplayDate.setSelection(sel < current.length() ? sel : current.length());

            }
        }





        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    private boolean validateName()
    {
        String First_Name = user_firstName.getText().toString().trim();

        {
            if (First_Name.isEmpty()) {
                inputLayout_Name.setError("*");
                requestFocus(user_firstName);
                return false;
            } else {
                inputLayout_Name.setError(" ");
                inputLayout_Name.setErrorEnabled(false);
                return true;
            }
        }
    }
    private boolean validateUserName() {
        String UserName = user_name.getText().toString().trim();

        { if (UserName.isEmpty()) {
            inputLayout_UserName.setError("*");
            requestFocus(user_name);
            return false;
        } else {
            inputLayout_UserName.setError(" ");
            inputLayout_UserName.setErrorEnabled(false);
            return true;
        }
        }
    }
    private boolean validatePassword() {
        String Password = passwordedt.getText().toString().trim();

        {
            if (Password.isEmpty()) {
                inputLayout_password.setError("*");
                requestFocus(passwordedt);
                return false;
            } else {
                inputLayout_password.setError(" ");
                inputLayout_password.setErrorEnabled(false);
                return true;
            }
        }
    }
    private boolean validateConfirmPassword() {
        String ConfirmPassword = confirmpsw.getText().toString().trim();

        {
            if (ConfirmPassword.isEmpty()) {
                inputLayout_confirmPassword.setError("*");
                requestFocus(confirmpsw);
                return false;
            } else {
                inputLayout_confirmPassword.setError(" ");
                inputLayout_confirmPassword.setErrorEnabled(false);
                return true;
            }
        }
    }
    private boolean validateContactNo() {
        String ContactNo = contact_no.getText().toString().trim();

        if (ContactNo.isEmpty()) {
            inputLayout_contact.setError("*");
            requestFocus(contact_no);
            return false;
        } else {
            inputLayout_contact.setError(" ");
            inputLayout_contact.setErrorEnabled(false);
            return true;
        }
    }


}