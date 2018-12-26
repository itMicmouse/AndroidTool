package com.yangyakun.androidtool.activity;

import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.stealthcopter.networktools.IPTools;
import com.stealthcopter.networktools.Ping;
import com.stealthcopter.networktools.ping.PingResult;
import com.stealthcopter.networktools.ping.PingStats;
import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.ChatConnection;
import com.yangyakun.androidtool.utils.NsdHelper;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 92155
 */
public class MdnsActivity extends AppCompatActivity {

    NsdHelper mNsdHelper;

    private TextView mStatusView;
    private Handler mUpdateHandler;

    public static final String TAG = "NsdChat";

    ChatConnection mConnection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Creating chat activity");
        setContentView(R.layout.activity_mdns);
        mStatusView = (TextView) findViewById(R.id.status);

        mUpdateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String chatLine = msg.getData().getString("msg");
                addChatLine(chatLine);
            }
        };

    }

    public void clickAdvertise(View v) {
        // Register service
        if(mConnection.getLocalPort() > -1) {
            mNsdHelper.registerService(mConnection.getLocalPort());
        } else {
            Log.d(TAG, "ServerSocket isn't bound.");
        }
    }

    public void clickDiscover(View v) {
        mNsdHelper.discoverServices();
    }

    public void clickConnect(View v) {
        NsdServiceInfo service = mNsdHelper.getChosenServiceInfo();
        if (service != null) {
            Log.d(TAG, "Connecting.");
            mConnection.connectToServer(service.getHost(),
                    service.getPort());
        } else {
            Log.d(TAG, "No service to connect to!");
        }
    }

    public void clickSend(View v) {
        EditText messageView = (EditText) this.findViewById(R.id.chatInput);
        if (messageView != null) {
            String messageString = messageView.getText().toString();
            if (!messageString.isEmpty()) {
                mConnection.sendMessage(messageString);
            }
            messageView.setText("");
        }
    }

    public void addChatLine(String line) {
        mStatusView.append("\n" + line);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting.");
        mConnection = new ChatConnection(mUpdateHandler);

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
        mConnection.tearDown();
        mNsdHelper = null;
        mConnection = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Being destroyed.");
        super.onDestroy();
    }

}