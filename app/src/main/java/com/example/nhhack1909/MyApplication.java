package com.example.nhhack1909;

import android.app.Application;

import com.naver.maps.map.NaverMapSdk;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient(getString(R.string.parseValue)));
    }
}