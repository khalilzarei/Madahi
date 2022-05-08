package com.khz.madahi.ui.contentdetail.viewmodel;


import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.khz.madahi.BR;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.SessionManager;
import com.khz.madahi.models.Content;


public class ContentDetailViewModel extends BaseObservable {

    BaseActivity activity;
    Content      content;
    int          fontSize;


    public ContentDetailViewModel(BaseActivity activity, Content content) {
        this.activity = activity;
        this.content  = content;
    }

    @Bindable
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        notifyPropertyChanged(BR.fontSize);
    }

    public void increaseFontSize(View view) {
        int size = SessionManager.getFontSize();
        if (size < 35) {
            size++;
            setFontSize(size);
            SessionManager.setFontSize(size);
        }
        setContent(content);
    }

    public void decreaseFontSize(View view) {
        int size = SessionManager.getFontSize();
        if (size > 11) {
            size--;
            setFontSize(size);
            SessionManager.setFontSize(size);
        }
        setContent(content);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("setContent")
    public static void setContent(WebView webView, Content content) {
        String data = "<html>" + "<head>" +
                //                "<meta charset=\"UTF-8\">" +
                "<style>" + "@font-face {" + "font-family: MyFont;" + "src: url(\"file:///android_asset/fonts/" +
                SessionManager.getFont() + "\");" + "}" + "body {" + "font-family: MyFont;" + "background-color: #192A19;" +
                "text-align: center;" + "direction: rtl;" + "color: white;" + "width: 100%;" + "}" + "p {" + "font-size: " +
                SessionManager.getFontSize() + "px;" + "padding: 0px;" + "}" + "</style>" + "</head>" + "<body>" +
                content.getContent() + "</body>" + "</html>";
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        webView.loadDataWithBaseURL("", data, mimeType, encoding, "");
    }


}
