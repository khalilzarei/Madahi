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
    void insert(Favorite homeScene);

    @Delete
    void delete(Favorite homeScene);

    @Update
    void update(Favorite homeScene);


    @Query("SELECT * FROM " + Const.TABLE_NAME_FAVORITE + " WHERE contentId=:roomId")
    List<Favorite> getAllWithRoomId(int roomId);


    @Query("DELETE FROM " + Const.TABLE_NAME_FAVORITE)
    void deleteTable();
}
