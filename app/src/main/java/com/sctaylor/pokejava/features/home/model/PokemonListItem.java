package com.sctaylor.pokejava.features.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by simon on 12/4/2017.
 */

public class PokemonListItem {

    @SerializedName("results")
    @Expose
    public List<Pokemon> pokemonList;
}
