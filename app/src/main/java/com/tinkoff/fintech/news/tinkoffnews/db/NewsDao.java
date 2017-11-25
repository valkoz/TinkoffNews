package com.tinkoff.fintech.news.tinkoffnews.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tinkoff.fintech.news.tinkoffnews.network.NewsObject;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM newsobject ORDER by publicationDate DESC")
    List<NewsObject> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(NewsObject... newsObjects);
}
