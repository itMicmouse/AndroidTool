package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.MainRecycleViewAdapter;

import java.util.ArrayList;

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
                ,RecyclerView.VERTICAL,false);
        viewById.setLayoutManager(linearLayoutManager);
    }
}
