package com.khz.madahi.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.khz.madahi.models.Content;

import java.util.List;

public class DataResponse {

    @SerializedName("error")
    @Expose
    private Boolean       error;
    @SerializedName("error_msg")
    @Expose
    private String        errorMsg;
    @SerializedName("data")
    @Expose
    private List<Content> contents = null;

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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

}
