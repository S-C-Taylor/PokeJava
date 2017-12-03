package com.sctaylor.pokejava.features.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.sctaylor.pokejava.R;
import com.sctaylor.pokejava.application.PokeApplication;
import com.sctaylor.pokejava.application.network.PokeService;
import com.sctaylor.pokejava.features.home.dagger.components.DaggerHomeActivityComponent;
import com.sctaylor.pokejava.features.home.dagger.components.HomeActivityComponent;
import com.sctaylor.pokejava.features.home.dagger.modules.HomeActivityModule;
import com.sctaylor.pokejava.features.home.model.PokemonForm;
import com.sctaylor.pokejava.features.home.model.PokemonListItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity {

    @Inject
    PokeService pokeService;

    @Inject
    AdapterPokes adapterPokes;

    @BindView(R.id.pokemon_list)
    ListView pokemonList;

    @BindView(R.id.shiny_switch)
    Switch shinySwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeActivityComponent component = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .pokeApplicationComponent(PokeApplication.get(this).getComponent())
                .build();

        ButterKnife.bind(this);

        component.injectHomeActivity(this);

        shinySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                adapterPokes.setShinySwitch(b);
            }
        });

        pokemonList.setAdapter(adapterPokes);

        Call<List<PokemonListItem>> pokemonListCall = pokeService.getPokemonList(151);

        pokemonListCall.enqueue(new Callback<List<PokemonListItem>>() {
            @Override
            public void onResponse(Call<List<PokemonListItem>> call, Response<List<PokemonListItem>> response) {
                
            }

            @Override
            public void onFailure(Call<List<PokemonListItem>> call, Throwable t) {

            }
        });

        for(int i = 1; i <= 151; i++){
            Call<PokemonForm> pokemonFormCall = pokeService.getPokemon(i);

            pokemonFormCall.enqueue(new Callback<PokemonForm>() {
                @Override
                public void onResponse(Call<PokemonForm> call, Response<PokemonForm> response) {
                    Timber.i(response.raw().toString());
                    Timber.i(Integer.toString(response.body().id));
                    adapterPokes.addItem(response.body(), response.body().id);
                }

                @Override
                public void onFailure(Call<PokemonForm> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, "Error getting pokemon " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
