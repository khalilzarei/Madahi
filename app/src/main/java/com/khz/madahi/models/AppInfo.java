package com.khz.madahi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("version_code")
    @Expose
    private String versionCode;
    @SerializedName("version_name")
    @Expose
    private String versionName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("app_url")
    @Expose
    private String appUrl;
    @SerializedName("create_at")
    @Expose
    private String createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

}
