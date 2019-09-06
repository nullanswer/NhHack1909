package com.example.nhhack1909;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.nhhack1909.Common.NetworkClass;
import com.example.nhhack1909.Data.MarkerData;
import com.google.gson.Gson;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.MarkerIcons;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapFindActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static NaverMap thisNaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_find);

        MapFragment mapFragment = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance(new NaverMapOptions().camera(new CameraPosition(
                    NaverMap.DEFAULT_CAMERA_POSITION.target, NaverMap.DEFAULT_CAMERA_POSITION.zoom, 30, 45)));
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        thisNaver = naverMap;
        Log.e("test", String.valueOf(thisNaver.getContentHeight()));

        new getData().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class getData extends AsyncTask<String, NaverMap, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String jws) {
            super.onPostExecute(jws);
            if (jws != null) {
                try {
                    Gson gson = new Gson();
                    MarkerData[] tempData = gson.fromJson(jws,MarkerData[].class);
                    ArrayList<MarkerData> data = new ArrayList<>(Arrays.asList(tempData));
                    Log.e("data", String.valueOf(data.size()));
                    for (int i = 0; i < tempData.length; i++) {
                        MarkerData tempDatum = tempData[i];
                        Log.e("data", String.valueOf(tempDatum.getLand_lat()));
                        Log.e("data", String.valueOf(tempDatum.getLand_lng()));

                        if(tempDatum.getLand_lat() != null) {
                            Log.e("마커", String.valueOf(tempDatum.getLand_jimk()));
                            if(tempDatum.getLand_jimk().equals("빈집")) {
                                Marker marker = new Marker();
                                marker.setPosition(new LatLng(tempDatum.getLand_lng(), tempDatum.getLand_lat()));
                                marker.setIcon(OverlayImage.fromResource(R.drawable.dot_option_2));
                                marker.setTag(tempDatum.getId());
                                marker.setOnClickListener(new Overlay.OnClickListener() {
                                    @Override
                                    public boolean onClick(@NonNull Overlay overlay) {
                                        Log.e("click Event", (String) overlay.getTag());
                                        return true;
                                    }
                                });
                                marker.setMap(thisNaver);
                            }
                            else {
                                Marker marker2 = new Marker();
                                marker2.setPosition(new LatLng(tempDatum.getLand_lng(), tempDatum.getLand_lat()));
                                marker2.setIcon(OverlayImage.fromResource(R.drawable.dot_option_1));
                                marker2.setOnClickListener(new Overlay.OnClickListener() {
                                    @Override
                                    public boolean onClick(@NonNull Overlay overlay) {
                                        Log.e("click Event", (String) overlay.getTag());
                                        return true;
                                    }
                                });

                                marker2.setMap(thisNaver);
                            }
                        }
                    }
                    Log.e("test", String.valueOf(thisNaver.getContentHeight()));

                } catch (Exception e) {
                    Gson gson = new Gson();
                    Log.e("tag",e.toString());
                }
            }
            else {
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.e("asdf","is Started");

            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .url(NetworkClass.ServerAddress + "getLandAll")
                    .get()
                    .build();

            OkHttpClient client = new OkHttpClient();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (SocketTimeoutException socket) {
//                makeLoginFailToast();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}
