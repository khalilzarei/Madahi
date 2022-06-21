package com.khz.madahi.ui.splash;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;

import com.khz.madahi.BuildConfig;
import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.models.AppInfo;
import com.khz.madahi.models.response.AppInfoResponse;
import com.khz.madahi.network.APIService;
import com.khz.madahi.network.RetroClass;
import com.khz.madahi.ui.category.CategoryActivity;
import com.khz.madahi.ui.intro.IntroActivity;
import com.khz.madahi.ui.login.LoginActivity;
import com.pushpole.sdk.PushPole;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        PushPole.initialize(this, true);
        setBaseActivityValues(this, findViewById(R.id.textView), this.getClass()
                                                                     .getSimpleName());
        if (activity.isNetworkConnected())
            getAppInfo();
        else
            startApp();
    }


    private void getAppInfo() {
        APIService apiService = RetroClass.getAPIService();

        Call<AppInfoResponse> responseCall = apiService.getAppInfo();

        responseCall.enqueue(new Callback<AppInfoResponse>() {
            @Override
            public void onResponse(Call<AppInfoResponse> call, Response<AppInfoResponse> response) {
                AppInfoResponse resultResponse = response.body();


                if (!resultResponse.getError()) {
                    AppInfo appInfo     = resultResponse.getAppInfo();
                    int     versionCode = BuildConfig.VERSION_CODE;
                    String  versionName = BuildConfig.VERSION_NAME;
                    if (Integer.parseInt(appInfo.getVersionCode()) > versionCode) {
                        new AestheticDialog.Builder(activity, DialogStyle.FLAT, DialogType.SUCCESS).setTitle("آپدیت")
                                                                                                   .setMessage(
                                                                                                           "نسخه جدید اپلیکیشن آماده است لطفا اپ را آپدیت کنید")
                                                                                                   .setCancelable(true)
                                                                                                   .setDarkMode(true)
                                                                                                   .setGravity(Gravity.CENTER)
                                                                                                   .setAnimation(
                                                                                                           DialogAnimation.SHRINK)
                                                                                                   .setOnClickListener(
                                                                                                           builder -> {
                                                                                                               builder.dismiss();
                                                                                                               Intent browserIntent = new Intent(
                                                                                                                       Intent.ACTION_VIEW,
                                                                                                                       Uri.parse(
                                                                                                                               appInfo.getAppUrl()));
                                                                                                               startActivity(
                                                                                                                       browserIntent);
                                                                                                               finish();
                                                                                                           })
                                                                                                   .show();

                    } else {
                        startApp();
                    }
                } else {
                    activity.showErrorSnackBar(resultResponse.getErrorMsg());
                }
            }


            @Override
            public void onFailure(Call<AppInfoResponse> call, Throwable t) {
                activity.log("onFailure " + t.getMessage());
            }
        });

    }

    private void startApp() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, CategoryActivity.class);

            if (sessionManager.isFirstTimeLaunch()) {
                intent = new Intent(SplashActivity.this, IntroActivity.class);
            } else if (!sessionManager.isLoggedIn()) {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            finish();

        }, 300);
    }


}