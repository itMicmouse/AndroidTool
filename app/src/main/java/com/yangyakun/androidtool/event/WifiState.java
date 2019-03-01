package com.yangyakun.androidtool.event;

import android.net.NetworkInfo;

public class WifiState {
    public WifiState(NetworkInfo.State currentState) {
        this.currentState = currentState;
    }

    NetworkInfo.State currentState = NetworkInfo.State.DISCONNECTED;

    public NetworkInfo.State getCurrentState() {
        return currentState;
    }
}
