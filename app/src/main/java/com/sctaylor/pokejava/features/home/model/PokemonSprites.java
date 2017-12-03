package com.sctaylor.pokejava.features.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by simon on 12/3/2017.
 */

public class PokemonSprites {
    @SerializedName("front_default")
    @Expose
    public String frontDefault;

    @SerializedName("back_default")
    @Expose
    public String backDefault;

    @SerializedName("front_shiny")
    @Expose
    public String frontShiny;

    @SerializedName("back_shiny")
    @Expose
    public String backShiny;
}
