package com.khz.madahi.ui.splash;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.Const;
import com.khz.madahi.helper.SessionManager;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.response.AddCategoryResponse;
import com.khz.madahi.network.APIService;
import com.khz.madahi.network.RetroClass;
import com.khz.madahi.ui.category.CategoryActivity;
import com.khz.madahi.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setBaseActivityValues(this, findViewById(R.id.textView), this.getClass()
                                                                     .getSimpleName());
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, CategoryActivity.class);
            if (!SessionManager.isLoggedIn()) {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            finish();

        }, 700);
    }

    public void addCategory(View view) {
        addCategoryToServer(view);
    }

    private void addCategoryToServer(View view) {
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        APIService apiService = RetroClass.getAPIService();

        Call<AddCategoryResponse> responseCall = apiService.insertCategory("1", "CategoryTitle", "CategoryDescription");

        responseCall.enqueue(new Callback<AddCategoryResponse>() {
            @Override
            public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                AddCategoryResponse resultResponse = response.body();


                if (!resultResponse.getError()) {
                    Category category = resultResponse.getCategory();
                    activity.log(category.getId() + " - " + category.getUserId() + " - " + category.getTitle() + " - " +
                            category.getDescription());
                    activity.showSuccessSnackBar(response.body()
                                                         .toString() + " - " + category.getTitle());
                } else {
                    activity.showErrorSnackBar(resultResponse.getErrorMsg());
                }
                //
                new Handler().postDelayed(progressDialog::dismiss, 1000);
                //                setIsVisible(!getIsVisible());
                //                }
            }


            @Override
            public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                activity.log("onFailure " + t.getMessage());
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }
        });

    }


}