package com.yangyakun.androidtool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.yangyakun.androidtool.utils.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 92155
 */
public class UsbBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "≈";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_MEDIA_MOUNTED)){
            Toast.makeText(context, "插入", Toast.LENGTH_LONG).show();
            Uri data = intent.getData();
            File file = null;
            String type = "mp4、3gp、avi、mkv、wmv、mpg、vob、flv、swf、mov";
            try {
                file = new File(new URI(data.toString()));
                if(file.isDirectory()){
                    String[] videos = file.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            if (name.equals("video"))
                                return true;
                            else
                                return false;
                        }
                    });
                    if(videos.length==1) {
                        File videFile = new File(file.getAbsolutePath()+"/"+videos[0]);
                        for (String s : videFile.list()) {
                            if(s.endsWith(".mp4")&&!s.startsWith(".")){
                                System.out.println("视频文件是" + s);
                                FileUtils.copyFile(videFile.getAbsolutePath()+File.separator+s,
                                        "/sdcard/filedownloader/vvideo/"+s,false);
                            }
                        }
                    }
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            System.out.println("挂载的...");
        }else if(action.equals(Intent.ACTION_MEDIA_UNMOUNTED)){
            Toast.makeText(context, "拔出", Toast.LENGTH_LONG).show();
            System.out.println("非挂载......");
        }
    }
}
