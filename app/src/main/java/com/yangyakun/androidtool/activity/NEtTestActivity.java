package com.yangyakun.androidtool.activity;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stealthcopter.networktools.IPTools;
import com.stealthcopter.networktools.Ping;
import com.stealthcopter.networktools.ping.PingResult;
import com.stealthcopter.networktools.ping.PingStats;
import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.DesityUtil;
import com.yangyakun.androidtool.utils.NetUtil;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NEtTestActivity extends AppCompatActivity {

    private TextView resultText;
    private EditText editIpAddress;
    private EditText edit_ip_route;
    private EditText edit_ip_prefix_length;
    private EditText edit_ip_dns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_test);
        InetAddress ipAddress = IPTools.getLocalIPv4Address();

        resultText = findViewById(R.id.resultText);
        editIpAddress = findViewById(R.id.editIpAddress);
        edit_ip_route = findViewById(R.id.edit_ip_route);
        edit_ip_prefix_length = findViewById(R.id.edit_ip_prefix_length);
        edit_ip_dns = findViewById(R.id.edit_ip_dns);

        initData();

        if (ipAddress != null) {
            editIpAddress.setText(ipAddress.getHostAddress());
        }

        findViewById(R.id.pingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            doPing();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        findViewById(R.id.wolButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("");
            }
        });
        findViewById(R.id.btn_set_ip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWifi();
            }
        });

    }

    private WifiManager my_wifiManager;
    private WifiInfo wifiInfo;
    private DhcpInfo dhcpInfo;


    private void initData() {
        my_wifiManager = ((WifiManager) getSystemService("wifi"));
        dhcpInfo = my_wifiManager.getDhcpInfo();
        wifiInfo = my_wifiManager.getConnectionInfo();

        StringBuilder sb = new StringBuilder();
        sb.append("网络信息：");
        sb.append("\nip：" + intToIp(dhcpInfo.ipAddress));
        sb.append("\n子网掩码：" + intToIp(dhcpInfo.netmask));
        sb.append("\n路由器(网关)：" + intToIp(dhcpInfo.gateway));
        sb.append("\nserverAddress：" + intToIp(dhcpInfo.serverAddress));
        sb.append("\ndns1：" + intToIp(dhcpInfo.dns1));
        sb.append("\ndns2：" + intToIp(dhcpInfo.dns2));
        sb.append("\n");

        sb.append("Wifi信息：");
        sb.append("\nIpAddress：" + intToIp(wifiInfo.getIpAddress()));
        sb.append("\nMacAddress：" + wifiInfo.getMacAddress());
        resultText.setText(sb);

    }

    private String intToIp(int paramInt) {
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "."
                + (0xFF & paramInt >> 24);
    }

    private void setWifi() {

        String ipv4 = editIpAddress.getText().toString();
        String regEx = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ipv4);
        if (!matcher.matches()) {
            DesityUtil.showToast("请输入正确ip");
            return;
        }



        WifiConfiguration wifiConfig = null;
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration conf : configuredNetworks) {
            if (conf.networkId == connectionInfo.getNetworkId()) {
                wifiConfig = conf;
                break;
            }
        }
        try {
            NetUtil.setStaticIpConfiguration(wifiManager, wifiConfig, InetAddress.getByName("192.168.0.202"), 24, InetAddress.getByName("192.168.0.202"), InetAddress.getAllByName("192.168.0.202"));
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void doPing() {
        String ipAddress = editIpAddress.getText().toString();

        if (TextUtils.isEmpty(ipAddress)) {
            appendResultsText("Invalid Ip Address");
            return;
        }

        // Perform a single synchronous ping
        PingResult pingResult = null;
        try {
            pingResult = Ping.onAddress(ipAddress).setTimeOutMillis(1000).doPing();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        appendResultsText("Pinging Address: " + pingResult.getAddress().getHostAddress());
        appendResultsText("HostName: " + pingResult.getAddress().getHostName());
        appendResultsText(String.format("%.2f ms", pingResult.getTimeTaken()));


        // Perform an asynchronous ping
        Ping.onAddress(ipAddress).setTimeOutMillis(1000).setTimes(5).doPing(new Ping.PingListener() {
            @Override
            public void onResult(PingResult pingResult) {
                appendResultsText(String.format("%.2f ms", pingResult.getTimeTaken()));
            }

            @Override
            public void onFinished(PingStats pingStats) {
                appendResultsText(String.format("Pings: %d, Packets lost: %d",
                        pingStats.getNoPings(), pingStats.getPacketsLost()));
                appendResultsText(String.format("Min/Avg/Max Time: %.2f/%.2f/%.2f ms",
                        pingStats.getMinTimeTaken(), pingStats.getAverageTimeTaken(), pingStats.getMaxTimeTaken()));
            }

            @Override
            public void onError(Exception e) {
                // TODO: STUB METHOD
            }
        });

    }

    private void appendResultsText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultText.append(text + "\n");
            }
        });
    }

    public void clearText(View view) {
        resultText.setText("");
    }
}
