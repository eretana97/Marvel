package com.eretana.marvel.services.endpoints;

import com.eretana.marvel.model.CharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharactersEndpoint {

    @GET("characters")
    Call<CharactersResponse> getCharacters(
            @Query("ts") long timestamp,
            @Query("apikey") String apikey,
            @Query("hash") String hash,
            @Query("nameStartsWith") String name,
            @Query("limit") int limit,
            @Query("offset") int offset);

}
