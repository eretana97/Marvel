package com.eretana.marvel.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.eretana.marvel.model.Character;

@Database(entities = {Character.class}, version = 1)
public abstract class FavsDatabase extends RoomDatabase {
    public abstract DaoCharacter daoCharacter();
}
