package com.khz.madahi.network;


import com.khz.madahi.models.response.AddCategoryResponse;
import com.khz.madahi.models.response.AddContentResponse;
import com.khz.madahi.models.response.DataResponse;
import com.khz.madahi.models.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {


    //region login

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("data") String data);

    //endregion

    //region login

    @FormUrlEncoded
    @POST("register.php")
    Call<LoginResponse> register(@Field("data") String data);

    //endregion

    //region Content With Category

    @FormUrlEncoded
    @POST("getContentWithCategory.php")
    Call<DataResponse> getContentWithCategory(@Field("category_id") String categoryId, @Field("user_id") String userId);

    //endregion

    //region User Contents

    @FormUrlEncoded
    @POST("getUserContents.php")
    Call<DataResponse> getUserContents(@Field("user_id") String userId);

    //endregion

    //region insert Category

    @FormUrlEncoded
    @POST("insertCategory.php")
    Call<AddCategoryResponse> insertCategory(@Field("user_id") String userId, @Field("msg") String title,
            @Field("description") String description);

    //endregion

    //region insert Contents

    @FormUrlEncoded
    @POST("insertContent.php")
    Call<AddContentResponse> insertContent(@Field("user_id") String userId, @Field("category_id") String categoryId,
            @Field("answer") String answer, @Field("content") String content, @Field("subject") String subject,
            @Field("content_type") String contentType);

    //endregion

}
