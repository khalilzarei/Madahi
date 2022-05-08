package com.khz.madahi.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.khz.madahi.helper.Const;

import java.io.Serializable;

@Entity(tableName = Const.TABLE_NAME_CATEGORIES)
public class Category implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idCategory;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("night")
    private String night;

    public Category(String id, String title, String night) {
        this.id    = id;
        this.title = title;
        this.night = night;
    }


    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

}