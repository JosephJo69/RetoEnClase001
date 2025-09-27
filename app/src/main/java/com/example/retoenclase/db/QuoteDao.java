package com.example.retoenclase.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.retoenclase.model.Quote;
import java.util.List;

@Dao
public interface QuoteDao {
    @Insert
    void insert(Quote quote);

    @Query("SELECT * FROM quotes ORDER BY timestamp DESC")
    List<Quote> getAllQuotes();
}
