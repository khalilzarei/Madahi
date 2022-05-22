package com.khz.madahi.ui.content;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.databinding.ActivityContentBinding;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.ui.content.adapter.ContentAdapter;
import com.khz.madahi.ui.content.viewmodels.ContentViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends BaseActivity {

    ActivityContentBinding binding;
    ContentViewModel       viewModel;
    List<Content>          items = new ArrayList<>();
    ContentAdapter         adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content);
        Category category = (Category) getIntent().getSerializableExtra("category");
        setView(binding.getRoot());
        if (category == null) {
            onBackPressed();
            finish();
            return;
        }
        adapter   = new ContentAdapter(this, items);
        viewModel = new ContentViewModel(this, adapter, category);
        binding.setViewModel(viewModel);
        items = databaseHelper
                .contentDAO()
                .getAllWithCategoryId(category.getId());
        adapter.setData(items);
    }
}