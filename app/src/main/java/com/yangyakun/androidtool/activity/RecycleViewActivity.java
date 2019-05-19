package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.recycleview.RecycleView;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);


        RecycleView viewById = findViewById(R.id.table);
        viewById.setClickable(true);
        viewById.setAdapter(new RecycleView.Adapter() {
            @Override
            public View onCreateViewHodler(int postion, View converView, ViewGroup parent) {
                converView = RecycleViewActivity.this.getLayoutInflater().inflate(R.layout.item_table,parent,false);
                TextView text1 = converView.findViewById(R.id.text1);
                text1.setText("第"+postion+"行");
                return converView;
            }

            @Override
            public View onBinderViewHodler(int postion, View converView, ViewGroup parent) {
                TextView text1 = converView.findViewById(R.id.text1);
                text1.setText("网易课堂"+postion);
                return converView;
            }

            @Override
            public int getItemViewType(int row) {
                return 0;
            }

            @Override
            public int getVIewTypeCount() {
                return 1;
            }

            @Override
            public int getCount() {
                return 300;
            }

            @Override
            public int getHeight(int index) {
                return 100;
            }
        });
    }
}
