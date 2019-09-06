package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.nhhack1909.Common.NetworkClass;
import com.example.nhhack1909.Common.SPController;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    EditText inputPw,inputId;

    String id,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputId = findViewById(R.id.inputId);
        inputPw = findViewById(R.id.inputPw);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
//                id = inputId.getText().toString();
//                pw = inputPw.getText().toString();

                id ="test@naver.com";
                pw = "1234";


                new setLogin().execute(id,pw);

                break;
        }
    }

    public String method(String str) {
        if (str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }


    public class setLogin extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String jws) {
            super.onPostExecute(jws);



            if (jws != null) {
                Log.e("asdf",jws);

                try {

//                    JWT parsedJWT = new JWT(jws);

//                    String[] aa = jws.split("\\.");

//                    Claim subscriptionMetaData = parsedJWT.getClaim("adminId");

                    String test = method(jws);
//            Log.e("test",jws.replaceAll("\"",""));


//                SPController.savePreferences(LoginActivity.this, getResources().getString(R.string.parseValue), aa[0].substring(1));
//                SPController.savePreferences(LoginActivity.this, getResources().getString(R.string.parseValue), jws.substring(1));
                    SPController.savePreferences(LoginActivity.this, getResources().getString(R.string.parseValue), jws.replaceAll("\"", ""));

                    Log.e("test",jws);
//                    if (autoLoginBox.isChecked()) {
//                        SPController.setLogin(LoginActivity.this, autoLoginBox.isChecked());
//                        SPController.savePreferences(LoginActivity.this, getResources().getString(R.string.adminId), adminId);
//                        SPController.savePreferences(LoginActivity.this, getResources().getString(R.string.adminPw), adminPwd);
//
//                    }

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    Gson gson = new Gson();
                    Log.e("tag",e.toString());

//                    FailedLoginData failedLoginData = gson.fromJson(jws, FailedLoginData.class);
//
//                    if (failedLoginData.getIsLogin().equals("false")) {
//                        Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.\n아이디 또는 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
            else {
                Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.\n아이디 또는 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            RequestBody formBody = new FormBody.Builder()
                    .add("email", strings[0])
                    .add("password", strings[1])
//                    .add("adminId", "test")
//                    .add("adminPwd", "test")
                    .build();

            String loginUrl = "users/login";
            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .url(NetworkClass.ServerAddress + loginUrl)
                    .post(formBody)
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
