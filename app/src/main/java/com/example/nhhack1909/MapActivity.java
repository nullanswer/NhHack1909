package com.example.nhhack1909;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.PolygonOverlay;
import com.naver.maps.map.overlay.PolylineOverlay;

import java.util.Arrays;

public class MapActivity extends AppCompatActivity  {


    private WebView wv;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        wv = (WebView)findViewById(R.id.webView);

        WebSettings webSettings = wv.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        wv.addJavascriptInterface(new AndroidBridge(), "android");


        wv.loadUrl("http://192.168.0.63:8080");

    }



    public class AndroidBridge {
        @JavascriptInterface
        public void setMessage(final String arg){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("abc", arg);
                    if (arg.equals("close")) {
                        Intent intent = new Intent(MapActivity.this, CompleteContractActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

}