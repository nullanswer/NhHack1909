package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhhack1909.Data.SelectLendData;

public class Contract1Activity extends AppCompatActivity {

    Button completeButton;

    ImageView rentImage;

    SelectLendData data;
    TextView contarcAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract1);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.mainStatusColor));

        data = (SelectLendData) getIntent().getSerializableExtra("data");

        rentImage = findViewById(R.id.rentImage);
        Glide.with(Contract1Activity.this).load(data.getImage()).into(rentImage);

        contarcAdd = findViewById(R.id.contarcAdd);
        contarcAdd.setText(data.getLand_address());

        completeButton = findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contract1Activity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }
}
