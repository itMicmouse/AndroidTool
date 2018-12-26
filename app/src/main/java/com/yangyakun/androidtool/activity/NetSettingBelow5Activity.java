package com.yangyakun.androidtool.activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.yangyakun.androidtool.R;

public class NetSettingBelow5Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(android.os.Build.VERSION.SDK_INT);
        setIpWithTfiStaticIp();
    }

    /**
     * 设置ip地址类型 assign: STATIC/DHCP 静态/动态
     * @param assign
     * @param wifiConf
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private static void setIpAssignment(String assign, WifiConfiguration wifiConf) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        if (Build.VERSION.SDK_INT >= 21) {
            Object ipConfiguration = wifiConf.getClass()
                    .getMethod("getIpConfiguration").invoke(wifiConf);
            setEnumField(ipConfiguration, assign, "ipAssignment");
        }
    }

    //  设置ip地址
    private static void setIpAddress(InetAddress addr, int prefixLength, WifiConfiguration wificonf) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        Object linkProperties = getField(wificonf, "linkProperties");
        if (linkProperties == null) {
            return;
        }
        Class<?> laClass = Class.forName("android.net.LinkAddress");
        Constructor<?> laConstructor = laClass.getConstructor(new Class[]{
                InetAddress.class, int.class
        });
        Object linkAddress = laConstructor.newInstance(addr, prefixLength);
        ArrayList<Object> mLinkAddresses = (ArrayList<Object>) getDeclaredField(linkProperties, "mLinkAddresses");
        mLinkAddresses.clear();
        mLinkAddresses.add(linkAddress);

    }

    @SuppressWarnings("unchecked")
//  设置网关
    private static void setGateway(InetAddress gateway, WifiConfiguration wifiConf) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Object linkProperties = getField(wifiConf, "linkProperties");
        if (linkProperties == null) {
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            //android4.x版本
            Class<?> routeInfoClass = Class.forName("android.net.RouteInfo");
            Constructor<?> routeInfoConstructor = routeInfoClass.getConstructor(new Class[]{InetAddress.class});
            Object routeInfo = routeInfoConstructor.newInstance(gateway);
            ArrayList<Object> mRoutes = (ArrayList<Object>) getDeclaredField(linkProperties, "mRoutes");
            mRoutes.clear();
            mRoutes.add(routeInfo);
        }
        /*else
        {
            //android 3.x版本
            ArrayList<InetAddress> mGateways=(ArrayList<InetAddress>)getDeclaredField(linkProperties,"mGateways");
            mGateways.clear();
            mGateways.add(gateway);
        }*/
    }

    @SuppressWarnings("unchecked")
    //设置域名解析服务器
    private static void setDNS(InetAddress dns, WifiConfiguration wifiConf) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Object linkProperties = getField(wifiConf, "linkProperties");
        if (linkProperties == null) {
            return;
        }
        ArrayList<InetAddress> mDnses = (ArrayList<InetAddress>) getDeclaredField(linkProperties, "mDnses");
        //清除原有Dns设置（如果只想增加，不想清除，词句可省略）
        mDnses.clear();
        //增加新的DNS
        mDnses.add(dns);
    }

    private static Object getField(Object obj, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Field f = obj.getClass().getField(name);
        Object out = f.get(obj);
        return out;
    }

    private static Object getDeclaredField(Object obj, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field f = obj.getClass().getDeclaredField(name);
        f.setAccessible(true);
        Object out = f.get(obj);
        return out;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void setEnumField(Object obj, String value, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Field f = obj.getClass().getField(name);
        f.set(obj, Enum.valueOf((Class<Enum>) f.getType(), value));
    }
    //以上是android3.x以上设置静态ip地址的方法

    //下面是调用方法
    private void setIpWithTfiStaticIp() {
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
        /*if(android.os.Build.VERSION.SDK_INT<11)
        {
            //如果是android2.x版本的话
            ContentResolver ctRes=this.getContentResolver();
            Settings.System.putInt(ctRes, Settings.System.WIFI_USE_STATIC_IP,1);
            Settings.System.putString(ctRes, Settings.System.WIFI_STATIC_IP,"192.168.0.202");
            Settings.System.putString(ctRes, Settings.System.WIFI_STATIC_NETMASK,"255.255.255.0");
            Settings.System.putString(ctRes, Settings.System.WIFI_STATIC_GATEWAY,"192.168.0.1");
            Settings.System.putString(ctRes,Settings.System.WIFI_STATIC_DNS1,"192.168.0.1");
            Settings.System.putString(ctRes, Settings.System.WIFI_STATIC_DNS2,"61.134.1.9");
        }
        else
        {*/
        //如果是andoir3.x版本及以上的话
        try {
            setIpAssignment("STATIC", wifiConfig);
            setIpAddress(InetAddress.getByName("192.168.0.202"), 24, wifiConfig);
            setGateway(InetAddress.getByName("192.168.0.1"), wifiConfig);
            setDNS(InetAddress.getByName("192.168.0.1"), wifiConfig);
            //apply the setting
            wifiManager.updateNetwork(wifiConfig);
            System.out.println("静态ip设置成功");
            Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("静态ip设置失败");
            Toast.makeText(getApplicationContext(), "失败",  Toast.LENGTH_LONG).show();
        }

    }
    /*}*/
}