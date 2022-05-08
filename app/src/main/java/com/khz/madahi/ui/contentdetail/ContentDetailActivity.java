package com.khz.madahi.ui.contentdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.databinding.ActivityContentDetailBinding;
import com.khz.madahi.models.Content;
import com.khz.madahi.ui.contentdetail.viewmodel.ContentDetailViewModel;

public class ContentDetailActivity extends BaseActivity {

    ActivityContentDetailBinding binding;
    ContentDetailViewModel       viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_detail);
        Content content = (Content) getIntent().getSerializableExtra("content");
        if (content == null) {
            toast("متنی یافت نشد");
            onBackPressed();
            return;
        }
        viewModel = new ContentDetailViewModel(this, content);
        binding.setViewModel(viewModel);
    }
}