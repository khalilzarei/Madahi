package com.khz.madahi.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.khz.madahi.models.Favorite;

public class InsertFavoriteResponse {

    @SerializedName("error")
    @Expose
    private Boolean  error;
    @SerializedName("error_msg")
    @Expose
    private String   errorMsg;
    @SerializedName("favorite")
    @Expose
    private Favorite favorite;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

}