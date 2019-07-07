package com.yangyakun.androidtool.activity;

import android.graphics.Path;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.MainRecycleViewAdapter;
import com.yangyakun.androidtool.layoutmanager.PathLayoutManager;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);


        RecyclerView viewById = findViewById(R.id.table);
        viewById.setClickable(true);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            list.add(i+"");
        }
        MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter(list);
        viewById.setAdapter(mainRecycleViewAdapter);

        Path path = new Path();
        path.moveTo(250,250);
        path.rLineTo(600,300);
        path.rLineTo(-600,300);
        path.rLineTo(600,300);
        path.rLineTo(-600,300);

        PathLayoutManager linearLayoutManager= new PathLayoutManager(path,150);

        LinearLayoutManager linearLayoutManager1= new LinearLayoutManager(RecycleViewActivity.this
                ,RecyclerView.VERTICAL,false);
        viewById.setLayoutManager(linearLayoutManager);
    }
}
