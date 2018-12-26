package com.yangyakun.androidtool.bean;

public class TracerouteContainer {
    private String hostname;
    private String ip;
    private float elapsedTime;

    public TracerouteContainer(String hostname, String ip, float elapsedTime) {
        this.hostname = hostname;
        this.ip = ip;
        this.elapsedTime = elapsedTime;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Override
    public String toString() {
        return "TracerouteContainer{" +
                "hostname='" + hostname + '\'' +
                ", ip='" + ip + '\'' +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
