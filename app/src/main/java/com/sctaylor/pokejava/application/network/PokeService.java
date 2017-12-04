package com.sctaylor.pokejava.application.network;

import com.sctaylor.pokejava.features.home.model.PokemonForm;
import com.sctaylor.pokejava.features.home.model.PokemonListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by simon on 12/3/2017.
 */

public interface PokeService {
    @GET("pokemon-form/{pokemon}")
    Call<PokemonForm> getPokemon(@Path("pokemon") String pokemon);

    @GET("pokemon-form/{pokemon}")
    Call<PokemonForm> getPokemon(@Path("pokemon") int id);

    @GET("pokemon-form")
    Call<PokemonListItem> getPokemonList(@Query("limit") int limit);
}
