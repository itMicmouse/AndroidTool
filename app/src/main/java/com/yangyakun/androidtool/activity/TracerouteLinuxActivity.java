package com.yangyakun.androidtool.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.bean.TracerouteContainer;
import com.yangyakun.androidtool.utils.DesityUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TracerouteLinuxActivity extends AppCompatActivity {

    private EditText et_cmd;
    private EditText et_host;
    private String app_path;
    private TextView tv_result;
    private TextView tv_ips;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traceroute_linux);
        /*初始化控件*/
        et_cmd = findViewById(R.id.et_cmd);
        tv_result = findViewById(R.id.tv_result);
        tv_ips = findViewById(R.id.tv_ips);
        et_host = findViewById(R.id.et_host);
        /* 获取app安装路径 */
        app_path = getApplicationContext().getFilesDir().getAbsolutePath();
    }


    /**
     * 按钮点击事件
     */
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            //拷贝busybox可执行文件
            case R.id.copy_busybox:
                varifyFile(getApplicationContext(), "busybox");
                break;
            //拷贝traceroute可执行文件
            case R.id.copy_traceroute:
                varifyFile(getApplicationContext(), "traceroute");
                break;
            /* 将busybox命令添加到Editext中 */
            case R.id.exe_busybox:
                String cmd = "." + app_path + "/busybox";
                System.out.println(et_cmd);
                et_cmd.setText(cmd);
                break;
            /* 将traceroute命令添加到Editext中 */
            case R.id.exe_traceroute:
                cmd = "." + app_path + "/traceroute";
                et_cmd.setText(cmd);
                break;
            /* 执行Editext中的命令 */
            case R.id.exe:
                cmd = et_cmd.getText().toString();
                /* 执行脚本命令 */
                List<String> results = exe(cmd);
                String result = "";
                /* 将结果转换成字符串, 输出到 TextView中 */
                for (String line : results) {
                    result += line + "\n";
                }
                tv_result.setText(result);
                break;
            /* 将traceroute命令添加到Editext中 */
            case R.id.resolve:
                final String hostName = et_host.getText().toString();
                if (hostName.isEmpty()) {
                    DesityUtil.showToast("请填写域名");
                    return;
                }

                new Thread() {
                    @Override
                    public void run() {
                        final String[] strings = parseHostGetIPAddress(hostName);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (String string : strings) {
                                    stringBuilder.append(string + ":");
                                }
                                tv_ips.setText(stringBuilder);
                            }
                        });
                    }
                }.start();
                break;
            default:
                break;
        }
    }

    /**
     * 验证文件是否存在, 如果不存在就拷贝
     */
    private void varifyFile(Context context, String fileName) {


        try {
            /* 查看文件是否存在, 如果不存在就会走异常中的代码 */
            context.openFileInput(fileName);
        } catch (FileNotFoundException notfoundE) {
            try {
                /* 拷贝文件到app安装目录的files目录下 */
                copyFromAssets(context, fileName, fileName);
                /* 修改文件权限脚本 */
                String script = "chmod 700 " + app_path + "/" + fileName;
                /* 执行脚本 */
                exe(script);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将文件从assets目录中拷贝到app安装目录的files目录下
     */
    private void copyFromAssets(Context context, String source,
                                String destination) throws IOException {
        /* 获取assets目录下文件的输入流 */
        InputStream is = context.getAssets().open(source);
        /* 获取文件大小 */
        int size = is.available();
        /* 创建文件的缓冲区 */
        byte[] buffer = new byte[size];
        /* 将文件读取到缓冲区中 */
        is.read(buffer);
        /* 关闭输入流 */
        is.close();
        /* 打开app安装目录文件的输出流 */
        FileOutputStream output = context.openFileOutput(destination,
                Context.MODE_PRIVATE);
        /* 将文件从缓冲区中写出到内存中 */
        output.write(buffer);
        /* 关闭输出流 */
        output.close();
    }

    /**
     * 解析域名获取IP数组
     *
     * @param host
     * @return
     */
    public String[] parseHostGetIPAddress(String host) {
        String[] ipAddressArr = null;
        try {
            InetAddress[] inetAddressArr = InetAddress.getAllByName(host);
            if (inetAddressArr != null && inetAddressArr.length > 0) {
                ipAddressArr = new String[inetAddressArr.length];
                for (int i = 0; i < inetAddressArr.length; i++) {
                    ipAddressArr[i] = inetAddressArr[i].getHostAddress();
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
        return ipAddressArr;
    }

    /**
     * 执行 shell 脚本命令
     */
    private List<String> exe(String cmd) {
        /* 获取执行工具 */
        Process process = null;
        /* 存放脚本执行结果 */
        List<String> list = new ArrayList<String>();
        try {
            /* 获取运行时环境 */
            Runtime runtime = Runtime.getRuntime();
            /* 执行脚本 */
            process = runtime.exec(cmd);
            /* 获取脚本结果的输入流 */
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            /* 逐行读取脚本执行结果 */
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
