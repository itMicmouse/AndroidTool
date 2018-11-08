package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.RecycleViewAdapter;
import com.yangyakun.androidtool.adapter.RecycleViewImageAdapter;
import com.yangyakun.androidtool.banner.BannerLayoutManager;
import com.yangyakun.androidtool.banner.CenterSnapHelper;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        RecyclerView recyclerView = findViewById(R.id.rlv_keshi);
        BannerLayoutManager layoutManager = new BannerLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList list = new ArrayList();
        list.add("消化科");
        RecycleViewImageAdapter adapter = new RecycleViewImageAdapter(list);
        recyclerView.setAdapter(adapter);

        CenterSnapHelper pagerSnapHelper = new CenterSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
    }
}
