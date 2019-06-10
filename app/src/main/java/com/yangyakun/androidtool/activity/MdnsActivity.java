package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.NsdHelper;

/**
 * @author 92155
 */
public class MdnsActivity extends AppCompatActivity {

    NsdHelper mNsdHelper;

    public static final String TAG = "NsdChat";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Creating chat activity");
        setContentView(R.layout.activity_mdns);
    }

    public void clickAdvertise(View v) {
        // Register service
        mNsdHelper.registerService(8888);
    }

    public void clickDiscover(View v) {
        mNsdHelper.discoverServices();
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "Starting.");
        mNsdHelper = new NsdHelper(this);
        mNsdHelper.initializeNsd();
        super.onStart();
    }


    @Override
    protected void onPause() {
        Log.d(TAG, "Pausing.");
        if (mNsdHelper != null) {
            mNsdHelper.stopDiscovery();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Resuming.");
        super.onResume();
        if (mNsdHelper != null) {
            mNsdHelper.discoverServices();
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Being stopped.");
        mNsdHelper.tearDown();
        mNsdHelper = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Being destroyed.");
        super.onDestroy();
    }

}
