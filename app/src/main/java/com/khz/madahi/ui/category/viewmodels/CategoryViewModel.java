package com.khz.madahi.ui.category.viewmodels;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khz.madahi.BR;
import com.khz.madahi.helper.SessionManager;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.Favorite;
import com.khz.madahi.models.response.AddCategoryResponse;
import com.khz.madahi.models.response.LoginResponse;
import com.khz.madahi.network.APIService;
import com.khz.madahi.network.RetroClass;
import com.khz.madahi.ui.category.CategoryActivity;
import com.khz.madahi.ui.category.adapter.CategoryAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class CategoryViewModel extends BaseObservable {
    CategoryAdapter  categoryAdapter;
    CategoryActivity activity;
    String           categoryTitle       = "";
    String           categoryDescription = "";
    boolean          isVisible           = false;

    public CategoryViewModel(CategoryActivity activity, CategoryAdapter categoryAdapter) {
        this.activity        = activity;
        this.categoryAdapter = categoryAdapter;
    }

    @Bindable
    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean visible) {
        isVisible = visible;
        notifyPropertyChanged(BR.isVisible);
    }

    @Bindable
    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
        notifyPropertyChanged(BR.categoryTitle);
    }

    @Bindable
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        notifyPropertyChanged(BR.categoryDescription);
    }

    @Bindable
    public CategoryAdapter getCategoryAdapter() {
        return categoryAdapter;
    }

    public void setCategoryAdapter(CategoryAdapter categoryAdapter) {
        this.categoryAdapter = categoryAdapter;
        notifyPropertyChanged(BR.categoryAdapter);
    }

    @BindingAdapter("setCategoryItems")
    public static void setCategoryItems(RecyclerView recyclerView, CategoryAdapter adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    public void addCategory(View view) {
        if (categoryTitle == null || categoryTitle.isEmpty()) {
            activity.toast("لطفا عنوان را وارد کنید!");
            return;
        }
        if (categoryDescription == null || categoryDescription.isEmpty()) {
            activity.toast("لطفا توضیحات را وارد کنید!");
            return;
        }
        if (!activity.isNetworkConnected()) {

            activity.databaseHelper
                    .categoryDAO()
                    .insert(new Category("", categoryTitle, categoryDescription));

            categoryAdapter.setData(activity.databaseHelper
                    .categoryDAO()
                    .getAll());

            setIsVisible(!getIsVisible());
        } else
            addCategoryToServer(view);
    }


    //region Method
    private void addCategoryToServer(View view) {
        activity.log("AddCategoryResponse : " + SessionManager
                .getUser()
                .getId() + " " + categoryTitle + " " + categoryDescription);
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        APIService apiService = RetroClass.getAPIService();

        Call<AddCategoryResponse> responseCall = apiService.insertCategory(activity.userId, categoryTitle, categoryDescription);
        //        Call<AddCategoryResponse> responseCall = apiService.insertCategory("1", "CategoryTitle", "CategoryDescription");


        responseCall.enqueue(new Callback<AddCategoryResponse>() {
            @Override
            public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                AddCategoryResponse resultResponse = response.body();
                if (!resultResponse.getError()) {
                    Category category = resultResponse.getCategory();
                    categoryAdapter.addCategory(category);
                    activity.databaseHelper
                            .categoryDAO()
                            .insert(category);
                    activity.showSuccessSnackBar(resultResponse.getErrorMsg() + " - " + category.getTitle());
                } else {
                    activity.showErrorSnackBar(resultResponse.getErrorMsg());
                }
                setIsVisible(!getIsVisible());
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }


            @Override
            public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                activity.log("onFailure " + t.getMessage());
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }
        });

    }

    private void login(View view) {
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        APIService          apiService   = RetroClass.getAPIService();
        Call<LoginResponse> responseCall = apiService.login(categoryTitle);
        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                activity.log("login : " + response.body() + "");
                if (response.isSuccessful() && loginResponse != null) {
                    if (!loginResponse.getError()) {
                        activity.showSuccessSnackBar(loginResponse.getErrorMsg());
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

    public void changeVisibility(View view) {
        setIsVisible(!getIsVisible());
    }
}
