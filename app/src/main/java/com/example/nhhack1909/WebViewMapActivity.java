package com.example.nhhack1909;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;
import java.util.List;

public class WebViewMapActivity extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_map);

//        String url = "nmap://actionPath?parameter=value&appname={NhHack1909}";
//
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        intent.addCategory(Intent.CATEGORY_BROWSABLE);
//
//        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//        if (list == null || list.isEmpty()) {
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.nmap")));
//        } else {
//            startActivity(intent);
//        }


    }

//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if (url.startsWith("intent:")) {
//            Intent intent;
//            try {
//                intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
//            } catch (URISyntaxException e) {
//                return false;
//            }
//            if (TextUtils.isEmpty(intent.getPackage())) {
//                return false;
//            }
//            List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//            if (list == null || list.isEmpty()) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + intent.getPackage())));
//            } else {
//                startActivity(intent);
//            }
//            return true;
//        }
//
//        return false;
//    }
}
