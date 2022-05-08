package com.khz.madahi.ui.content.viewmodels;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.khz.madahi.BR;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.Const;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.ui.content.ContentActivity;
import com.khz.madahi.ui.contentdetail.ContentDetailActivity;


public class ContentItemViewModel extends BaseObservable {

    BaseActivity activity;
    Content      content;
    String       contentType = "";

    public ContentItemViewModel(BaseActivity activity, Content content) {
        this.activity = activity;
        this.content  = content;
        setContentType(content.getContentType() == Const.ContentType.NOHEH ? "نوحه" : "روضه");

    }

    @Bindable
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
        notifyPropertyChanged(BR.contentType);
    }

    @Bindable
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    public void onContentClick(View view) {
        Intent intent = new Intent(activity, ContentDetailActivity.class);
        intent.putExtra("content", content);
        activity.startActivity(intent);
    }

}
