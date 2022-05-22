package com.khz.madahi.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.khz.madahi.models.Category;

public class AddCategoryResponse {

    @SerializedName("error")
    @Expose
    private boolean  error;

    @SerializedName("error_msg")
    @Expose
    private String   errorMsg;

    @SerializedName("category")
    @Expose
    private Category category;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
