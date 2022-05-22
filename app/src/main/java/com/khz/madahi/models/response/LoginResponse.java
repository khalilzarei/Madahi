package com.khz.madahi.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.Favorite;
import com.khz.madahi.models.User;

import java.util.List;

public class LoginResponse {

    @SerializedName("user")
    private User user;

    @SerializedName("categories")
    private List<Category> categories = null;

    @SerializedName("favorites")
    private List<Favorite> favorites = null;

    @SerializedName("contents")
    private List<Content> contents = null;

    @SerializedName("error")
    private Boolean error;

    @SerializedName("error_msg")
    private String errorMsg;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

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

}