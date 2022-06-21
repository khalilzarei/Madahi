package com.khz.madahi.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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
        setBaseActivityValues(this, binding.getRoot(), this.getClass()
                                                           .getSimpleName());

        WebView webView = binding.webView;

        webView.setVerticalScrollBarEnabled(false);

        String privacy = "<html>\n" + "<head>\n" + "    <meta charset=\"UTF-8\"/>\n" + "</head>\n" +
                "<body style=\"text-align: center;background: #192A19;\">\n" + "<P style=\"color: yellow;direction: rtl\">\n" +
                "    با ورود یاثبت نام در دفترچه مداحی شما\n" +
                "    <a href='https://madahinote.ir/page/terms/' style=\"color: yellow\">شرایط و قوانین</a>\n" +
                "    استفاده از سرویس های سایت و\n" +
                "    <a href='https://madahinote.ir/page/privacy/' style=\"color: yellow\">قوانین حریم خصوصی</a>\n" +
                "    آن را می پذیرید.\n" + "</P>\n" + "</body>\n" + "</html>";

        webView.loadDataWithBaseURL(null, privacy, "text/html", "UTF-8", null);
    }

}