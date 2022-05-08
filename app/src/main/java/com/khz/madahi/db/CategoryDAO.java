package com.khz.madahi.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.khz.madahi.helper.Const;
import com.khz.madahi.models.Category;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM " + Const.TABLE_NAME_CATEGORIES)
    List<Category> getAll();

    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);

    @Update
    void update(Category category);

    @Query("DELETE FROM " + Const.TABLE_NAME_CATEGORIES)
    void deleteTable();
}