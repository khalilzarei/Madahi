package com.khz.madahi.ui.category.viewmodels;

import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.khz.madahi.BR;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.models.Category;
import com.khz.madahi.ui.content.ContentActivity;


public class CategoryItemViewModel extends BaseObservable {

    BaseActivity activity;
    Category     category;


    public CategoryItemViewModel(BaseActivity activity, Category category) {
        this.activity = activity;
        this.category = category;
    }

    @Bindable
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        notifyPropertyChanged(BR.category);
    }

    public void onCategoryClick(View view){
        Intent intent=new Intent(activity, ContentActivity.class);
        intent.putExtra("category",category);
        activity.startActivity(intent);
    }

}
