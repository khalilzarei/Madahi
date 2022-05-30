package com.khz.madahi.ui.category;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.databinding.ActivityCategoryBinding;
import com.khz.madahi.models.Category;
import com.khz.madahi.ui.category.adapter.CategoryAdapter;
import com.khz.madahi.ui.category.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends BaseActivity {

    ActivityCategoryBinding binding;
    CategoryViewModel       viewModel;
    List<Category>          categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding    = DataBindingUtil.setContentView(this, R.layout.activity_category);
        categories = databaseHelper.categoryDAO()
                                   .getAll();
        viewModel  = new CategoryViewModel(this, new CategoryAdapter(this, categories));
        binding.setViewModel(viewModel);
        setBaseActivityValues(this, binding.getRoot(), this.getClass().getSimpleName());
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setTitle("خروج")
                                     .setMessage("از برنامه خارج می شوید؟")
                                     .setPositiveButton("بله", (dialogInterface, i) -> finish())
                                     .setNegativeButton("خیر", null)
                                     .show();
    }

}