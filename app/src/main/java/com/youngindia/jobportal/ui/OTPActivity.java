package com.youngindia.jobportal.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.Model_SearchFranchise;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OTPActivity extends AppCompatActivity {
EditText edt_code;
    Button btn_submit;
    private ProgressDialog pDialog;
    ProgressDialog pd;
    Model_SearchFranchise p;
    public static ArrayList<Model_SearchFranchise> searchfranchiseuser = new ArrayList<Model_SearchFranchise>();
    LoginUser_Activity loginUser_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.toolbar_backbtn);
        toolbar.setTitle("Verification");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });
        pd=new ProgressDialog(this);
        pDialog=new ProgressDialog(this);
        loginUser_activity=new LoginUser_Activity();
        edt_code=(EditText)findViewById(R.id.edt_code);
        final String data = getIntent().getExtras().getString("keyName");
        final String data1 = getIntent().getExtras().getString("keyName1");
        btn_submit=(Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtcode=edt_code.getText().toString().trim();
                String s="abc";
                if(edtcode.equals(data1))
                {
                    Intent intent = new Intent(getApplicationContext(), Franchiseuser.class);
                    startActivity(intent);
                   // calljson2(data);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Code not match",Toast.LENGTH_LONG).show();
                }
            }
        });

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
                        startActivity(intent);
                    }
                    else
                    {

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
                        hideDialog();
                        Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
