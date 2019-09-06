package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.nhhack1909.Common.SPController;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{


    ImageView myPageTabButton,commuTabButton,mainTabButton,searchResultIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.mainStatusColor));

        mainTabButton = findViewById(R.id.mainTabButton);
        mainTabButton.setOnClickListener(this);

        commuTabButton = findViewById(R.id.commuTabButton);
        commuTabButton.setOnClickListener(this);

        myPageTabButton = findViewById(R.id.myPageTabButton);
        myPageTabButton.setOnClickListener(this);

        searchResultIV = findViewById(R.id.searchResultIV);
        searchResultIV.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainTabButton:

                if (mainTabButton.isSelected()) {
                    mainTabButton.setSelected(false);
                } else {
                    mainTabButton.setSelected(true);
                    commuTabButton.setSelected(false);
                    myPageTabButton.setSelected(false);
                }

                break;

            case R.id.commuTabButton:

                if (commuTabButton.isSelected()) {
                    commuTabButton.setSelected(false);
                } else {
                    commuTabButton.setSelected(true);
                    mainTabButton.setSelected(false);
                    myPageTabButton.setSelected(false);
                }

                break;

            case R.id.myPageTabButton:
                if (myPageTabButton.isSelected()) {
                    myPageTabButton.setSelected(false);
                } else {
                    myPageTabButton.setSelected(true);
                    commuTabButton.setSelected(false);
                    mainTabButton.setSelected(false);
                }

                break;

            case R.id.searchResultIV:
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                startActivity(intent);
                break;
        }
    }


}
