package com.khz.madahi.ui.favorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.databinding.ActivityFavoritesBinding;
import com.khz.madahi.models.Content;
import com.khz.madahi.ui.content.adapter.ContentAdapter;
import com.khz.madahi.ui.content.viewmodels.ContentViewModel;

import java.util.List;

public class FavoritesActivity extends BaseActivity {

    ActivityFavoritesBinding binding;
    ContentViewModel         viewModel;
    ContentAdapter           adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites);
        List<Content> items = databaseHelper.contentDAO()
                                            .getAllFavorites();
        if (items.size() == 0) {
            onBackPressed();
            finish();
            return;
        }
        log("Size : " + items.size());
        adapter   = new ContentAdapter(this, items);
        viewModel = new ContentViewModel(this, adapter);
        binding.setViewModel(viewModel);
        setBaseActivityValues(this, binding.getRoot(), this.getClass().getSimpleName());
    }

}