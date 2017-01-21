package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;

import android.os.Handler;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.youngindia.jobportal.GCM.MainActivity;
import com.youngindia.jobportal.GCM.QuickstartPreferences;
import com.youngindia.jobportal.GCM.RegistrationIntentService;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.Dialog_Advertisement;
import com.youngindia.jobportal.adapter.Model_Search;
import com.youngindia.jobportal.adapter.Model_SearchFranchise;
import com.youngindia.jobportal.adapter.Model_Search_newJObs;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.database.SqliteHandler;
import com.youngindia.jobportal.model.DefaultDialog;
import com.youngindia.jobportal.ui.app.AppConfig;
import com.youngindia.jobportal.ui.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import info.hoang8f.android.segmented.SegmentedGroup;

public class LoginUser_Activity extends AppCompatActivity implements OnClickListener {
    private ProgressDialog pDialog;
    private SessionManager session;
    private SqliteHandler db;
    Button forgotPassword;
    EditText edt_username,edt_password;
    Button btn_Login;
    String status="Iscandidat";
    AppController mInstance;
    //SegmentedGroup segmented2;
    RadioGroup radioGroup;
    boolean sentToken;
    TextView txt_forgetpsw,txt_registration,txt_createAccount;
    int SPLASH_TIME_OUT=5000;
    String tokenvalue,keyword1;
    private static final String TAG = LoginUser_Activity.class.getSimpleName();
    public static final String MyPREFERENCES2 = "MyPrefs" ;
    SharedPreferences sharedpreferences2;
    public boolean isFirstRun;
    ProgressDialog pd;
    String franchisename,franchiseusername;
    Model_SearchFranchise p;
    Intent myIntent;
    Dialog addWordRemainderDialog;
    RelativeLayout rl;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    RegistrationIntentService r=new RegistrationIntentService();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    String highereducation;
    Dialog dialog;
    public static ArrayList<Model_SearchFranchise> searchfranchiseuser = new ArrayList<Model_SearchFranchise>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_user_);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sentToken = sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
// Toast.makeText(getApplicationContext(),"Token created", Toast.LENGTH_SHORT).show();

                } else {
// Toast.makeText(getApplicationContext(),"Token not created", Toast.LENGTH_SHORT).show();
                }
            }
        };

        if (checkPlayServices()) {
// Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startService(intent);
        }
        dialog = new Dialog(LoginUser_Activity.this,R.style.MyDialog);
        dialog.setContentView(R.layout.dialogsegment);
        dialog.setCancelable(false);
        radioGroup=(RadioGroup) dialog.findViewById(R.id.radiogroup1);
        RadioButton rd1 = (RadioButton) dialog.findViewById(R.id.btncandidate);
        RadioButton rd2 = (RadioButton) dialog.findViewById(R.id.btncompany);
        RadioButton rd3 = (RadioButton) dialog.findViewById(R.id.btnfranchise);
        RadioButton rd4 = (RadioButton) dialog.findViewById(R.id.btndailywagers);
        forgotPassword=(Button)findViewById(R.id.buttonforgotpassword);
        Button btnok=(Button) dialog.findViewById(R.id.btnok);
        dialog.show();


        btnok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                checkFirstRun();
            }
        });

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user2=session.gettoken();
        tokenvalue=user2.get(SessionManager.KEY_TOKEN);
        pd=new ProgressDialog(this);

        HashMap<String, String> user22=session.getstoreedu();
        keyword1= user22.get(SessionManager.KEY_Highereducation1);
// Session manager
        session = new SessionManager(getApplicationContext());
        rl=(RelativeLayout) findViewById(R.id.fullloginactivity);
        edt_username=(EditText)findViewById(R.id.edt_username);
        edt_password=(EditText)findViewById(R.id.edt_password);
        btn_Login=(Button) findViewById(R.id.btn_login);
        pDialog=new ProgressDialog(this);
        mInstance=new AppController();
        // segmented2 = (SegmentedGroup)findViewById(R.id.segmented2);
     //   txt_forgetpsw=(TextView) findViewById(R.id.txt_forget_psw);
        txt_registration=(TextView) findViewById(R.id.txt_newuserregistraation);
        registratontxt_click();
        myIntent = getIntent();

        if(!myIntent.toString().isEmpty())
        {
            if(session.getcompanyflag()==true)
            {
                radioGroup.check(R.id.btncompany);
                status="Iscompany";
                txt_registration.setText("Company Registration Here!!!!");
                registratontxt_click();
                session.setcompanyflag(false);

            }else{
                radioGroup.check(R.id.btncandidate);
                status="Iscandidat";
                registratontxt_click();
            }
            final String username1=(myIntent.getStringExtra("Username"));
            final String password1=( myIntent.getStringExtra("Password"));
            final String status1=( myIntent.getStringExtra("status"));
            edt_username.setText(username1);
            edt_password.setText(password1);
        }
        else
        {
            edt_username.setClickable(true);
            edt_username.setFocusable(true);
            edt_password.setFocusable(true);
        }
//        txt_forgetpsw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(LoginUser_Activity.this,CheckingEmailActivity.class);
//                startActivity(i);
//          //     DefaultDialog dialog =new DefaultDialog();
//             //   dialog.showDialog(LoginUser_Activity.this,"Please Enter your E-Mail associated with your jobby account and we will send you the link to reset your password.");
//
//            }
//        });



        // segmented2.setTintColor(Color.argb(180,255,165,0),Color.BLACK);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.btncandidate) {
                    status="Iscandidat";
                    // segmented2.setTintColor(Color.argb(180,255,165,0),Color.BLACK);
                    if(edt_username.getText().toString().isEmpty() && edt_password.getText().toString().isEmpty())
                    {   edt_username.setText("");
                        edt_password.setText("");
                        txt_registration.setVisibility(View.VISIBLE);
                        rl.setBackgroundResource(R.drawable.imagejob6);
                        txt_registration.setText("Registration Here!!");
                        registratontxt_click();
                    }

                }
                if (checkedId==R.id.btncompany){
                    status="Iscompany";
                    // segmented2.setTintColor(Color.WHITE,Color.BLACK);
                    edt_username.setText("");
                    edt_password.setText("");
                    txt_registration.setVisibility(View.VISIBLE);
                    rl.setBackgroundResource(R.drawable.imagejob7);
                    txt_registration.setText("Company Registration Here!!!!");
                    registratontxt_click();

                }
                if (checkedId==R.id.btndailywagers)
                {
                    //segmented2.setTintColor(Color.argb(100,0,0,139),Color.BLACK);
                    txt_registration.setVisibility(View.INVISIBLE);
                    status="Isdailywager";
                    Intent intent = new Intent(LoginUser_Activity.this, DailyWagesRegstr.class);
                    startActivity(intent);


                }
                if (checkedId==R.id.btnfranchise)
                {
                    // segmented2.setTintColor(Color.argb(170,0,100,0),Color.BLACK);
                    status="IsFranchis";
                    edt_username.setText("");
                    edt_password.setText("");
                    rl.setBackgroundResource(R.drawable.imagejob9);
                    txt_registration.setVisibility(View.INVISIBLE);
                }
            }
        });


// SQLite database handler
        db = new SqliteHandler(getApplicationContext());
        if (session.isLoggedIn()) {
// User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginUser_Activity.this, Employee_HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }


        // Check if user is already logged in or not
        forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot=new Intent(LoginUser_Activity.this,CheckingEmailActivity.class);
                startActivity(forgot);
            }
        });


        btn_Login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

// Intent intent = new Intent(LoginUser_Activity.this, MainActivity.class);
// startActivity(intent);

                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();

// Check for empty data in the form
                if (!username.isEmpty() && !password.isEmpty()) {
                    if(isNetworkAvailable()==true) {
                        if (status.equals("Iscandidat")) {
                            checkLogin(username, password, status,tokenvalue);
                        }
                        else if (status.equals("Iscompany"))
                        {
                            checkcompanyLogin(username, password, status);
                        }
                        else if (status.equals("IsFranchis"))
                        {
                            checkfranchiseLogin(username, password, status);
                        }

                    }
                    else {Toast.makeText(getApplicationContext(),
                            "Please check the Internet Connection!", Toast.LENGTH_LONG)
                            .show();}
                } else {
// Prompt user to enter credentials

                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }


            }
        });
    }


    private void checkfranchiseLogin(final String username, final String password, final String status) {
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

// Check for error node in json
                    if (!error) {
// user successfully logged in
// Create login session
// session.setLogin(true);

// // Now store the user in SQLite
// String uid = jObj.optString("uid");
//
// JSONObject user = jObj.getJSONObject("user");
// String name = user.optString("name");
// String email = user.optString("email");
// String created_at = user
// .optString("created_at");
//
// // Inserting row in users table
// db.addUser(name, email,password, uid, created_at);

/* Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
r.play();
Intent intent1 = new Intent(LoginUser_Activity.this,Employee_HomeActivity.class);
PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
Notification n = new Notification.Builder(getApplicationContext())
.setContentTitle("Notification by Jobportal")
.setContentText("You have successfuly Registered!!!!")
.setSmallIcon(R.drawable.app_icon)
.setContentIntent(pIntent).getNotification();
n.flags=Notification.FLAG_AUTO_CANCEL;
NotificationManager notificationManager =
(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
notificationManager.notify(0, n);*/

//Launch main activity

                        franchisename=jObj.getString("franchisename");
                        final String code=jObj.getString("code");
                        Intent intent = new Intent(LoginUser_Activity.this, FranchiseHome_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("keyName",franchisename);
                        intent.putExtra("keyName1",code);
                        startActivity(intent);
// final Dialog dialog=new Dialog(LoginUser_Activity.this);
// dialog.setTitle("Please select an options:");
// dialog.setContentView(R.layout.customdialogfranchise);
// dialog.show();
// TextView txtview=(TextView) dialog.findViewById(R.id.txtviewdetails);
// txtview.setOnClickListener(new View.OnClickListener() {
// @Override
// public void onClick(View v) {
// calljson2(franchisename);
// dialog.dismiss();
// }
// });
// TextView txtnewuser=(TextView) dialog.findViewById(R.id.txtnewuser);
// txtnewuser.setOnClickListener(new View.OnClickListener() {
// @Override
// public void onClick(View v) {
// dialog.dismiss();
// Intent intent = new Intent(LoginUser_Activity.this, OTPActivity.class);
// intent.putExtra("keyName",franchisename);
// intent.putExtra("keyName1",code);
// startActivity(intent);
// }
// });




// Intent intent = new Intent(LoginUser_Activity.this,
// Franchise_Activity.class);
// startActivity(intent);
// finish();

                    } else {
// Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
// JSON error
                    e.printStackTrace();
// Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Sorry Server is down,Please try after some time", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Sorry Server is down,Please try after some time", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
// Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("status", status);
                return params;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);


    }
    public void onBackPressed() {
        Intent i = new Intent(LoginUser_Activity.this, LoginUser_Activity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("EXIT", true);
        startActivity(i);
        //dialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    private void registratontxt_click() {
        if(status.equals("Iscandidat")){
            radioGroup.check(R.id.btncandidate);
            txt_registration.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginUser_Activity.this, UserRegistration.class);
//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }
        else{
            radioGroup.check(R.id.btncompany);
            txt_registration.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginUser_Activity.this,Company_registration.class);
//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }
    }

    private void checkLogin(final String username, final String password,final String status,final String tokenvalue) {
// Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

// Check for error node in json
                    if (!error) {
// user successfully logged in
//Create login session
//;
// // Now store the user in SQLite
// String uid = jObj.optString("uid");
//
// JSONObject user = jObj.getJSONObject("user");
// String name = user.optString("name");
// String email = user.optString("email");
// String created_at = user
// .optString("created_at");
//
// // Inserting row in users table
// db.addUser(name, email,password, uid, created_at);

// final Handler handler = new Handler();
// handler.postDelayed(new Runnable() {
// @Override
// public void run() {
//
// Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
// Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
// r.play();
// Intent intent1 = new Intent(LoginUser_Activity.this,User_jobdetails.class);
// intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
// Notification n = new Notification.Builder(getApplicationContext())
// .setContentTitle("Notification by Jobportal")
// .setContentText("jobs recommend for you!!!!")
// .setSmallIcon(R.drawable.app_icon)
// .setContentIntent(pIntent).getNotification();
// n.flags=Notification.FLAG_AUTO_CANCEL;
// NotificationManager notificationManager =
// (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// notificationManager.notify(0, n);
//
// }
// }, 10000);


//Launch main activity
                        String value=jObj.getString("status");
                        if(status.equals("Iscandidat")) {
                            String username=jObj.getString("name");
                            String username1=jObj.getString("username");
                            String sponsoredname=jObj.getString("sponsoredname");
                            String sponsoredimage=jObj.getString("sponsoredimage");
                            String count=jObj.getString("count");
                            highereducation=jObj.getString("higheredu");
/*int update_percentae=jObj.getInt("com");
session.setpercentage(update_percentae);*/
//String highereducation1=jObj.getString("highereducation1");
                            if(count.equals("active")) {
                                if (sponsoredimage.isEmpty()) {
                                    session.setLogin(true);
                                    Intent intent = new Intent(LoginUser_Activity.this, Employee_HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Dialog_Advertisement showAdvertisment = new Dialog_Advertisement();
                                    Bitmap btm = StringToBitMap(sponsoredimage);
                                    showAdvertisment.showDialog(LoginUser_Activity.this, "Your profile has been sponsored by:" + sponsoredname, btm);
                                    final Handler handler1 = new Handler();
                                    handler1.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            session.setLogin(true);
                                            Intent intent = new Intent(getApplicationContext(),
                                                    Employee_HomeActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    }, 3000);
                                }
                            }else {
                                if (sponsoredimage.isEmpty()) {
                                    session.setLogin(true);
                                    Intent intent = new Intent(LoginUser_Activity.this, Employee_RegistrationActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Dialog_Advertisement showAdvertisment = new Dialog_Advertisement();
                                    Bitmap btm = StringToBitMap(sponsoredimage);
                                    showAdvertisment.showDialog(LoginUser_Activity.this, "Your profile has been sponsored by:" + sponsoredname, btm);
                                    final Handler handler1 = new Handler();
                                    handler1.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            session.setLogin(true);
                                            Intent intent = new Intent(getApplicationContext(),
                                                    Employee_RegistrationActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);


                                        }
                                    }, 3000);
                                }
                            }

                            session.storeuser(username);
                            session.storeusername(username1);
                            session.storeedu(highereducation);
// checkFirstRun();

/* Intent intent = new Intent(getApplicationContext(),
Employee_HomeActivity.class);
startActivity(intent);*/
                            checknewjobdata(highereducation);

                        }
                        if(status.equals("Iscompany"))
                        {
                            Intent intent = new Intent(LoginUser_Activity.this,
                                    Company_Base.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
//checkFirstRun();

                        }
/* if(status.equals("IsFranchis"))
{
franchisename=jObj.getString("franchisename");
//calljson2();

// Intent intent = new Intent(LoginUser_Activity.this,
// Franchise_Activity.class);
// startActivity(intent);
// finish();
}*/
                    } else {
// Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
// JSON error
                    e.printStackTrace();
// Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Sorry Server is down,Please try after some time", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Sorry Server is down,Please try after some time", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
// Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("status", status);
                params.put("token", tokenvalue);
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
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

    }

    private void checknewjobdata(String highereducation) {
        String url = AppConfig.NOTIFICATIONTEST+"jobqualification="+highereducation;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jObj = new JSONObject(response);
                    String error=jObj.getString("notify");
                    if(error.equals("valid"))
                    {
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        Intent intent1 = new Intent(LoginUser_Activity.this,User_jobdetails.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
                        Notification n = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Notification by Jobby")
                                .setContentText("jobs recommend for you!!!!")
                                .setSmallIcon(R.drawable.app_icon)
                                .setContentIntent(pIntent).getNotification();
                        n.flags=Notification.FLAG_AUTO_CANCEL;
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0, n);
/* final Handler handler = new Handler();
handler.postDelayed(new Runnable() {
@Override
public void run() {

Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
r.play();
Intent intent1 = new Intent(LoginUser_Activity.this,User_jobdetails.class);
intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
Notification n = new Notification.Builder(getApplicationContext())
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
                } catch (JSONException e) { e.printStackTrace(); }
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

    private void checkcompanyLogin(final String username, final String password,final String status) {
// Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_COMPANYLOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

// Check for error node in json
                    if (!error) {
// user successfully logged in
// Create login session
// session.setLogin(true);

// // Now store the user in SQLite
// String uid = jObj.optString("uid");
//
// JSONObject user = jObj.getJSONObject("user");
// String name = user.optString("name");
// String email = user.optString("email");
// String created_at = user
// .optString("created_at");
//
// // Inserting row in users table
// db.addUser(name, email,password, uid, created_at);


//Launch main activity
/* Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
r.play();
Intent intent1 = new Intent(LoginUser_Activity.this,Company_Base.class);
PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
Notification n = new Notification.Builder(getApplicationContext())
.setContentTitle("Notification by Jobportal")
.setContentText("You have successfuly Registered!!!!")
.setSmallIcon(R.drawable.app_icon)
.setContentIntent(pIntent).getNotification();
n.flags=Notification.FLAG_AUTO_CANCEL;
NotificationManager notificationManager =
(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
notificationManager.notify(0, n);
*/

                        String username=jObj.getString("companyname");
                        session.storeuser(username);
                        String companylocation=jObj.getString("companylocation");
                        session.storecompanyloc(companylocation);
//session.setLogin(true);
                        Intent intent = new Intent(LoginUser_Activity.this,
                                Company_Base.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
//checkFirstRun();
                        checknotification(username);

// Intent intent = new Intent(LoginUser_Activity.this,
// Franchise_Activity.class);
// startActivity(intent);
// finish();
                    }
                    else {
// Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
// JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
// Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("status", status);
                return params;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

    }

    private void checknotification(final String username) {
        String url = AppConfig.NOTIFICATIONCOMPANY+"companyname="+username;
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
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        Intent intent1 = new Intent(LoginUser_Activity.this,Companyuserapplied.class);
                        intent1.putExtra("companyname",username);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);
                        Notification n = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Notification by Jobby")
                                .setContentText("User enrolled for you!!!!")
                                .setSmallIcon(R.drawable.app_icon)
                                .setContentIntent(pIntent).getNotification();
                        n.flags=Notification.FLAG_AUTO_CANCEL;
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0, n);
                    }
                    else
                    {
                        String errorMsg = jObj.getString("error_msg");
                    }
                } catch (JSONException e) { e.printStackTrace(); }
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

    public void calljson2(String franchisename) {
        String url = AppConfig.URL_FRANCHISE+"franchisename="+franchisename;
        url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    Boolean error=jObj.getBoolean("status");
                    if (!error) {
                        JSONArray valarray=jObj.getJSONArray("result");
                        for (int n = 0; n < valarray.length(); n++) {
                            JSONObject searchData = valarray.getJSONObject(n);
                            p = new Model_SearchFranchise(searchData.getString(AppConfig.KEY_NAME),
                                    searchData.getString(AppConfig.KEY_USERNAME),
                                    searchData.getString(AppConfig.KEY_MOBILE),searchData.getString(AppConfig.KEY_LOGINSTATUS));
                            searchfranchiseuser.add(p);
                        }
                        Intent intent = new Intent(getApplicationContext(), Franchise_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else
                    {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),"errorMsg:"+errorMsg,Toast.LENGTH_LONG).show();
// finish();
                    }
                } catch (JSONException e) { e.printStackTrace(); }
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
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onStart() {
        super.onStart();
//// if(bt==null) {
// bt = new BackTask();
// bt.execute();
// // }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void checkFirstRun() {
        isFirstRun = getSharedPreferences("PREFERENCE2", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){
// startActivity(new Intent(LoginUser_Activity.this, Employee_RegistrationActivity.class));
            getSharedPreferences("PREFERENCE2", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .commit();
// if(!myIntent.toString().isEmpty())
// {
// final String username1=(myIntent.getStringExtra("Username"));
// final String password1=( myIntent.getStringExtra("Password"));
// final String status1=( myIntent.getStringExtra("status"));
// edt_username.setText(username1);
// edt_password.setText(password1);
// }
            addWordRemainderDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            addWordRemainderDialog.setContentView(R.layout.loginadd_dialog);

            ImageView addWordRemainderImage = (ImageView) addWordRemainderDialog.findViewById(R.id.addwordremainderImage);
            //addWordRemainderImage.setImageResource(R.drawable.homepage);

            addWordRemainderDialog.show();

            addWordRemainderImage.setOnClickListener(this);

        }
    }
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }


    @Override
    public void onClick(View v) {
        addWordRemainderDialog.dismiss();
    }

}