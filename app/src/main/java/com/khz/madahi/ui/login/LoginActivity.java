package com.khz.madahi.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;
    LoginViewModel       viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding   = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new LoginViewModel(this);
        binding.setViewModel(viewModel);
        setView(binding.getRoot());
    }
}