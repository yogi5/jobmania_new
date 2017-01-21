package com.youngindia.jobportal;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.android.gms.fitness.data.Application;

/**
 * Created by User on 7/12/2016.
 */
public class jobmania_new extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
