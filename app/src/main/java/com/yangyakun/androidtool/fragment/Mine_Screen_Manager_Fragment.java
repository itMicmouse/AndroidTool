package com.yangyakun.androidtool.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.yangyakun.androidtool.R;


/**
 * Created by Lihao on 2019-5-6.
 * Email heaolihao@163.com
 */

public class Mine_Screen_Manager_Fragment extends DialogFragment {

    private AgentWeb mAgentWeb;

    public static Mine_Screen_Manager_Fragment newInstance(String s) {
        Mine_Screen_Manager_Fragment newFragment = new Mine_Screen_Manager_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", s);
        newFragment.setArguments(bundle);

        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String host = "http://192.168.30.25:8090/";

        View rootView = inflater.inflate(R.layout.fragment_mine_screen_manager, container, false);
        LinearLayout view = rootView.findViewById(R.id.ll_webview);
        LinearLayout ll_top_main = rootView.findViewById(R.id.ll_top_main);
        mAgentWeb = AgentWeb.with(getActivity())
                .setAgentWebParent((LinearLayout) view, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(host+"cloudscreen");


        mAgentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                System.out.println(view.getUrl());
                if(view.getUrl().contains(host)){
                    ll_top_main.setVisibility(View.GONE);
                }else {
                    ll_top_main.setVisibility(View.VISIBLE);
                }
            }
        });
        ll_top_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAgentWeb.getJsAccessEntrace().quickCallJs("closePage");
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }
}
