package com.example.otherproess;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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

        if(mNsdHelper==null){
            mNsdHelper = new NsdHelper(this);
            mNsdHelper.initializeNsd();
        }
        mNsdHelper.registerService(8888);
    }
    public void unRegisterService(View v){
        if(mNsdHelper==null){
            return;
        }
        mNsdHelper.tearDown();
        mNsdHelper = null;
    }
    public void clickDiscover(View v) {
        if(mNsdHelper==null){
            mNsdHelper = new NsdHelper(this);
            mNsdHelper.initializeNsd();
        }
        mNsdHelper.discoverServices();
    }
    public void stopDiscover(View view) {
        if(mNsdHelper==null){
            return;
        }
        mNsdHelper.stopDiscovery();
    }
    @Override
    protected void onDestroy() {
        Log.d(TAG, "Being destroyed.");
        if(mNsdHelper!=null){
            mNsdHelper.stopDiscovery();
            mNsdHelper.tearDown();
        }
        super.onDestroy();
    }
}
