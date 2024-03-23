package com.eretana.marvel.viewmodel;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.model.EndpointParameters;
import com.eretana.marvel.model.CharactersResponse;
import com.eretana.marvel.services.RetrofitService;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainVM extends ViewModel {

    private MutableLiveData<List<Character>> characters;

    private RetrofitService retrofitService;


    public MainVM(){
        characters = new MutableLiveData<>();
        retrofitService = new RetrofitService();
    }

    public LiveData<List<Character>> getCharaters(){
        return characters;
    }


    public void callService(String name, int limit, int offset){
        EndpointParameters parameters = new EndpointParameters();
        Call<CharactersResponse> call = retrofitService.callCharactersEndpoint().getCharacters(parameters.getTimestamp(),parameters.getApikey(),parameters.getHash(),name,limit,offset);
        call.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    characters.setValue(response.body().data.results);
                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable throwable) {
            }
        });
    }




}
