package com.yangyakun.androidtool.activity;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.StaticIpUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class WifiSetting extends AppCompatActivity {

    Button aaa, aaa2, aaa3, aaa4, aaa5, aaa6;
    long netmaskIpL;
    StaticIpUtil s;
    WifiConfiguration mWifiConfiguration;
    private static final int SELECTED_PREMMSION_STORAGE = 6;
    private WifiManager mwifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        aaa = (Button) findViewById(R.id.aaa);
        aaa.setOnClickListener(ccc);

        aaa2 = (Button) findViewById(R.id.aaa2);
        aaa2.setOnClickListener(ccc);

        aaa3 = (Button) findViewById(R.id.aaa3);
        aaa3.setOnClickListener(ccc);

        aaa4 = (Button) findViewById(R.id.aaa4);
        aaa4.setOnClickListener(ccc);

        aaa5 = (Button) findViewById(R.id.aaa5);
        aaa5.setOnClickListener(ccc);

        aaa6 = (Button) findViewById(R.id.aaa6);
        aaa6.setOnClickListener(ccc);

        s = new StaticIpUtil(WifiSetting.this);
        mwifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

    }

    View.OnClickListener ccc = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aaa:
                    //IP 网络前缀长度24 DNS1域名1 网关
                    Boolean b = s.setIpWithTfiStaticIp(false, "192.168.1.123", 24, "255.255.255.0", "192.168.1.1");
                    Toast.makeText(WifiSetting.this, "" + b, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.aaa2:
                    //IP 网络前缀长度24 DNS1域名1 网关
                    Boolean c = s.setIpWithTfiStaticIp(true, "192.168.1.123", 24, "255.255.255.0", "192.168.1.1");
                    Toast.makeText(WifiSetting.this, "" + c, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.aaa3:
                    WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                    WifiInfo w = wifiManager.getConnectionInfo();
                    String ip = intToIp(w.getIpAddress());
                    Log.e("IP", ip);
                    Toast.makeText(WifiSetting.this, ip, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.aaa4:
                    Toast.makeText(WifiSetting.this, getWifiSetting(WifiSetting.this), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.aaa5:
                    String ssid = "zhiyi01-4G";
                    String password = "11111111";
                    mWifiConfiguration = CreateWifiInfo(ssid, password, 3);
                    System.out.println("mWifiConfiguration" + mWifiConfiguration);
                    int wcgID = mwifiManager.addNetwork(mWifiConfiguration);
                    boolean bbb = mwifiManager.enableNetwork(wcgID, true);
                    Log.e("wcgID", "" + wcgID);
                    Log.e("b", "" + bbb);
                    break;

                case R.id.aaa6:
                    try {
                        setStaticIpConfiguration(mwifiManager, mWifiConfiguration,
                                InetAddress.getByName("106.168.0.235"), 24,
                                InetAddress.getByName("192.168.0.202"),
                                InetAddress.getAllByName("8.8.8.8"));
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

                    break;
                default:
                    break;

            }
        }
    };


    public String getWifiSetting(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        netmaskIpL = dhcpInfo.netmask;
        if (dhcpInfo.leaseDuration == 0) {
            return "StaticIP";
        } else {
            return "DHCP";
        }
    }


    public WifiConfiguration CreateWifiInfo(String SSID, String Password,
                                            int Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";

        WifiConfiguration tempConfig = this.IsExsits(SSID);
        if (tempConfig != null) {

            Boolean c = mwifiManager.removeNetwork(tempConfig.networkId);
            Log.e("创建新的", "" + c);
        }

        if (Type == 1) // WIFICIPHER_NOPASS
        {
            config.wepKeys[0] = "";
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 2) // WIFICIPHER_WEP
        {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers
                    .set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 3) // WIFICIPHER_WPA
        {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }


    // 判断曾经连接过得WiFi中是否存在指定SSID的WifiConfiguration
    public WifiConfiguration IsExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = mwifiManager
                .getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            System.out.println("existingConfig" + existingConfig.SSID);
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public static void setStaticIpConfiguration(WifiManager manager,
                                                WifiConfiguration config, InetAddress ipAddress, int prefixLength,
                                                InetAddress gateway, InetAddress[] dns)
            throws ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, NoSuchFieldException, InstantiationException {
        // First set up IpAssignment to STATIC.
        Object ipAssignment = getEnumValue(
                "android.net.IpConfiguration$IpAssignment", "STATIC");
        callMethod(config, "setIpAssignment",
                new String[]{"android.net.IpConfiguration$IpAssignment"},
                new Object[]{ipAssignment});

        // Then set properties in StaticIpConfiguration.
        Object staticIpConfig = newInstance("android.net.StaticIpConfiguration");

        Object linkAddress = newInstance("android.net.LinkAddress",
                new Class[]{InetAddress.class, int.class}, new Object[]{
                        ipAddress, prefixLength});
        setField(staticIpConfig, "ipAddress", linkAddress);
        setField(staticIpConfig, "gateway", gateway);
        ArrayList<Object> aa = (ArrayList<Object>) getField(staticIpConfig,
                "dnsServers");
        aa.clear();
        for (int i = 0; i < dns.length; i++) {
            aa.add(dns[i]);
        }
        callMethod(config, "setStaticIpConfiguration",
                new String[]{"android.net.StaticIpConfiguration"},
                new Object[]{staticIpConfig});
        System.out.println("conconconm" + config);
        int updateNetwork = manager.updateNetwork(config);
        boolean saveConfiguration = manager.saveConfiguration();
        System.out.println("updateNetwork" + updateNetwork + saveConfiguration);

        System.out.println("ttttttttttt" + "成功");

        int netId = manager.addNetwork(config);
        manager.disableNetwork(netId);
        boolean flag = manager.enableNetwork(netId, true);
        Log.e("netId", netId + "");
        Log.e("flag", flag + "");

    }


    private static Object newInstance(String className)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException,
            IllegalArgumentException, InvocationTargetException {
        return newInstance(className, new Class[0], new Object[0]);
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object newInstance(String className,
                                      Class[] parameterClasses, Object[] parameterValues)
            throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, ClassNotFoundException {
        Class clz = Class.forName(className);
        Constructor constructor = clz.getConstructor(parameterClasses);
        return constructor.newInstance(parameterValues);
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object getEnumValue(String enumClassName, String enumValue)
            throws ClassNotFoundException {
        Class enumClz = (Class) Class.forName(enumClassName);
        return Enum.valueOf(enumClz, enumValue);
    }


    private static void setField(Object object, String fieldName, Object value)
            throws IllegalAccessException, IllegalArgumentException,
            NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.set(object, value);
    }


    private static Object getField(Object object, String fieldName)
            throws IllegalAccessException, IllegalArgumentException,
            NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(fieldName);
        Object out = field.get(object);
        return out;
    }


    @SuppressWarnings("rawtypes")
    private static void callMethod(Object object, String methodName,
                                   String[] parameterTypes, Object[] parameterValues)
            throws ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException {
        Class[] parameterClasses = new Class[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameterClasses[i] = Class.forName(parameterTypes[i]);
        }

        Method method = object.getClass().getDeclaredMethod(methodName,
                parameterClasses);
        method.invoke(object, parameterValues);
    }


    public String intToIp(int ipAddress) {
        return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));

    }

    // 直接使用set方法调用 可能遇到需要地址转换方法如下：
    public static String int2ip(int ip) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf((int) (ip & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 8) & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 16) & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 24) & 0xff)));
        return sb.toString();
    }
}
