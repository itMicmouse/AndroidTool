package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.MainRecycleViewAdapter;
import com.yangyakun.androidtool.recycleview.widget.LinearLayoutManager;
import com.yangyakun.androidtool.recycleview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);


        RecyclerView viewById = findViewById(R.id.table);
        viewById.setClickable(true);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i+"1");
        }
        MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter(list);
        viewById.setAdapter(mainRecycleViewAdapter);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(RecycleViewActivity.this
                ,LinearLayoutManager.VERTICAL,false);
        viewById.setLayoutManager(linearLayoutManager);
    }
}
