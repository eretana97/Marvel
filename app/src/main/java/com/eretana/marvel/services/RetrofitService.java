package com.eretana.marvel.services;

import com.eretana.marvel.services.endpoints.CharacterByIdEndpoint;
import com.eretana.marvel.services.endpoints.CharactersEndpoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    public static String MARVEL_PUBLIC_KEY = "95ff5465dd2a684665d40290e3586b08";
    public static String MARVEL_PRIVATE_KEY = "f1938d6e84678cd99afae3eaf684e9fc26867992";

    public RetrofitService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public CharactersEndpoint callCharactersEndpoint(){
        return retrofit.create(CharactersEndpoint.class);
    }

    public CharacterByIdEndpoint callCharacterByIdEndpoint(){
        return retrofit.create(CharacterByIdEndpoint.class);
    }


}
