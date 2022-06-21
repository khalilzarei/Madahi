package com.khz.madahi.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.annotations.SerializedName;
import com.khz.madahi.models.User;


public class SessionManager {
    private static final String PREF_NAME            = "TakeItFreePref";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_NIGHT_MODE        = "IS_NIGHT_MODE";
    private static final String IS_LOGGED_IN         = "IS_LOGGED_IN";
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

    public void setIsLoggedIn(boolean login) {
        editor.putBoolean(IS_LOGGED_IN, login);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGGED_IN, false);
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
        return pref.getString(KEY_FONT, "yekan.ttf");
    }

    public static void setFontSize(int size) {
        editor.putInt(KEY_FONT_SIZE, size);
        editor.commit();
    }


    public static int getFontSize() {
        return pref.getInt(KEY_FONT_SIZE, 16);
    }


    public static final  String USER_ID        = "USER_ID";
    public static final  String USER_USER_NAME = "USER_USER_NAME";
    public static final  String USER_EMAIL     = "USER_EMAIL";
    private static final String USER_FULL_NAME = "USER_FULL_NAME";
    private static final String USER_MOBILE    = "USER_MOBILE";
    private static final String USER_CREATE_AT = "USER_CREATE_AT";
    private static final String USER_UPDATE_AT = "USER_UPDATE_AT";

    public  void setUser(User user) {
        editor.putString(USER_ID, user.getId());
        editor.putString(USER_USER_NAME, user.getUserName());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_FULL_NAME, user.getFullName());
        editor.putString(USER_MOBILE, user.getMobile());
        editor.putString(USER_CREATE_AT, user.getCreateAt());
        editor.putString(USER_UPDATE_AT, user.getUpdateAt());
        editor.commit();

    }

    public static User getUser() {
        User user = new User();
        user.setId(pref.getString(USER_ID, null));
        user.setUserName(pref.getString(USER_USER_NAME, null));
        user.setEmail(pref.getString(USER_EMAIL, null));
        user.setFullName(pref.getString(USER_FULL_NAME, null));
        user.setMobile(pref.getString(USER_MOBILE, null));
        user.setCreateAt(pref.getString(USER_CREATE_AT, null));
        user.setUpdateAt(pref.getString(USER_UPDATE_AT, null));
        return user;
    }
}
