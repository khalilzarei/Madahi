package com.khz.madahi.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.khz.madahi.helper.Const;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.Favorite;

import java.util.List;


@Dao
public interface ContentDAO {

    @Query("SELECT * FROM " + Const.TABLE_NAME_CONTENT)
    List<Content> getAll();

    @Insert
    void insert(Content content);

    @Delete
    void delete(Content content);

    @Update
    void update(Content content);

    @Query("SELECT * FROM " + Const.TABLE_NAME_CONTENT + " WHERE categoryId=:id")
    List<Content> getAllWithCategoryId(String id);


    @Query("SELECT * FROM " + Const.TABLE_NAME_CONTENT + " WHERE id in(SELECT contentId FROM " + Const.TABLE_NAME_FAVORITE + ")")
    List<Content> getAllFavorites();

    @Query("DELETE FROM " + Const.TABLE_NAME_CONTENT)
    void deleteTable();

}
