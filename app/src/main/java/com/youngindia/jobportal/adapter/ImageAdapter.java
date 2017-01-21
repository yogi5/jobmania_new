package com.youngindia.jobportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngindia.jobportal.R;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public Integer[] mThumbIds;
    public String[]jobtype;

    public ImageAdapter(Context mContext, String[] jobtype, Integer[] mThumbIds) {
        this.mContext = mContext;
        this.jobtype = jobtype;
        this.mThumbIds = mThumbIds;
    }


    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(jobtype[position]);
            imageView.setImageResource(mThumbIds[position]);
            Animation anim = AnimationUtils.loadAnimation(mContext,R.anim.slide_down);

            // By default all grid items will animate together and will look like the gridview is
            // animating as a whole. So, experiment with incremental delays as below to get a
            // wave effect.
            anim.setStartOffset((position*3) *100);
            grid.setAnimation(anim);
            anim.start();
        } else {
            grid = (View) convertView;
        }

        return grid;
    }

}
