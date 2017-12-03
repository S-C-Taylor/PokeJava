package com.sctaylor.pokejava.features.home.model;

/**
 * Created by simon on 12/4/2017.
 */

public class PokemonListItem {
    private int id;
    private PokemonForm pokemonForm;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PokemonForm getPokemonForm() {
        return pokemonForm;
    }

    public void setPokemonForm(PokemonForm pokemonForm) {
        this.pokemonForm = pokemonForm;
    }
}
