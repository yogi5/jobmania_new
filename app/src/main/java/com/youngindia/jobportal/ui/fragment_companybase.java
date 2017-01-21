package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youngindia.jobportal.CompanylogoChange;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.SearchCompany;
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
 * {@link fragment_companybase.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class fragment_companybase extends Fragment {
    Company_Base employeer_homeActivity;
    SessionManager session;
    Button b1,b2,b3;
    ProgressDialog pDialog;
    Bitmap bitmap;
    ImageView profilepic;
    public Integer[] mThumbIds = {
            R.drawable.post_job, R.drawable.search_candidate,
            R.drawable.shotlist, R.drawable.recive_aplicnt,
    };
    TextView company_name,company_location;
    public String[]jobtype={"Post a  Job","Candidate Search","Shortlisted Candidate","Received Candidatelist"};

    private OnFragmentInteractionListener mListener;

    public fragment_companybase() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_fragment_companybase, container, false);
        company_name=(TextView)rootView.findViewById(R.id.company_name);
        company_location=(TextView)rootView.findViewById(R.id.company_location);
        session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user1=session.getUsername();
        String username = user1.get(SessionManager.KEY_NAME);
        company_name.setText(username);
        HashMap<String, String> user11=session.getCompanylocation();
        String comploc = user11.get(SessionManager.KEY_COMPLOC);
        company_location.setText(comploc);
        b1=(Button)rootView.findViewById(R.id.button);
        b2=(Button)rootView.findViewById(R.id.button2);
        b3=(Button)rootView.findViewById(R.id.button3);
        profilepic = (ImageView) rootView.findViewById(R.id.user_profilepic);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchCompany.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CompanyDetail.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CompanyShortlisted.class);
                startActivity(intent);
            }
        });
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CompanylogoChange.class);
                startActivity(intent);
            }
        });
        progressbarvalue(username);
       /* final GridView gridview = (GridView)rootView.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(employeer_homeActivity,jobtype,mThumbIds));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(getActivity(),CompanyDetail.class);

                    startActivity(intent);
                }
                if(position==1)
                {
                    Intent intent=new Intent(getActivity(),SearchCompany.class);
                    startActivity(intent);
                }
                if(position==2|| position==3)
                {
                    Intent intent=new Intent(getActivity(),Company_candidatelist.class);
                    startActivity(intent);
                }

            }
        });*/
        return rootView;
    }
    private void progressbarvalue(String userneme) {
        String url = AppConfig.COMPANYLOGO + "username=" + userneme;
        url=url.replaceAll(" ", "%20");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);

                    String userpic = jObj.getString("userprofilepic");
                    if(userpic.isEmpty()) {

                    }
                    else
                    {
                        new LoadImage().execute(userpic);
                    }
                    //Bitmap bitmap22=StringToBitMap(userpic);
                    //profilepic.setImageBitmap(bitmap22);



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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        employeer_homeActivity=(Company_Base) activity;
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
