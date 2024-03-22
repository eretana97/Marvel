package com.eretana.marvel.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.eretana.marvel.model.Character;

import java.util.List;

@Dao
public interface DaoCharacter {

    @Query("SELECT EXISTS (SELECT 1 FROM characters WHERE id = :characterId)")
    boolean exist(int characterId);

    @Query("SELECT * FROM characters")
    LiveData<List<Character>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Character character);

    @Delete
    void delete(Character character);



}
