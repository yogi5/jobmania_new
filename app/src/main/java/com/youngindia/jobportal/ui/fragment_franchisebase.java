package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.ImageAdapter;
import com.youngindia.jobportal.adapter.Model_SearchFranchise;
import com.youngindia.jobportal.ui.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_franchisebase#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_franchisebase extends Fragment {
    FranchiseHome_Activity franchisehome;
    ProgressDialog pDialog;
    LoginUser_Activity loginUser_activity;
    private OnFragmentInteractionListener mListener;
    ProgressBar pb;
    TextView tv;
    int prg = 0;
    public Integer[] mThumbIds = {
            R.drawable.job_status, R.drawable.job_recommended
    };
    Model_SearchFranchise p;
    public String[]jobtype={"View Details ","Register new User"};
    public static ArrayList<Model_SearchFranchise> searchfranchiseuser = new ArrayList<Model_SearchFranchise>();
    public fragment_franchisebase() {
        // Required empty public constructor
    }
    public static fragment_franchisebase newInstance(String param1, String param2) {
        fragment_franchisebase fragment = new fragment_franchisebase();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_fragment_franchisebase, container, false);
        loginUser_activity=new LoginUser_Activity();
        pDialog=new ProgressDialog(getActivity());
        pb = (ProgressBar) rootView.findViewById(R.id.pbId);
        tv = (TextView)rootView.findViewById(R.id.tvId);
        new Thread(myThread).start();
        final GridView gridview = (GridView)rootView.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(franchisehome,jobtype,mThumbIds));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position==0)
                {
                    franchisehome.calljson2(franchisehome.data);
                }
                else {
                    Intent intent = new Intent(franchisehome, OTPActivity.class);
                                intent.putExtra("keyName",franchisehome.data);
                                intent.putExtra("keyName1",franchisehome.data1);
                                startActivity(intent);
                }
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private Runnable myThread = new Runnable()
    {
        @Override
        public void run()
        {
            while (prg < 60)
            {
                try
                {
                    hnd.sendMessage(hnd.obtainMessage());
                    Thread.sleep(60);
                }
                catch(InterruptedException e)
                {
                    Log.e("ERROR", "Thread was Interrupted");
                }
            }

            runOnUiThread(new Runnable() {
                public void run() {
                    tv.setText("Finished");
                }
            });
        }

        Handler hnd = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                prg++;
                pb.setProgress(prg);

                String perc = String.valueOf(prg).toString();
                tv.setText(perc+"% Profile completed");
            }
        };
    };

    private void runOnUiThread(Runnable finished) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        franchisehome=(FranchiseHome_Activity) activity;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//    public void calljson2(String franchisename) {
//        String url = AppConfig.URL_FRANCHISE+"franchisename="+franchisename;
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response)
//            {
//               //hideDialog();
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    Boolean error=jObj.getBoolean("status");
//                    if (!error) {
//                        JSONArray valarray=jObj.getJSONArray("result");
//                        for (int n = 0; n < valarray.length(); n++) {
//                            JSONObject searchData = valarray.getJSONObject(n);
//                            p = new Model_SearchFranchise(searchData.getString(AppConfig.KEY_NAME),
//                                    searchData.getString(AppConfig.KEY_USERNAME),
//                                    searchData.getString(AppConfig.KEY_MOBILE),searchData.getString(AppConfig.KEY_LOGINSTATUS));
//                            searchfranchiseuser.add(p);
//                        }
//                        Intent intent = new Intent(getActivity().getApplicationContext(), Franchise_Activity.class);
//                        startActivity(intent);
//                    }
//                    else
//                    {
//
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getActivity().getApplicationContext(),"errorMsg:"+errorMsg,Toast.LENGTH_LONG).show();
//                        // finish();
//                    }
//                } catch (JSONException e) {  e.printStackTrace();  }
//                //adapter.notifyDataSetChanged();
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    //    hideDialog();
//                        Toast.makeText(getActivity().getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        requestQueue.add(stringRequest);
//    }

    public void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
