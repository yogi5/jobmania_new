package com.youngindia.jobportal.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.adapter.ListAdapter;
import com.youngindia.jobportal.adapter.NewJob_Adapter;
import com.youngindia.jobportal.adapter.Search_Adapter;

public class Tab1 extends Fragment {
    public String[]job_name={"Fundraiser", "Game Industry", "Genealogist", "Government Jobs", "Hair Stylist", "Human Resources"};
    public String[]job_specification={"Android List View", "Adapter implementation", "Simple List View In Android", "Create List View Android","Android Example", "List View Source Code"};
    public String[]job_skills={"Job views","Job Recommended","New Jobs","Latest Jobs","New Jobs","Latest Jobs"};
    User_jobdetails user_jobdetails;
    NewJob_Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_tab1, container, false);
        ListView listview=(ListView)rootview.findViewById(R.id.listView);
        user_jobdetails= new User_jobdetails();
        //savedInstanceState.clear();

        adapter=new NewJob_Adapter(getActivity(),R.layout.customsearch_layout,user_jobdetails.searchlist_newjob);
      //  search_list.setAdapter(adapter);
        listview.setAdapter(adapter);


        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();


        /*user_jobdetails.finish();*/
    }

   /* @Override
    public void onPause() {
        super.onPause();
        adapter.clear();
    }
*/

}
