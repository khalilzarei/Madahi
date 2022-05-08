package com.khz.madahi.ui.category.viewmodels;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khz.madahi.BR;
import com.khz.madahi.models.Category;
import com.khz.madahi.ui.category.CategoryActivity;
import com.khz.madahi.ui.category.adapter.CategoryAdapter;

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
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    public void addCategory(View view) {
        if (categoryTitle == null || categoryTitle.isEmpty()) {
            activity.toast("لطفا عنوان را وارد کنید!");
            return;
        }
        if (categoryDescription == null || categoryDescription.isEmpty()) {
            activity.toast("لطفا عنوان را وارد کنید!");
            return;
        }

        activity.databaseHelper.categoryDAO().insert(new Category("", categoryTitle, categoryDescription));
        categoryAdapter.setData(activity.databaseHelper.categoryDAO().getAll());
        setIsVisible(!getIsVisible());
    }

    public void changeVisibility(View view) {
        setIsVisible(!getIsVisible());
    }
}
