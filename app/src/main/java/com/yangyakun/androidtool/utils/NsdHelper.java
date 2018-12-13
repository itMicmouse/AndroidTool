/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yangyakun.androidtool.utils;

import android.content.Context;
import android.net.nsd.NsdServiceInfo;
import android.net.nsd.NsdManager;
import android.util.Log;

/**
 * @author 92155
 */
public class NsdHelper {

    private Context mContext;

    private NsdManager mNsdManager;

    /**
     * 网络服务连接时间监听器
     */
    private NsdManager.ResolveListener mResolveListener;

    /**
     * 用于服务发现的回调调用接口
     */
    private NsdManager.DiscoveryListener mDiscoveryListener;

    /**
     * 注册事件监听器
     */
    private NsdManager.RegistrationListener mRegistrationListener;

    private static final String SERVICE_TYPE = "_http._tcp.";

    private static final String TAG = "NsdHelper";
    private String mServiceName = "NsdCha11";

    /**
     * 网络服务的信息
     */
    NsdServiceInfo mService;

    public NsdHelper(Context context) {
        mContext = context;
        mNsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
    }

    public void initializeNsd() {
        initializeResolveListener();
    }

    private void initializeDiscoveryListener() {
        mDiscoveryListener = new NsdManager.DiscoveryListener() {
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "开始扫描服务");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                Log.d(TAG, "发现服务-服务内容:" + service);
                if (!service.getServiceType().equals(SERVICE_TYPE)) {
                    Log.d(TAG, "服务类型: " + service.getServiceType());
                } else if (service.getServiceName().equals(mServiceName)) {
                    Log.d(TAG, "相同的服务: " + mServiceName);
                } else if (mServiceName.contains(service.getServiceName())) {
                    Log.d(TAG, "需要的服务: " + mServiceName);
                    mNsdManager.resolveService(service, mResolveListener);
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                Log.e(TAG, "丢失服务" + service);
                if (mService == service) {
                    mService = null;
                }
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "停止扫描服务: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "开始扫描服务失败-原因:" + errorCode);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "停止扫描服务失败-原因" + errorCode);
            }
        };
    }

    private void initializeResolveListener() {
        mResolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Log.e(TAG, "链接失败-原因:" + errorCode);
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                Log.e(TAG, "链接成功-链接信息: " + serviceInfo);

                //根据名称是否相等来判断
                if (serviceInfo.getServiceName().equals(mServiceName)) {
                    Log.d(TAG, "同一台机器.");
                    return;
                }
                mService = serviceInfo;
            }
        };
    }

    private void initializeRegistrationListener() {
        mRegistrationListener = new NsdManager.RegistrationListener() {

            @Override
            public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
                mServiceName = NsdServiceInfo.getServiceName();
                Log.d(TAG, "注册服务成功-服务名称: " + mServiceName);
            }

            @Override
            public void onRegistrationFailed(NsdServiceInfo arg0, int arg1) {
                Log.d(TAG, "注册服务失败-失败原因: " + arg1);
            }

            @Override
            public void onServiceUnregistered(NsdServiceInfo arg0) {
                Log.d(TAG, "注销服务-服务名称: " + arg0.getServiceName());
            }

            @Override
            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Log.d(TAG, "注销服务失败-失败原因: " + errorCode);
            }

        };
    }

    public void registerService(int port) {
        tearDown();  // Cancel any previous registration request
        initializeRegistrationListener();
        NsdServiceInfo serviceInfo = new NsdServiceInfo();
        serviceInfo.setPort(port);
        serviceInfo.setServiceName(mServiceName);
        serviceInfo.setServiceType(SERVICE_TYPE);

        mNsdManager.registerService(
                serviceInfo, NsdManager.PROTOCOL_DNS_SD, mRegistrationListener);

    }

    public void discoverServices() {
        stopDiscovery();  // Cancel any existing discovery request
        initializeDiscoveryListener();
        mNsdManager.discoverServices(
                SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
    }

    public void stopDiscovery() {
        if (mDiscoveryListener != null) {
            try {
                mNsdManager.stopServiceDiscovery(mDiscoveryListener);
            } finally {
            }
            mDiscoveryListener = null;
        }
    }

    public NsdServiceInfo getChosenServiceInfo() {
        return mService;
    }

    public void tearDown() {
        if (mRegistrationListener != null) {
            try {
                mNsdManager.unregisterService(mRegistrationListener);
            } finally {
            }
            mRegistrationListener = null;
        }
    }
}
