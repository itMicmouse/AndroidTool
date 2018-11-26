package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.DesityUtil;

import static android.media.AudioManager.FLAG_PLAY_SOUND;

public class AudioManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
    }

    public void opensettting(View view) {

        AudioManager audiomanage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int current = audiomanage.getStreamVolume(AudioManager.STREAM_SYSTEM);
        audiomanage.setStreamVolume(AudioManager.STREAM_SYSTEM, current + 1, FLAG_PLAY_SOUND);
        int currentt = audiomanage.getStreamVolume(AudioManager.STREAM_SYSTEM);
        DesityUtil.showToast(this, "按了上键" + currentt);
    }

    public void openFlexBox(View view) {
        DesityUtil.showToast(this, "按了下键");
        AudioManager audiomanage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int current = audiomanage.getStreamVolume(AudioManager.STREAM_SYSTEM);
        DesityUtil.showToast(this, "最低音量了啊");
        audiomanage.setStreamVolume(AudioManager.STREAM_SYSTEM, current - 1, FLAG_PLAY_SOUND);
        int currentt = audiomanage.getStreamVolume(AudioManager.STREAM_SYSTEM);
        DesityUtil.showToast(this, "按了下键" + currentt);
    }
}
