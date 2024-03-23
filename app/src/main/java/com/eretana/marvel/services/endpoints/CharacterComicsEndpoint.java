package com.eretana.marvel.services.endpoints;

import com.eretana.marvel.model.ComicsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterComicsEndpoint {


    @GET("characters/{id}/comics")
    Call<ComicsResponse> getCharacterComics(
            @Path("id") int id,
            @Query("format") String format,
            @Query("formatType") String formatType,
            @Query("ts") long timestamp,
            @Query("apikey") String apikey,
            @Query("hash") String hash);


}
