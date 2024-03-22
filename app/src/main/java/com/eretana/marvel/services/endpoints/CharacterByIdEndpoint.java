package com.eretana.marvel.services.endpoints;

import com.eretana.marvel.model.CharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterByIdEndpoint {

    @GET("characters/{id}")
    Call<CharactersResponse> getCharacterById(
            @Path("id") int id,
            @Query("ts") long timestamp,
            @Query("apikey") String apikey,
            @Query("hash") String hash);

}
