package com.eretana.marvel.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.model.CharactersResponse;
import com.eretana.marvel.model.Comic;
import com.eretana.marvel.model.ComicsResponse;
import com.eretana.marvel.model.EndpointParameters;
import com.eretana.marvel.services.RetrofitService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoVM extends ViewModel {

    private MutableLiveData<Character> character;
    private MutableLiveData<List<Comic>> comics;
    private RetrofitService retrofitService;
    private CharacterRepository repository;




    public InfoVM(Context ctx) {
        this.character = new MutableLiveData<>();
        this.comics = new MutableLiveData<>();
        this.retrofitService = new RetrofitService();
        repository = new CharacterRepository(ctx);
    }

    public LiveData<Character> getCharacterInfo(){return character;}
    public LiveData<Boolean> isAlreadySaved(int id) {
        return repository.ifCharacterExist(id);
    }
    public LiveData<List<Comic>> getComicsList() {return comics;}

    public void callService(int id){
        EndpointParameters parameters = new EndpointParameters();
        Call<CharactersResponse> call = retrofitService.callCharacterByIdEndpoint().getCharacterById(id,parameters.getTimestamp(),parameters.getApikey(),parameters.getHash());
        call.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                if(response.isSuccessful()){
                    character.setValue(response.body().data.results.get(0));
                }else{

                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable throwable) {

            }
        });



    }

    public void callComicService(int id){

        EndpointParameters parameters = new EndpointParameters();
        Call<ComicsResponse> call = retrofitService.callCharacterComicsEndpoint().getCharacterComics(id,"comic","comic",parameters.getTimestamp(),parameters.getApikey(),parameters.getHash());
        call.enqueue(new Callback<ComicsResponse>() {
            @Override
            public void onResponse(Call<ComicsResponse> call, Response<ComicsResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    comics.setValue(response.body().data.results);
                }
            }

            @Override
            public void onFailure(Call<ComicsResponse> call, Throwable throwable) {

            }
        });
    }


}
