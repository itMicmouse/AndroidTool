package com.yangyakun.androidtool.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.ViewPagerAdapter;

import java.util.ArrayList;


public class ViewPager2Activity extends AppCompatActivity {

    private ViewPager2 viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        viewById = findViewById(R.id.view_pager2);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i+"1");
        }

        viewById.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewById.setAdapter(new ViewPagerAdapter());
    }
}
