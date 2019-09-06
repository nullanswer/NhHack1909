package com.example.nhhack1909;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
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

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

//    MapView naverMapView;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://192.168.0.63:8080");
        webView.setWebViewClient(new WebViewClientClass());

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        naverMapView = findViewById(R.id.naverMapView);
//        naverMapView.onCreate(savedInstanceState);
//
//        naverMapView.getMapAsync(this);
//
//
//        LatLng coord = new LatLng(37.5670135, 126.9783740);


    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL", url);
            view.loadUrl(url);
            return true;
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        naverMapView.onStart();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        naverMapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        naverMapView.onPause();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        naverMapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        naverMapView.onStop();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        naverMapView.onLowMemory();
//    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {


        naverMap.setMapType(NaverMap.MapType.Satellite);

        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(false);

//        locationOverlay.setPosition(new LatLng(37.9670135, 126.3783740));

        PolygonOverlay polygon = new PolygonOverlay();
        polygon.setCoords(Arrays.asList(
                new LatLng(37.5640984, 126.9712268),
                new LatLng(37.5651279, 126.9767904),
                new LatLng(37.5625365, 126.9832241),
                new LatLng(37.5585305, 126.9809297),
                new LatLng(37.5590777, 126.974617)
        ));
//        polygon.setColor(0xf0F1FFFF);
        polygon.setOutlineColor(0xFF00FF00);
        polygon.setMap(naverMap);

    }
}
