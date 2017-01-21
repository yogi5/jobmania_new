package com.youngindia.jobportal.GCM;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.squareup.okhttp.OkHttpClient;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;

import java.util.HashMap;
import java.util.Map;


public class RegistrationIntentService extends IntentService {
    private OkHttpClient client = new OkHttpClient();
    private static final String TAG = "RegistrationIntentService";
    private static final String pathToServer = "http://youngindiagroup.in/jobportal/gcm/registeruser.php";
    String token;
    SessionManager session;
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // Initially this call goes out to the network to retrieve the token, subsequent calls are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            InstanceID instanceID = InstanceID.getInstance(this);
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            session = new SessionManager(getApplicationContext());
            session.storetoken(token);
         //   Log.e(TAG, "GCM Registration Token: " + token);

           // sendRegistrationToServer(token);

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();

        } catch (Exception e) {
            //Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, pathToServer,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("registrationId",token);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
















      /*  RequestBody body = new FormEncodingBuilder()
                .add("registrationId", token)

                .build();
        Request request = new Request.Builder().url(pathToServer).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                //  Log.e(TAG_REGISTER, "Registration error: " + e.getMessage());
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String resp = response.body().string();
//                    Log.v(TAG_REGISTER, resp);
                    System.out.println(resp);
                    if (response.isSuccessful()) {
                    } else {

                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }
        });*/
      /*  final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, bowlingJson(token));
        Request request = new Request.Builder()
                .url(pathToServer)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //Log.d(TAG, "Response");
            Log.d(TAG, "Response" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

   /* private String bowlingJson(String token) {
        return "{'registrationId': " + token + "}";
    }*/

}
