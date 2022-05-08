package com.khz.madahi.db;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.khz.madahi.helper.Const;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.models.Favorite;


@Database(entities = {
        Category.class,
        Content.class,
        Favorite.class
}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract FavoriteDAO favoriteDAO();

    public abstract ContentDAO contentDAO();


    public abstract CategoryDAO categoryDAO();


    private static DatabaseHelper databaseHelper;

    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ DatabaseHelper getInstance(Context context) {
        if (null == databaseHelper) {
            databaseHelper = buildDatabaseInstance(context);
        }
        return databaseHelper;
    }

    private static DatabaseHelper buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, DatabaseHelper.class, Const.DB_NAME).allowMainThreadQueries().build();
    }

    public void cleanUp() {
        databaseHelper = null;
    }

    public void clearTables() {
        this.favoriteDAO().deleteTable();
        this.contentDAO().deleteTable();
        this.categoryDAO().deleteTable();
    }
}
