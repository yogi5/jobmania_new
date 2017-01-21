package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.CompanylogoChange;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.ImageAdapter;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_base.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_base#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_base extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Employee_HomeActivity employee_homeActivity;
    public Integer[] mThumbIds = {
            R.drawable.job_status,
            R.drawable.job_update, R.drawable.job_find,
    };
    public String[] jobtype = {"Job search", "New Jobs", "Applied Jobs"};
    private OnFragmentInteractionListener mListener;
    SessionManager session;
    ProgressBar pb;
    TextView tv;
    int prg = 0;
    ImageView profilepic;
    frgmnt_setting frgmnt_setting;
    Button b1, b2, b3;
    ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;
    public fragment_base() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_base.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_base newInstance(String param1, String param2) {
        fragment_base fragment = new fragment_base();
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

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_fragment_base, container, false);
        TextView user_name = (TextView) rootView.findViewById(R.id.user_name);
        profilepic = (ImageView) rootView.findViewById(R.id.user_profilepic);
        b1 = (Button) rootView.findViewById(R.id.button);
        b2 = (Button) rootView.findViewById(R.id.button2);
        b3 = (Button) rootView.findViewById(R.id.button3);
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frgmnt_setting = new frgmnt_setting();
                FragmentTransaction fragmenttransaction = employee_homeActivity.getSupportFragmentManager().beginTransaction();
                fragmenttransaction.replace(R.id.frame_container, frgmnt_setting);
                fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmenttransaction.addToBackStack("tag21").commit();
//                Intent intent=new Intent(getActivity(), CompanylogoChange.class);
//                startActivity(intent);
            }
        });
        TextView user_qualification = (TextView) rootView.findViewById(R.id.user_qualification);
        session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user1 = session.getUsername();
        String username = user1.get(SessionManager.KEY_NAME);
        user_name.setText(username);
        HashMap<String, String> user3 = session.getUsername1();
        String username1 = user3.get(SessionManager.KEY_NAME2);
        HashMap<String, String> user2 = session.getstoreedu();
        String qualification = user2.get(SessionManager.KEY_Highereducation1);
        tv = (TextView) rootView.findViewById(R.id.tvId);
        user_qualification.setText(qualification);
        pb = (ProgressBar) rootView.findViewById(R.id.pbId);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Search_Activity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AppliedJobs.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), User_jobdetails.class);
                startActivity(intent);
            }
        });

        progressbarvalue(username1);
//        new Thread(myThread).start();
      /*  final GridView gridview = (GridView)rootView.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(employee_homeActivity,jobtype,mThumbIds));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(getActivity(),Search_Activity.class);
                    startActivity(intent);
                }
                else if(position==1) {
                    Intent intent=new Intent(getActivity(),User_jobdetails.class);
                    startActivity(intent);
                }  else if(position==2) {
                    Intent intent=new Intent(getActivity(),AppliedJobs.class);
                    startActivity(intent);
                }
            }
        });*/
        return rootView;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        employee_homeActivity = (Employee_HomeActivity)context;
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


    public void onAttach(Activity activity) {
        super.onAttach(activity);
        employee_homeActivity = (Employee_HomeActivity) activity;
    }

    private void progressbarvalue(String userneme) {
        String url = AppConfig.PRGRESBARVAL + "username=" + userneme;
      //  String url = AppConfig.PRGRESBARVAL + "username1=" + userneme;
       // String url = AppConfig.COMPANYLOGO + "username=" + userneme;
        url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    Boolean error = jObj.getBoolean("status");
                    final Integer progressintval = jObj.getInt("com");
                    String userpic = jObj.getString("userprofilepic");
                    new LoadImage().execute(userpic);
                    //Bitmap bitmap22=StringToBitMap(userpic);
                    //profilepic.setImageBitmap(bitmap22);
                    pb.setProgress(progressintval);
                    tv.setText(progressintval + "% Profile completed");
                    if (!error) {
                        Runnable myThread = null;
                        new Thread(myThread).start();
                        myThread = new Runnable() {
                            @Override
                            public void run() {
                                while (prg < 100) {
                                    try {
                                        hnd.sendMessage(hnd.obtainMessage());
                                        Thread.sleep(60);
                                    } catch (InterruptedException e) {
                                        Log.e("ERROR", "Thread was Interrupted");
                                    }
                                }

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        tv.setText("Finished");
                                    }
                                });
                            }

                            Handler hnd = new Handler() {
                                @Override
                                public void handleMessage(Message msg) {
                                    prg++;
                                    pb.setProgress(progressintval);
                                    tv.setText(progressintval + "% Profile completed");
                                }
                            };
                        };

                    } else {
                        String errorMsg = jObj.getString("error_msg");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void runOnUiThread(Runnable finished) {
    }


    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap11 = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap11;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }

        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {
                profilepic.setImageBitmap(image);
                pDialog.dismiss();

            } else {

                pDialog.dismiss();
                //Toast.makeText(getActivity(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
}