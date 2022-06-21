package com.khz.madahi.application;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.snackbar.Snackbar;
import com.khz.madahi.db.DatabaseHelper;
import com.khz.madahi.helper.SessionManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pushpole.sdk.PushPole;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    public BaseActivity activity;
    public View         view;

    public String         TAG;
    public SessionManager sessionManager;

    public DatabaseHelper databaseHelper;
    public String         userId = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        PushPole.initialize(this, true);
        if (sessionManager.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        activity       = this;
        databaseHelper = DatabaseHelper.getInstance(this);
        hideTiTleActionBar();
        if (sessionManager.isLoggedIn())
            userId = SessionManager.getUser()
                                   .getId();
    }

    public String getHtmlTag(String content) {
        StringBuilder stringBuilder = new StringBuilder("<p>");
        String[]      contents      = content.split("\n");
        for (String line : contents) {
            if (line.length() > 2) {
                stringBuilder.append(line);
                stringBuilder.append("</p>\n" + "<p>");
            }
        }
        stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length());
        return stringBuilder.toString();
    }


    private void hideTiTleActionBar() {
        //        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideTiTleActionBar();

    }

    public void toast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT)
             .show();
    }

    public void log(String message) {
        Log.e(TAG, message);
    }


    public int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    public int pxToDp(int px) {
        return (int) (px / getResources().getDisplayMetrics().density);
    }

    public boolean isEmailValid(String email) {
        String  expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern    = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher    = pattern.matcher(email);
        return matcher.matches();
    }

    public void showErrorSnackBar(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.RED)
                .setTextColor(Color.YELLOW)
                .show();
    }

    public boolean isNetworkConnected() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void showSuccessSnackBar(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.GREEN)
                .setTextColor(Color.BLACK)
                .show();
    }


    public void setBaseActivityValues(BaseActivity activity, View view, String TAG) {
        this.activity = activity;
        this.view     = view;
        this.TAG      = TAG;
    }

}
