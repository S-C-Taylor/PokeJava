package com.sctaylor.pokejava.features.home.dagger.modules;

import com.sctaylor.pokejava.application.network.PokeService;
import com.sctaylor.pokejava.features.home.AdapterPokes;
import com.sctaylor.pokejava.features.home.HomeActivity;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by simon on 12/3/2017.
 */

@Module
public class HomeActivityModule {
    private final HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity){
        this.homeActivity = homeActivity;
    }

    @Provides
    public AdapterPokes adapterPokes(Picasso picasso){
        return new AdapterPokes(homeActivity, picasso);
    }
}
