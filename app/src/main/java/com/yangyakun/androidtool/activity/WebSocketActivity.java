package com.yangyakun.androidtool.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.service.SimpleServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebSocketActivity extends AppCompatActivity {

    private SimpleServer simpleServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        simpleServer = new SimpleServer(new InetSocketAddress(8887));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            simpleServer.stop();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openWebSocket(View view) {
        simpleServer.start();
    }
}
