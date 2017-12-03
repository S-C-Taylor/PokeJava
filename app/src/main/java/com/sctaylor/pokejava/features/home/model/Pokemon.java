package com.sctaylor.pokejava.features.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by simon on 12/3/2017.
 */

public class Pokemon {
    @SerializedName("url")
    @Expose
    public String resource;

    @SerializedName("name")
    @Expose
    public String name;
}
