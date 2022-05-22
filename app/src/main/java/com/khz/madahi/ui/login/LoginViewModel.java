package com.khz.madahi.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.khz.madahi.BR;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.SessionManager;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.Favorite;
import com.khz.madahi.models.User;
import com.khz.madahi.models.response.LoginResponse;
import com.khz.madahi.network.APIService;
import com.khz.madahi.network.RetroClass;
import com.khz.madahi.ui.category.CategoryActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends BaseObservable {

    //region variable
    String       email   = "";
    BaseActivity activity;
    boolean      isLogin = true;
    //endregion

    //region Constructor

    public LoginViewModel(BaseActivity activity) {
        this.activity = activity;
    }

    //endregion


    //region Getter Setter
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean login) {
        isLogin = login;
        notifyPropertyChanged(BR.isLogin);
    }

    //endregion

    //region OnClick
    public void checkLogin(View view) {
        if (email == null || email.isEmpty()) {
            activity.showErrorSnackBar("لطفا موبایل یا ایمیل خود را وارد کنید");
            return;
        }

        if (activity.isNetworkConnected()) {
            if (isLogin)
                login(view);
            else
                register(view);
        } else {
            activity.showErrorSnackBar("لطفا اتصت=ال به اینترنت را بررسی کنید");
        }

    }

    public void changeLoginStatus(View view) {
        setIsLogin(!getIsLogin());
    }
    //endregion

    //region Method
    private void login(View view) {
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        APIService          apiService   = RetroClass.getAPIService();
        Call<LoginResponse> responseCall = apiService.login(getEmail());
        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                activity.log("login : " + response.body() + "");
                if (response.isSuccessful() && loginResponse != null) {
                    activity.showSuccessSnackBar(loginResponse.getErrorMsg());
                    if (!loginResponse.getError()) {
                        SessionManager.setIsLoggedIn(true);
                        for (Category category : loginResponse.getCategories()) {
                            activity.databaseHelper
                                    .categoryDAO()
                                    .insert(category);
                        }
                        for (Favorite favorite : loginResponse.getFavorites()) {
                            activity.databaseHelper
                                    .favoriteDAO()
                                    .insert(favorite);
                        }
                        for (Content content : loginResponse.getContents()) {
                            activity.databaseHelper
                                    .contentDAO()
                                    .insert(content);
                        }

                        SessionManager.setUser(loginResponse.getUser());

                        new Handler().postDelayed(() -> {
                            progressDialog.dismiss();
                            activity.startActivity(new Intent(activity, CategoryActivity.class));
                            activity.finish();
                        }, 1000);
                    } else {
                        activity.showErrorSnackBar(loginResponse.getErrorMsg());
                        new Handler().postDelayed(progressDialog::dismiss, 1000);
                    }

                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("onFailure", t.getMessage() + " ");
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }
        });

    }

    private void register(View view) {
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        APIService          apiService   = RetroClass.getAPIService();
        Call<LoginResponse> responseCall = apiService.register(getEmail());
        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                activity.log("login : " + response.body() + "");
                if (response.isSuccessful() && loginResponse != null) {
                    activity.showSuccessSnackBar(loginResponse.getErrorMsg());
                    if (!loginResponse.getError()) {
                        SessionManager.setIsLoggedIn(true);
                        for (Category category : loginResponse.getCategories()) {
                            activity.databaseHelper
                                    .categoryDAO()
                                    .insert(category);
                        }
                        for (Favorite favorite : loginResponse.getFavorites()) {
                            activity.databaseHelper
                                    .favoriteDAO()
                                    .insert(favorite);
                        }
                        for (Content content : loginResponse.getContents()) {
                            activity.databaseHelper
                                    .contentDAO()
                                    .insert(content);
                        }

                        SessionManager.setUser(loginResponse.getUser());
                        new Handler().postDelayed(() -> {
                            progressDialog.dismiss();
                            activity.startActivity(new Intent(activity, CategoryActivity.class));
                            activity.finish();
                        }, 1000);
                    } else {
                        activity.showErrorSnackBar(loginResponse.getErrorMsg());
                        new Handler().postDelayed(progressDialog::dismiss, 1000);
                    }

                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("onFailure", t.getMessage() + " ");
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }
        });

    }

    //endregion


}
