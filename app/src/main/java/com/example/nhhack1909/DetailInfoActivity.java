package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhhack1909.Common.NetworkClass;
import com.example.nhhack1909.Common.SPController;
import com.example.nhhack1909.Data.BestHouseData;
import com.example.nhhack1909.Data.SelectLendData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailInfoActivity extends AppCompatActivity implements View.OnClickListener{

    TextView detailSubs,detailLoc,rentTypeTextView,cropType,landPrice,detailDesc,
            landInput04,landInput03,landInput02,landInput01,priceInput01,priceInput02,priceInput03,priceInput04,rentalName
            ,rentalInfo;

    Button reqButton;

    String id;

    ImageView detailTopImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);

        id = getIntent().getStringExtra("id");

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.mainStatusColor));

        detailTopImage = findViewById(R.id.detailTopImage);


//      "id":422,"land_type":"농지임대","land_pay":810000,"land_size":"3273","land_jimk":"답","land_admin":"강진지사","land_phone":"061-430-7731","land_description":"방울방울 터지는 250평","land_lat":126.702,"land_lng":34.7356,"land_address":"전라남도 강진군 성전면 월하리 1421-7","img":"https://nhhacker.s3.ap-northeast-2.amazonaws.com/img_list_1%403x.png","isbest":1,"land_pay_type":"임대"}


        detailSubs = findViewById(R.id.detailSubs);
        detailLoc = findViewById(R.id.detailLoc);
        rentTypeTextView = findViewById(R.id.rentTypeTextView);
        cropType = findViewById(R.id.cropType);
        landPrice = findViewById(R.id.landPrice);

        detailDesc = findViewById(R.id.detailDesc);

        landInput04 = findViewById(R.id.landInput04);
        landInput03 = findViewById(R.id.landInput03);
        landInput02 = findViewById(R.id.landInput02);
        landInput01 = findViewById(R.id.landInput01);

        priceInput01 = findViewById(R.id.priceInput01);
        priceInput02 = findViewById(R.id.priceInput02);
        priceInput03 = findViewById(R.id.priceInput03);
        priceInput04 = findViewById(R.id.priceInput04);

        rentalName = findViewById(R.id.rentalName);
        rentalInfo = findViewById(R.id.rentalInfo);

        reqButton = findViewById(R.id.reqButton);
        reqButton.setOnClickListener(this);

        new getSelectedLand().execute();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reqButton:

                Intent intent = new Intent(DetailInfoActivity.this, ReqActivity.class);
                startActivity(intent);
                break;
        }
    }

    public class getSelectedLand extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null) {
                Log.e("wewe",s);
//                searchDateTextView.setText(stringFormat.format(selectedDate));
                Gson gson = new Gson();
                SelectLendData selectLendData = gson.fromJson(s, SelectLendData.class);



                Glide.with(DetailInfoActivity.this).load(selectLendData.getSecond_image()).into(detailTopImage);

                detailSubs.setText(selectLendData.getLand_description());
                detailLoc.setText(selectLendData.getLand_address());

                rentTypeTextView.setText(selectLendData.getLand_type());
                landPrice.setText(selectLendData.getLand_pay());

                detailDesc.setText(selectLendData.getLand_long_description());

                landInput01.setText("- 지목 : " + selectLendData.getLand_jimk());
                landInput02.setText("- 면적 : " + selectLendData.getLand_size());
                landInput03.setText("- 소유구분 : " + selectLendData.getLand_admin());
                landInput04.setVisibility(View.GONE);

                priceInput01.setText("- 개별공시지가 : " + selectLendData.getLand_official_price());




            }
            else {
                Log.e("isNull","www");
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("id", id)
                    .build();

            Log.e("id",id);

            String temUrl = "selectLand";
            Request request = new Request.Builder()
                    .header("x-access-token", SPController.getPreferences(DetailInfoActivity.this, getResources().getString(R.string.parseValue)))
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
