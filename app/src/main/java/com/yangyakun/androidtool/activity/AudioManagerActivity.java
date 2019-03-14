package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.DesityUtil;

import static android.media.AudioManager.FLAG_PLAY_SOUND;

public class AudioManagerActivity extends Activity {

    EditText et_hour;
    EditText et_mines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        et_hour = findViewById(R.id.et_mines);
        et_mines = findViewById(R.id.et_mines);
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

    public void onClose(View view) {
        String hour = et_hour.getText().toString();
        String mines = et_mines.getText().toString();
        Intent intent = new Intent("com.soniq.cybercast.time");
        intent.putExtra("hour", Integer.valueOf(hour));
        intent.putExtra("minute", Integer.valueOf(mines));
        intent.putExtra("mAttribute", 2);
        intent.putExtra("daysOfWeek", 0x7f);
        sendBroadcast(intent);
        //说明：其中属性hour为小时（0-23），minute为分钟（0-59），mAttribute为事件属性，值为1表明为开机，2为关机。
        // daysOfWeek为一周中哪几天需要开关机的属性。值为0x7f，二进制为1111111，表明每天都要执行此事件。
        // 如果我们需要周一到周五才执行此事件，二进制为 001 1111，即将daysOfWeek属性设为0x1f即可。
        // 再如我们需要周六和周日才执行此事件，二进制为110 0000，即将daysOfWeek属性设为0x60即可。
        // 所以，以上代码的意思是增加一个每天20点30分关机的事件。

    }

    public void onCear(View view) {
        Intent intent = new Intent("com.soniq.cybercast.time_delete");
        intent.putExtra("delete_all", true);
        sendBroadcast(intent);
    }

    public void onOpen(View view) {
        String hour = et_hour.getText().toString();
        String mines = et_mines.getText().toString();
        Intent intent = new Intent("com.soniq.cybercast.time");
        intent.putExtra("hour", Integer.valueOf(hour));
        intent.putExtra("minute", Integer.valueOf(mines));
        intent.putExtra("mAttribute", 1);
        intent.putExtra("daysOfWeek", 0x7f);
        sendBroadcast(intent);
        //说明：其中属性hour为小时（0-23），minute为分钟（0-59），mAttribute为事件属性，值为1表明为开机，2为关机。
        // daysOfWeek为一周中哪几天需要开关机的属性。值为0x7f，二进制为1111111，表明每天都要执行此事件。
        // 如果我们需要周一到周五才执行此事件，二进制为 001 1111，即将daysOfWeek属性设为0x1f即可。
        // 再如我们需要周六和周日才执行此事件，二进制为110 0000，即将daysOfWeek属性设为0x60即可。
        // 所以，以上代码的意思是增加一个每天20点30分关机的事件。
    }
}
