package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.nhhack1909.Common.NetworkClass;
import com.example.nhhack1909.Common.SPController;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView myPageTabButton,commuTabButton,mainTabButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

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

        new getBestHouse().execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mainTabButton:
                new getBestHouse().execute();
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
        }
    }

    public class getBestHouse extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {

                Log.e("sss",s);
//                searchDateTextView.setText(stringFormat.format(selectedDate));
//                Gson gson = new Gson();
//                AttendanceByDateData attendanceByDateData = gson.fromJson(s, AttendanceByDateData.class);
//                attendanceCountTextView.setText("입장 : " + attendanceByDateData.getEnterPeople() + " 명");
//                completeCountTextView.setText("이수 : "+ attendanceByDateData.getCompletePeople() + " 명");
//                exitCountTextView.setText("퇴장 : " + attendanceByDateData.getExitPeople() + " 명");
            }
            else {
                Log.e("isNull","www");
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
//                    .add("queryDate", strings[0])
                    .build();


            String temUrl = "bestHouse";
            Request request = new Request.Builder()
                    .header("x-access-token", SPController.getPreferences(SearchResultActivity.this, getResources().getString(R.string.parseValue)))
                    .url(NetworkClass.ServerAddress + temUrl)
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }
        }
    }
}
