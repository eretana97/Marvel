package com.eretana.marvel.viewmodel;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.model.CharactersResponse;
import com.eretana.marvel.model.EndpointParameters;
import com.eretana.marvel.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoVM extends ViewModel {

    private MutableLiveData<Character> character;
    private MutableLiveData<Boolean> saved;
    private RetrofitService retrofitService;
    private CharacterRepository repository;


    public InfoVM(Context ctx) {
        this.character = new MutableLiveData<>();
        this.saved = new MutableLiveData<>();
        this.retrofitService = new RetrofitService();
        this.repository = new CharacterRepository(ctx);
    }

    public LiveData<Character> getCharacterInfo(){return character;}
    public LiveData<Boolean> ifSaved() {return saved; }

    public void callService(int id){
        EndpointParameters parameters = new EndpointParameters();
        Call<CharactersResponse> call = retrofitService.callCharacterByIdEndpoint().getCharacterById(id,parameters.getTimestamp(),parameters.getApikey(),parameters.getHash());
        call.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                if(response.isSuccessful()){
                    Log.d("MARVEL_INFOVM","CHARACTER NAME: " + response.body().data.results.get(0).name);
                    Log.d("MARVEL_INFOVM","CHARACTER DESCRIPTION: " + response.body().data.results.get(0).description);
                    character.setValue(response.body().data.results.get(0));
                }else{
                    Log.d("MARVEL_INFOVM","CODE:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable throwable) {
                Log.d("MARVEL_INFOVM","ERROR: " + throwable.getMessage());
            }
        });
    }

    public void findCharacter(int id){
        new AsyncTask<Integer,Void,Boolean>(){

            @Override
            protected Boolean doInBackground(Integer... integers) {
                return repository.ifCharacterExist(integers[0]);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                saved.setValue(aBoolean);
            }
        }.execute(id);
    }
}
