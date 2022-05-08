package com.khz.madahi.ui.category;


import android.os.Bundle;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        setView(binding.getRoot());
        categories = databaseHelper.categoryDAO().getAll();
        viewModel  = new CategoryViewModel(this, new CategoryAdapter(this, categories));
        binding.setViewModel(viewModel);
    }

}