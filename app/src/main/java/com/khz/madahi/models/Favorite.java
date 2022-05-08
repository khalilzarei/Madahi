package com.khz.madahi.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.khz.madahi.helper.Const;

import java.io.Serializable;

@Entity(tableName = Const.TABLE_NAME_FAVORITE)
public class Favorite implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idFavorite;

    private int contentId;

    public Favorite(int contentId) {
        this.contentId = contentId;
    }

    public int getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(int idFavorite) {
        this.idFavorite = idFavorite;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }
}
