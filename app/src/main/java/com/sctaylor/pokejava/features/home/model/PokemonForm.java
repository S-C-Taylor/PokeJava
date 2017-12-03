package com.sctaylor.pokejava.features.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by simon on 12/3/2017.
 */

public class PokemonForm {

    @SerializedName("sprites")
    @Expose
    public PokemonSprites sprites;

    @SerializedName("pokemon")
    @Expose
    public Pokemon pokemon;

    @SerializedName("id")
    @Expose
    public int id;
}
