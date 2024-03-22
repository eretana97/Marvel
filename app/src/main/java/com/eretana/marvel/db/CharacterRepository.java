package com.eretana.marvel.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.eretana.marvel.model.Character;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CharacterRepository {

    private DaoCharacter dao;
    private Executor executor = Executors.newSingleThreadExecutor();


    public CharacterRepository(Context ctx){
        FavsDatabase db = Room.databaseBuilder(ctx,FavsDatabase.class,"favs-database").build();
        dao = db.daoCharacter();
    }

    public List<Character> getAllFavorites(){
        return dao.getAll();
    }


    public void insertFavorite(Character c){
        try {
            executor.execute(()->dao.insert(c));
        }catch (Exception e){
            Log.e("MARVEL_ROOM",e.getMessage());
        }
    }

    public boolean ifCharacterExist(int id){
        return dao.exist(id);
    }

}
