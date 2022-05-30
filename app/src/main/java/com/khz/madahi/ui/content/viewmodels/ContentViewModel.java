package com.khz.madahi.ui.content.viewmodels;

import android.app.ProgressDialog;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khz.madahi.BR;
import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.SessionManager;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.response.AddContentResponse;
import com.khz.madahi.network.APIService;
import com.khz.madahi.network.RetroClass;
import com.khz.madahi.ui.content.adapter.ContentAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentViewModel extends BaseObservable {
    ContentAdapter contentAdapter;
    BaseActivity   activity;
    Category       category;
    String         contentTitle   = "";
    String         contentAnswer  = "";
    String         contentContent = "";
    boolean        isVisible      = false;
    boolean        isNoheh        = false;

    public ContentViewModel(BaseActivity activity, ContentAdapter contentAdapter, Category category) {
        this.activity       = activity;
        this.contentAdapter = contentAdapter;
        this.category       = category;
    }

    public ContentViewModel(BaseActivity activity, ContentAdapter contentAdapter) {
        this.activity       = activity;
        this.contentAdapter = contentAdapter;
    }

    @Bindable
    public boolean getIsNoheh() {
        return isNoheh;
    }

    public void setIsNoheh(boolean noheh) {
        isNoheh = noheh;
        notifyPropertyChanged(BR.isNoheh);
    }

    @Bindable
    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
        notifyPropertyChanged(BR.contentTitle);
    }

    @Bindable
    public String getContentAnswer() {
        return contentAnswer;
    }

    public void setContentAnswer(String contentAnswer) {
        this.contentAnswer = contentAnswer;
        notifyPropertyChanged(BR.contentAnswer);
    }

    @Bindable
    public String getContentContent() {
        return contentContent;
    }

    public void setContentContent(String contentContent) {
        this.contentContent = contentContent;
        notifyPropertyChanged(BR.contentContent);
    }

    @Bindable
    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean visible) {
        isVisible = visible;
        notifyPropertyChanged(BR.isVisible);
    }

    @Bindable
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        notifyPropertyChanged(BR.category);
    }

    @Bindable
    public ContentAdapter getContentAdapter() {
        return contentAdapter;
    }

    public void setContentAdapter(ContentAdapter contentAdapter) {
        this.contentAdapter = contentAdapter;
        notifyPropertyChanged(BR.contentAdapter);
    }

    @BindingAdapter("setContentItems")
    public static void setContentItems(RecyclerView recyclerView, ContentAdapter adapter) {
        //        RoomAdapter adapter = new RoomAdapter((RoomAdapter.HomeRoomClickListener) recyclerView.getContext(), rooms);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    public void addContent(View view) {
        if (contentTitle == null || contentTitle.isEmpty()) {
            activity.toast("لطفا عنوان را وارد کنید!");
            return;
        }
        if (contentAnswer == null || contentAnswer.isEmpty()) {
            activity.toast("لطفا جواب را وارد کنید!");
            return;
        }
        if (contentContent == null || contentContent.isEmpty()) {
            activity.toast("لطفا متن را وارد کنید!");
            return;
        }
        String userId = SessionManager
                .getUser()
                .getId();
        String contentMsg  = activity.getHtmlTag(contentContent);
        String contentType = isNoheh ? "نوحه" : "روضه";

        Content content = new Content("", category.getId(), userId, contentAnswer, contentMsg, contentTitle, contentType);

        if (!activity.isNetworkConnected()) {
            activity.databaseHelper
                    .contentDAO()
                    .insert(content);
            contentAdapter.addContent(content);
            setIsVisible(!getIsVisible());
        } else {
            addContentToServer(view, content);
        }
    }


    //region Method
    private void addContentToServer(View view, Content content) {
        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        String userId = SessionManager
                .getUser()
                .getId();
        String categoryId  = category.getId();
        String answer      = content.getAnswer();
        String contentMsg  = content.getContent();
        String subject     = content.getSubject();
        String contentType = isNoheh ? "0" : "1";
        activity.log(
                categoryId + " answer : " + answer + " contentMsg : " + contentMsg + " subject : " + subject + " contentType: " +
                        contentType + " userId: " + userId);

        APIService apiService = RetroClass.getAPIService();
        Call<AddContentResponse> responseCall = apiService.insertContent(userId, categoryId, answer, contentMsg, subject,
                contentType);
        //        Call<AddContentResponse> responseCall = apiService.insertContent("1", "1", "answer", "<p>contentMsg</p>", "subject", "1");
        responseCall.enqueue(new Callback<AddContentResponse>() {
            @Override
            public void onResponse(Call<AddContentResponse> call, Response<AddContentResponse> response) {

                AddContentResponse resultResponse = response.body();
                activity.log(resultResponse.getErrorMsg() + " " + resultResponse.getError());
                if (response.isSuccessful()) {
                    if (!resultResponse.getError())
                        activity.showSuccessSnackBar(resultResponse.getErrorMsg());

                    if (!resultResponse.getError()) {
                        Content content = resultResponse.getContent();
                        contentAdapter.addContent(content);
                        activity.databaseHelper
                                .contentDAO()
                                .insert(content);
                    } else {
                        activity.showErrorSnackBar(resultResponse.getErrorMsg());
                    }

                    new Handler().postDelayed(progressDialog::dismiss, 1000);
                    setIsVisible(!getIsVisible());
                }
            }


            @Override
            public void onFailure(Call<AddContentResponse> call, Throwable t) {
                Log.e("onFailure", t.getMessage() + " ");
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }
        });

    }


    //endregion


    public void changeVisibility(View view) {
        setIsVisible(!getIsVisible());
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rbNoheh:
                if (checked)
                    setIsNoheh(true);
                break;
            case R.id.rbRoozeh:
                if (checked)
                    setIsNoheh(false);
                break;

        }
    }

}
