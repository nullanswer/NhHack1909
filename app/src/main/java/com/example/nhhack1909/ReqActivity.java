package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.nhhack1909.Data.SelectLendData;

public class ReqActivity extends AppCompatActivity {

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            Intent t = new Intent(ReqActivity.this, Contract1Activity.class);
//            t.putExtra("gcm_value", getIntent().getSerializableExtra("gcm_value"));
            t.putExtra("data",data);
            ReqActivity.this.startActivity(t);
            finish();
        }
    };

    SelectLendData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);

        data = (SelectLendData) getIntent().getSerializableExtra("data");
        Log.e("data",data.getId());
        goToNextStage();
    }

    private void goToNextStage() {
        mHandler.sendEmptyMessageDelayed(0, 100);
    }

}
