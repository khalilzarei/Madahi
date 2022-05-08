package com.khz.madahi.ui.content.viewmodels;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khz.madahi.BR;
import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.Const;
import com.khz.madahi.helper.SessionManager;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.User;
import com.khz.madahi.network.APIService;
import com.khz.madahi.network.LoginResponse;
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


    public void addCategory(View view) {
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


        activity.databaseHelper.contentDAO().insert(new Content("", category.getId(), "1", contentAnswer, activity
                .getHtmlTag(contentContent), contentTitle, isNoheh ? Const.ContentType.NOHEH : Const.ContentType.ROZEH));
        contentAdapter.setData(activity.databaseHelper.contentDAO().getAllWithCategoryId(category.getId()));
        setIsVisible(!getIsVisible());
    }

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

    //region Method
    private void login(View view) {
   /*     ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        APIService          apiService   = RetroClass.getAPIService();
        Call<LoginResponse> responseCall = apiService.login(getEmail(), getPassword());
        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("login", response.body() + "");
                    String  message = response.body().getErrorMsg();
                    boolean error   = response.body().getError();
                    if (!error) {
                        activity.showSuccessSnackBar(message);
                        User    user    = response.body().getUser();
                        Project project = response.body().getProject();
                        SessionManager.setUser(user);
                        SessionManager.setProject(project);
                        SessionManager.setIsLoggedIn(true);
                        new Handler().postDelayed(() -> {
                            progressDialog.dismiss();
                            activity.startActivity(new Intent(activity, HomeActivity.class));
                            activity.finish();
                        }, 1000);
                    } else {
                        activity.showErrorSnackBar(message);
                        new Handler().postDelayed(progressDialog::dismiss, 1000);
                    }

                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("onFailure", t.getMessage() + " ");
                new Handler().postDelayed(progressDialog::dismiss, 1000);
            }
        });
*/
    }

    //endregion

}
