package com.khz.madahi.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.khz.madahi.helper.Const;
import com.khz.madahi.models.Favorite;

import java.util.List;

@Dao
public interface FavoriteDAO {

    @Query("SELECT * FROM " + Const.TABLE_NAME_FAVORITE)
    List<Favorite> getAll();

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Update
    void update(Favorite favorite);


    @Query("SELECT * FROM " + Const.TABLE_NAME_FAVORITE + " WHERE contentId=:contentId")
    List<Favorite> getAllWithRoomId(int contentId);

    @Query("SELECT * FROM " + Const.TABLE_NAME_FAVORITE + " WHERE contentId=:contentId AND userId=:userId")
    Favorite getFavoriteWithUserIdAndContentId(int userId, int contentId);


    @Query("DELETE FROM " + Const.TABLE_NAME_FAVORITE)
    void deleteTable();
}
