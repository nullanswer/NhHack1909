package com.example.nhhack1909.Common;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SPController {

    private static String TAG = "pref";
    private static String isLogin = "isLogin";

    private static boolean defaultLoginState = false;

    public static  boolean isLogin(Context ctx) {
        SharedPreferences pref = ctx.getSharedPreferences(TAG, MODE_PRIVATE);
        return pref.getBoolean(isLogin, defaultLoginState);
    }

    public static boolean setLogin(Context ctx, boolean loginState) {
        SharedPreferences pref = ctx.getSharedPreferences(TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(isLogin, loginState);
        return editor.commit();
    }

    // 값 불러오기
    public static String getPreferences(Context ctx, String key) {
        SharedPreferences pref = ctx.getSharedPreferences(TAG, MODE_PRIVATE);
        return pref.getString(key, "");
    }

    // 값 저장하기
    public static void savePreferences(Context ctx, String key, String inputValue) {
        SharedPreferences pref = ctx.getSharedPreferences(TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, inputValue);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    private void removePreferences(Context ctx, String removeKey) {
        SharedPreferences pref = ctx.getSharedPreferences(TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(removeKey);
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    private void removeAllPreferences(Context ctx) {
        SharedPreferences pref = ctx.getSharedPreferences(TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}