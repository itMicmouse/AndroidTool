package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.RecycleViewAdapter;

import java.util.ArrayList;

public class FlexboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rlv_keshi);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList list = new ArrayList();
        list.add("消化科");
        list.add("心内科");
        list.add("神经内科");
        list.add("呼吸科");
        list.add("内分泌科");
        list.add("妇科");
        list.add("儿科");
        list.add("骨科");
        list.add("泌尿男科");
        list.add("皮肤科");
        list.add("眼科");
        list.add("耳鼻喉科");
        RecycleViewAdapter adapter = new RecycleViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
