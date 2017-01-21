package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.Model_Search;
import com.youngindia.jobportal.adapter.Model_Searchuser;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Search_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextId;
    private Button buttonGet;
    private Spinner location,qualification,experience,salary;
    private EditText autoCompleteTextView;
    private TextView textViewResult;
    Search_Result search_result;
    private ProgressDialog pd;
    BackTask bt;
    Model_Searchuser p;
    Toolbar toolbar;
    String keyword1,qualification1,location1,salaryvalue,salaryfirst,salarysecond;
    private ProgressDialog loading;
    String GOTO="";
    Dialog addWordRemainderDialog;
    Context ctx;
    ProgressDialog pDialog;
    EditText qualification2,location2,salary2;
    Spinner salary11,salary21;
    RadioButton male_radiobtn,female_radiobtn,radioButton_fresher,radioButton_experience;
    RadioGroup radioGroup_gender, radioGroup_user;
    String Experience="fresher";
    int forgetpsw;
    EditText spinnervalue;
    String value,experiencevalue;
    SeekBar seekBar_experience, seekBar_expected_salry;
    LinearLayout linearLayoutfranchisename;
    SessionManager sessionManager;
    TextInputLayout textinputlayout_qualificatiopn;
    public static ArrayList<Model_Searchuser> searchlist = new ArrayList<Model_Searchuser>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Search ");
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        setSupportActionBar(toolbar);
        ctx=this;
        textinputlayout_qualificatiopn=(TextInputLayout)findViewById(R.id.input_layout_qualification);
        linearLayoutfranchisename=(LinearLayout)findViewById(R.id.linearlayout_experience) ;
        spinnervalue=(EditText)findViewById(R.id.edit_experience);
        seekBar_experience= (SeekBar)findViewById(R.id.seekBar_Experience);
        value = spinnervalue.getText().toString();
        radioGroup_user= (RadioGroup)findViewById(R.id.radio_btn_group);
        radioButton_fresher= (RadioButton)findViewById(R.id.radioButton_fresher);
        radioButton_experience= (RadioButton)findViewById(R.id.radioButton_experience);
        autoCompleteTextView= (EditText) findViewById(R.id.autocompleteEditTextView);
        qualification2=(EditText)findViewById(R.id.linearlayout_qualification);
        qualification2.addTextChangedListener(new MyTextWatcher(qualification2));
        location2=(EditText)findViewById(R.id.linearlayout_location);
        salary11=(Spinner)findViewById(R.id.salaryyear);
        salary21=(Spinner)findViewById(R.id.salaryyear1);
        addWordRemainderDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        addWordRemainderDialog.setContentView(R.layout.searchadd_dialog);

        ImageView addWordRemainderImage = (ImageView) addWordRemainderDialog.findViewById(R.id.addwordremainderImage);
        //addWordRemainderImage.setImageResource(R.drawable.seacrh);

        addWordRemainderDialog.show();

        addWordRemainderImage.setOnClickListener(this);
        sessionManager=new SessionManager(getApplicationContext());
        // salary2=(EditText)findViewById(R.id.linearlayout_slary);
//        qualification=(Spinner)findViewById(R.id.qualification);
//        location=(Spinner)findViewById(R.id.location);
//        salary=(Spinner)findViewById(R.id.salary);
        buttonGet = (Button) findViewById(R.id.btnSubmit);
        pDialog=new ProgressDialog(this);
        qualification2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchdata();
                    handled = true;
                }
                return handled;
            }
        });
        location2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchdata();
                    handled = true;
                }
                return handled;
            }
        });
        spinnervalue.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(count==0)
                {
                    seekBar_experience.setProgress(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==0)
                {
                    seekBar_experience.setProgress(0);
                }

            }



            @Override
            public void afterTextChanged(Editable s) {
                try {
                    //Update Seekbar value after entering a number

                    if((Float.parseFloat(s.toString()) <=10.00))
                    {
                        int currentProgress = seekBar_experience.getProgress();
                        seekBar_experience.setProgress(0);
                        Rect bounds = seekBar_experience.getProgressDrawable().getBounds();
                        seekBar_experience.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));
                        seekBar_experience.getProgressDrawable().setBounds(bounds);
                        forgetpsw=Math.round(Float.parseFloat(s.toString()));
                        seekBar_experience.setProgress(forgetpsw);
                        //seekBar_experience.setProgress(Integer.parseInt(s.toString()));
                        // spinnervalue.setSelection(spinnervalue.getText().length());
                        // seekBar_experience.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                        //int maxProgress=50;
                        //spinnervalue.setText(String.valueOf(progress));
               /* progress = progress;
                int lower = Color.argb(0xFF,progress*0x10000, progress*0x100, progress)+0xff000000;
                color.setBackgroundColor(lower);*/
                        /*float[] hsvColor = {0, 1, 1};
// generate only hue component in range [0, 360),
// leaving saturation and brightness maximum possible
                        hsvColor[0] = 360f * progress / maxProgress;
                        seekBar_experience.setBackgroundColor(Color.HSVToColor(hsvColor));*/


                    }
                    else if((Float.parseFloat(s.toString()) <=25.00))
                    {

                        int currentProgress = seekBar_experience.getProgress();
                        seekBar_experience.setProgress(0);
                        Rect bounds = seekBar_experience.getProgressDrawable().getBounds();
                        seekBar_experience.setProgressDrawable(getResources().getDrawable(R.drawable.progrees_bar1));
                        seekBar_experience.getProgressDrawable().setBounds(bounds);
                        forgetpsw=Math.round(Float.parseFloat(s.toString()));
                        seekBar_experience.setProgress(forgetpsw);
                        //seekBar_experience.setProgress(currentProgress);
                        //seekBar_experience.setProgressTintList(ColorStateList.valueOf(Color.RED));

                        //spinnervalue.setSelection(spinnervalue.getText().length());
                        //seekBar_experience.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            seekBar_experience.setProgressDrawable(getResources()
                                    .getDrawable(R.drawable.progress_bar,getContext().getTheme()));

                        } else {
                            seekBar_experience.setProgressDrawable(getResources()
                                    .getDrawable(R.drawable.progress_bar));

                        }*/

                    }
                    else if((Float.parseFloat(s.toString()) >=26.00))
                    {
                        int currentProgress = seekBar_experience.getProgress();
                        seekBar_experience.setProgress(0);
                        Rect bounds = seekBar_experience.getProgressDrawable().getBounds();
                        seekBar_experience.setProgressDrawable(getResources().getDrawable(R.drawable.progrees_bar2));
                        seekBar_experience.getProgressDrawable().setBounds(bounds);
                        forgetpsw=Math.round(Float.parseFloat(s.toString()));
                        seekBar_experience.setProgress(forgetpsw);
                        // spinnervalue.setSelection(spinnervalue.getText().length());

                        //seekBar_experience.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                    }


                }catch (Exception ex) {
                }
            }
        });
        if(sessionManager.getfilter()==true)
        {
            HashMap<String, String> user1 = sessionManager.getusersearchvalue();
            autoCompleteTextView.setText(user1.get(SessionManager.KEY_USERSEARCHKEYWORD));
            qualification2.setText(user1.get(SessionManager.KEY_USERSEARCHQUALIFICATION));
            location2.setText(user1.get(SessionManager.KEY_USERSEARCHLOCATION));
            //salary11.setSelection(Integer.parseInt(user1.get(SessionManager.KEY_USERSEARCHKESALARYFIRST)));
            //salary21.setSelection(Integer.parseInt(user1.get(SessionManager.KEY_USERSEARCHKESALARSECOND)));
            String experience1 = user1.get(SessionManager.KEY_USERSEARCHEXP);
            if(experience1.equals("fresher"))
            {
                radioButton_fresher.isChecked();
            }else
            {
                radioButton_experience.isChecked();
            }

        }
        radioGroup_user.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton_experience:
                        radioButton_experience.isChecked();
                        Experience="experienced";
                        linearLayoutfranchisename.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButton_fresher:
                        Experience="fresher";
                        radioButton_fresher.isChecked();
                        linearLayoutfranchisename.setVisibility(View.GONE );
                        break;
                }
            }
        });
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchdata();
            }});
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    public void searchdata()
    {
        keyword1=autoCompleteTextView.getText().toString().trim();
        keyword1 = keyword1.replace(" ", "");
        keyword1 = keyword1.replaceAll("[\\-\\+\\.\\^:,]","");
        keyword1=keyword1.toLowerCase();
        qualification1=qualification2.getText().toString().trim();
        qualification1 = qualification1.replace(" ", "");
        qualification1 = qualification1.replaceAll("[\\-\\+\\.\\^:,]","");
        qualification1=qualification1.toLowerCase();
        location1=location2.getText().toString().trim();
        location1= location1.replace(" ", "");
        location1 = location1.replaceAll("[\\-\\+\\.\\^:,]","");
        location1=location1.toLowerCase();
        salaryfirst=salary11.getSelectedItem().toString().trim();
        salarysecond=salary21.getSelectedItem().toString().trim();
        salaryvalue=salaryfirst.concat(salarysecond);
        salaryvalue = salaryvalue.replace(" ", "");
        salaryvalue =salaryvalue.replaceAll("[\\-\\+\\.\\^:,]","");
        salaryvalue=salaryvalue.toLowerCase();
        if(Experience.equals("fresher"))
        {
            experiencevalue="fresher";
        }else{
            experiencevalue= String.valueOf(forgetpsw);
        }
        // salary1=salary2.getText().toString().trim();

        if (!qualification2.getText().toString().isEmpty() || !location2.getText().toString().isEmpty())
        {
//                    if(qualification.getSelectedItem().toString().equals("Qualification") || location.getSelectedItem().toString().equals("Location")  )
//                    {
//                        Toast.makeText(getApplicationContext(),"Please select the value from Qualification/Location",Toast.LENGTH_SHORT).show();
//                    }

//                    else if(salary.getSelectedItem().toString().equals("Salary"))
//                    {
//                        salary1="";
//                        pDialog.setMessage("Searching ...");
//                        showDialog();
//                        calljson();
//                    }
            // else {
//                        bt = new BackTask();
//                        bt.execute();

            if(salaryvalue.equals("00"))
            {
                salaryvalue="";
            }
            sessionManager.storeusersearchvalue(keyword1,qualification1,location1,salaryfirst,salarysecond,experiencevalue);
            pDialog.setMessage("Searching ...");
            showDialog();
            calljson();
//                          if(GOTO.toString().equals("success")) {
//                              Intent intent = new Intent(Search_Activity.this, Search_Result.class);
////                              intent.putExtra("Keyword", autoCompleteTextView.getText().toString().trim());
////                              intent.putExtra("Qualification", qualification.getSelectedItem().toString().trim());
////                              intent.putExtra("Location", location.getSelectedItem().toString().trim());
////                              intent.putExtra("Salary", salary.getSelectedItem().toString().trim());
//                              startActivity(intent);
//                          }else
//                          {
//                              Toast.makeText(getApplicationContext(),"No Data found",Toast.LENGTH_SHORT).show();
//                          }
            //  }

        } else {
           // validatequalification();
            //  Toast.makeText(getApplicationContext(),"please enter the value",Toast.LENGTH_SHORT).show();
        }
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void calljson() {

        String url = AppConfig.DATA_URL+"jobdetails="+keyword1
                +"&jobqualification="+qualification1
                +"&location="+location1
                +"&salary="+salaryvalue;
        url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                hideDialog();
                try {
                      /*  JSONArray result=new JSONArray(response);
                        JSONArray valarray=result.getJSONArray(1);
                        valarray.toString().replace("[", "");
                        valarray.toString().replace("]", "");*/
                        /*SONObject jsonObject = new JSONObject(response);
                        JSONArray namearray=jsonObject.names();
                        JSONArray result = jsonObject.toJSONArray(namearray);
                        JSONArray valarray=result.getJSONArray(1);
                        valarray.toString().replace("[", "");
                        valarray.toString().replace("]", "");*/

                    JSONObject jObj = new JSONObject(response);


                    Boolean error=jObj.getBoolean("status");
                      /*  Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();*/
                       /* Boolean error = jObj.getBoolean("error");
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();*/
                    // String error1=jsonObject.getString("error1");
                    //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    if (!error) {
                        JSONArray valarray=jObj.getJSONArray("result");
                        int s=valarray.length();
                        for (int n = 0; n < valarray.length(); n++) {
                            JSONObject searchData = valarray.getJSONObject(n);
                            p = new Model_Searchuser(searchData.getString(AppConfig.KEY_TECHNOLOGY),
                                    searchData.getString("jobdetails"),
                                    searchData.getString("jobqualification"),
                                    searchData.getString("companyname"),
                                    searchData.getString("location"),
                                    searchData.getString("salary"));
                            searchlist.add(p);
                           /* Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                            r.play();
                            Intent intent1 = new Intent(Search_Activity.this,User_jobdetails.class);
                            PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
                            Notification n1  = new Notification.Builder(getApplicationContext())
                                    .setContentTitle("Notification by Jobportal")
                                    .setContentText("jobs recommend for you!!!!")
                                    .setSmallIcon(R.drawable.app_icon)
                                    .setContentIntent(pIntent).getNotification();
                            n1.flags=Notification.FLAG_AUTO_CANCEL;
                            NotificationManager notificationManager =
                                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            notificationManager.notify(0, n1);*/
                        }
                        Intent intent = new Intent(Search_Activity.this, Search_Result.class);
//                              intent.putExtra("Keyword", autoCompleteTextView.getText().toString().trim());
//                              intent.putExtra("Qualification", qualification.getSelectedItem().toString().trim());
//                              intent.putExtra("Location", location.getSelectedItem().toString().trim());
//                              intent.putExtra("Salary", salary.getSelectedItem().toString().trim());
                        intent.putExtra("s", s);
                        startActivity(intent);
                    }
                    else
                    {

                        String errorMsg = jObj.getString("error_msg");
/* Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();*/
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(Search_Activity.this);


                        builder1.setMessage( errorMsg );
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                hideDialog();
                            }
                        });
                        builder1.show();
                        // finish();
                    }
                } catch (JSONException e) {  e.printStackTrace();  }
                //adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
////        if(bt==null) {
//            bt = new BackTask();
//            bt.execute();
//       // }
    }

    @Override
    public void onClick(View v) {
        addWordRemainderDialog.dismiss();

    }


    public class BackTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String url = AppConfig.DATA_URL+"keyword="+keyword1
                    +"&qualification="+qualification1
                    +"&location="+location1
                    +"&salary="+salaryvalue;
            url=url.replaceAll(" ", "%20");
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {
                    try {
                      /*  JSONArray result=new JSONArray(response);
                        JSONArray valarray=result.getJSONArray(1);
                        valarray.toString().replace("[", "");
                        valarray.toString().replace("]", "");*/
                        /*SONObject jsonObject = new JSONObject(response);
                        JSONArray namearray=jsonObject.names();
                        JSONArray result = jsonObject.toJSONArray(namearray);
                        JSONArray valarray=result.getJSONArray(1);
                        valarray.toString().replace("[", "");
                        valarray.toString().replace("]", "");*/

                        JSONObject jObj = new JSONObject(response);


                        Boolean error=jObj.getBoolean("status");
                      /*  Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();*/
                       /* Boolean error = jObj.getBoolean("error");
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();*/
                        // String error1=jsonObject.getString("error1");
                        //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        if (!error) {
                            JSONArray valarray=jObj.getJSONArray("result");
                            for (int n = 0; n < valarray.length(); n++) {
                                JSONObject searchData = valarray.getJSONObject(n);
                                p = new Model_Searchuser(searchData.getString(AppConfig.KEY_TECHNOLOGY),
                                        searchData.getString("jobdetails"),
                                        searchData.getString("jobqualification"),
                                        searchData.getString("companyname"),
                                        searchData.getString("location"),
                                        searchData.getString("salary"));
                                GOTO="success";
                            }
                            GOTO="success";
                            searchlist.add(p);
                        }
                        else
                        {
                            GOTO="fail";
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getApplicationContext(),"errorMsg:"+errorMsg,Toast.LENGTH_LONG).show();
                            // finish();
                        }
                    } catch (JSONException e) {  e.printStackTrace();  }
                    //adapter.notifyDataSetChanged();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
            requestQueue.add(stringRequest);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ctx);

            pd.setTitle("Retrieving data");

            pd.setMessage("Please wait.");

            pd.setCancelable(true);

            pd.setIndeterminate(true);

            pd.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pd!=null) pd.dismiss();
//            Log.e("size", searchlist.size() + "");
//            adapter.notifyDataSetChanged();
        }
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
                case R.id.linearlayout_qualification:
                   // validatequalification();
                    break;
            }
        }

    }

    private Boolean validatequalification() {
        String qualification = qualification2.getText().toString().trim();

        {
            if (qualification.isEmpty()) {
                textinputlayout_qualificatiopn.setError("Please fillup this field");
                qualification2.requestFocus();
                return false;
            } else {
                textinputlayout_qualificatiopn.setError(" ");
                textinputlayout_qualificatiopn.setErrorEnabled(false);
                return true;
            }
        }
    }


}