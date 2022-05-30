package com.khz.madahi.ui.contentdetail.viewmodel;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.khz.madahi.BR;
import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.SessionManager;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.Favorite;
import com.khz.madahi.models.response.InsertFavoriteResponse;
import com.khz.madahi.models.response.LoginResponse;
import com.khz.madahi.network.APIService;
import com.khz.madahi.network.RetroClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;


public class ContentDetailViewModel extends BaseObservable {

    BaseActivity activity;
    Content      content;
    int          fontSize;
    boolean      isFavorite;
    int          favoriteIcon = R.drawable.ic_favorite_border;

    public ContentDetailViewModel(BaseActivity activity, Content content) {
        this.activity = activity;
        this.content  = content;
        int userId = Integer.parseInt(SessionManager.getUser()
                                                    .getId());
        int contentId = Integer.parseInt(content.getId());
        Favorite favorite = activity.databaseHelper.favoriteDAO()
                                                   .getFavoriteWithUserIdAndContentId(userId, contentId);

        if (favorite != null) {
            isFavorite = true;
            setFavoriteIcon(R.drawable.ic_favorite);
        } else {
            isFavorite = false;
            setFavoriteIcon(R.drawable.ic_favorite_border);
        }
    }

    @Bindable
    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
        notifyPropertyChanged(BR.isFavorite);
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

    @Bindable
    public int getFavoriteIcon() {
        return favoriteIcon;
    }

    public void setFavoriteIcon(int favoriteIcon) {
        this.favoriteIcon = favoriteIcon;
        notifyPropertyChanged(BR.favoriteIcon);
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

    public void insertFavorite(View view) {
        insertFavoriteToServer(view);
    }

    @BindingAdapter("setImageResource")
    public static void setImageResource(ImageView imageView, int res) {
        Glide.with(imageView)
             .load(res)
             .into(imageView);
    }

    private void insertFavoriteToServer(View view) {
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        APIService apiService = RetroClass.getAPIService();
        Call<InsertFavoriteResponse> responseCall = apiService.insertFavorite(SessionManager.getUser()
                                                                                            .getId(), content.getId());
        responseCall.enqueue(new Callback<InsertFavoriteResponse>() {
            @Override
            public void onResponse(Call<InsertFavoriteResponse> call, Response<InsertFavoriteResponse> response) {

                InsertFavoriteResponse resultResponse = response.body();
                activity.log("login : " + response.body().getErrorMsg() + "");
                if (response.isSuccessful() && resultResponse != null) {
                    if (!resultResponse.getError()) {
                        Favorite favorite = resultResponse.getFavorite();
                        activity.databaseHelper.favoriteDAO()
                                               .insert(favorite);
                        activity.showSuccessSnackBar(resultResponse.getErrorMsg());
                        setFavoriteIcon(R.drawable.ic_favorite);
                        setIsFavorite(true);
                    } else {
                        activity.showErrorSnackBar(resultResponse.getErrorMsg());
                    }
                }
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }


            @Override
            public void onFailure(Call<InsertFavoriteResponse> call, Throwable t) {
                Log.e("onFailure", t.getMessage() + " ");
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }
        });

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
