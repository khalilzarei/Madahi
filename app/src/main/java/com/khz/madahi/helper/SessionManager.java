package com.khz.madahi.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SessionManager {
    private static final String PREF_NAME            = "TakeItFreePref";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_NIGHT_MODE        = "IS_NIGHT_MODE";
    private static final String KEY_FONT             = "KEY_FONT";
    private static final String KEY_FONT_SIZE        = "KEY_FONT_SIZE";

    // Shared Preferences
    private static SharedPreferences pref;
    private static Editor            editor;

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        // Shared pref mode
        int PRIVATE_MODE = 0;
        pref   = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setIsNightMode(boolean bool) {
        editor.putBoolean(IS_NIGHT_MODE, bool);
        editor.commit();
    }

    public boolean isNightMode() {
        return pref.getBoolean(IS_NIGHT_MODE, true);
    }


    public void setFont(String font) {
        editor.putString(KEY_FONT, font);
        editor.commit();
    }


    public static String getFont() {
        return pref.getString(KEY_FONT, "Vazir-Bold.ttf");
    }

    public static void setFontSize(int size) {
        editor.putInt(KEY_FONT_SIZE, size);
        editor.commit();
    }


    public static int getFontSize() {
        return pref.getInt(KEY_FONT_SIZE, 16);
    }
}
