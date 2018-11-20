package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.RecycleViewImageAdapter;
import com.yangyakun.androidtool.banner.BannerLayoutManager;
import com.yangyakun.androidtool.banner.CenterSnapHelper;

import java.util.ArrayList;

public class RecycleViewDoubleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        RecyclerView recyclerView = findViewById(R.id.rlv_keshi);
    }
}
