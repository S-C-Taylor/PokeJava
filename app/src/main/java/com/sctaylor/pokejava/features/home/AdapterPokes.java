package com.sctaylor.pokejava.features.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sctaylor.pokejava.R;
import com.sctaylor.pokejava.features.home.model.PokemonForm;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by simon on 12/3/2017.
 */

public class AdapterPokes extends BaseAdapter {

    private HomeActivity context;
    private Picasso picasso;
    private boolean shinySwitch;

    private List<PokemonForm> pokemonList = new ArrayList<PokemonForm>();

    public AdapterPokes(HomeActivity context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public PokemonForm getItem(int i) {
        return pokemonList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).id;
    }

    public void setShinySwitch(boolean shiny){
        this.shinySwitch = shiny;
        notifyDataSetChanged();
    }

    public void addItem(PokemonForm pokemonForm, int position){
        if(pokemonList.size() < position - 1){
            pokemonList.add(pokemonForm);
        }else {
            pokemonList.add(position - 1, pokemonForm);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View pokemonListItem = convertView;
        PokemonListItemHolder pokemonListItemHolder = null;
        PokemonForm pokemonForm = getItem(position);

        if(pokemonListItem == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Inflate the view
            pokemonListItem = inflater.inflate(R.layout.list_item_pokemon, parent, false); //Get the view
            pokemonListItemHolder = new PokemonListItemHolder(pokemonListItem);
            pokemonListItem.setTag(pokemonListItemHolder); //Attach reference to the view
        } else {

            pokemonListItemHolder = (PokemonListItemHolder) pokemonListItem.getTag(); //Load holder from memory

            String sprite = (shinySwitch) ? (pokemonForm.sprites.frontShiny) : (pokemonForm.sprites.frontDefault);

            picasso.load(sprite)
                    .into(pokemonListItemHolder.getPokemonImage());

            String pokemonName = pokemonForm.pokemon.name;
            pokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1);
            pokemonListItemHolder.getPokemonName().setText(pokemonName);

            String pokemonId = "#" + pokemonForm.id;
            pokemonListItemHolder.getPokemonId().setText(pokemonId);
        }
        return pokemonListItem;
    }

    private class PokemonListItemHolder{

        private TextView pokemonName;
        private ImageView pokemonImage;
        private TextView pokemonId;

        public PokemonListItemHolder(View view){
            this.pokemonName = view.findViewById(R.id.pokemon_name);
            this.pokemonImage = view.findViewById(R.id.pokemon_thumbnail);
            this.pokemonId = view.findViewById(R.id.pokemon_id);
        }

        public TextView getPokemonId() {
            return pokemonId;
        }

        public void setPokemonId(TextView pokemonId) {
            this.pokemonId = pokemonId;
        }

        public TextView getPokemonName() {
            return pokemonName;
        }

        public void setPokemonName(TextView pokemonName) {
            this.pokemonName = pokemonName;
        }

        public ImageView getPokemonImage() {
            return pokemonImage;
        }

        public void setPokemonImage(ImageView pokemonImage) {
            this.pokemonImage = pokemonImage;
        }
    }
}
