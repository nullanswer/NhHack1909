package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    WebView webView;

    ImageView myPageTabButton,commuTabButton,mainTabButton;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        webView = findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient());
//        webView.loadUrl("https://map.naver.com/?query=%EC%A0%84%EB%9D%BC%EB%82%A8%EB%8F%84+%EA%B0%95%EC%A7%84%EA%B5%B0+%EA%B0%95%EC%A7%84%EC%9D%8D+%EC%84%9C%EC%82%B0%EB%A6%AC+62-1");
//        webView.setWebViewClient(new WebViewClientClass());

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.mainStatusColor));

        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

        mainTabButton = findViewById(R.id.mainTabButton);
        mainTabButton.setOnClickListener(this);

        commuTabButton = findViewById(R.id.commuTabButton);
        commuTabButton.setOnClickListener(this);

        myPageTabButton = findViewById(R.id.myPageTabButton);
        myPageTabButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mainTabButton:

                if (mainTabButton.isSelected()){
                    mainTabButton.setSelected(false);
                }
                else {
                    mainTabButton.setSelected(true);
                    commuTabButton.setSelected(false);
                    myPageTabButton.setSelected(false);
                }

                break;

            case R.id.commuTabButton:

                if (commuTabButton.isSelected()){
                    commuTabButton.setSelected(false);
                }
                else {
                    commuTabButton.setSelected(true);
                    mainTabButton.setSelected(false);
                    myPageTabButton.setSelected(false);
                }

                break;

            case R.id.myPageTabButton:
                if (myPageTabButton.isSelected()){
                    myPageTabButton.setSelected(false);
                }
                else {
                    myPageTabButton.setSelected(true);
                    commuTabButton.setSelected(false);
                    mainTabButton.setSelected(false);
                }

                break;

            case R.id.searchButton:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }


}
