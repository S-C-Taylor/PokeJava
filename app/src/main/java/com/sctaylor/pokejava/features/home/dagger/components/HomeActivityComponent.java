package com.sctaylor.pokejava.features.home.dagger.components;

import com.sctaylor.pokejava.features.home.AdapterPokes;
import com.sctaylor.pokejava.application.dagger.components.PokeApplicationComponent;
import com.sctaylor.pokejava.features.home.HomeActivity;
import com.sctaylor.pokejava.features.home.dagger.modules.HomeActivityModule;
import com.sctaylor.pokejava.features.home.dagger.scopes.HomeActivityScope;

import dagger.Component;

/**
 * Created by simon on 12/3/2017.
 */

@HomeActivityScope
@Component(modules = {HomeActivityModule.class}, dependencies = {PokeApplicationComponent.class})
public interface HomeActivityComponent {

    void injectHomeActivity(HomeActivity homeActivity);

}
