package com.khz.madahi.models.response;

import com.google.gson.annotations.SerializedName;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;

public class AddContentResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("error_msg")
    private String errorMsg;

    @SerializedName("content")
    private Content content;

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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
