package com.yangyakun.androidtool.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.net.GitHubService;
import com.yangyakun.androidtool.net.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> itMicmouse = service.listRepos("itMicmouse");

        itMicmouse.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                System.out.println("failure");
            }
        });

    }
}
