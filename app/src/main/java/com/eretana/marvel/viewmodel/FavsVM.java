package com.eretana.marvel.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;

import java.util.List;

public class FavsVM extends ViewModel {

    private LiveData<List<Character>> characters;

    public LiveData<List<Character>> getFavorites() {return characters;}

    public FavsVM(Application app) {
        CharacterRepository repository = new CharacterRepository(app);
        characters = repository.getAllFavorites();
    }




}
