package com.syed.humantask.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;


public class ApplicationController extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    private static ApplicationController mApplicationInstance;

    private Activity activity;
    private boolean mIsNetworkConnected;

    private Bitmap image;
    private Context context;

    public static ApplicationController getApplicationInstance() {
        if (mApplicationInstance == null)
            mApplicationInstance = new ApplicationController();
        return mApplicationInstance;
    }


    public Activity getActivity() {
        return activity;
    }

    /**
     * Method to tell the state of internet connectivity
     *
     * @return State of internet
     */
    public boolean isNetworkConnected() {
        return mIsNetworkConnected;
    }

    public void setIsNetworkConnected(boolean mIsNetworkConnected) {
        this.mIsNetworkConnected = mIsNetworkConnected;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        mApplicationInstance = this;
        context = getApplicationContext();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity != null)
            this.activity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    public Context getContext() {
        return context;
    }
}
