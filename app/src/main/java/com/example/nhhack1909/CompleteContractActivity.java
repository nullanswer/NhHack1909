package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class CompleteContractActivity extends AppCompatActivity {

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            Intent t = new Intent(CompleteContractActivity.this, MainActivity.class);
//            t.putExtra("gcm_value", getIntent().getSerializableExtra("gcm_value"));
            CompleteContractActivity.this.startActivity(t);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_contract);

        goToNextStage();
    }

    private void goToNextStage() {
        mHandler.sendEmptyMessageDelayed(0, 5000);
    }
}
