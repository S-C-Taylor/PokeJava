package com.sctaylor.pokejava.application.dagger.components;

import com.sctaylor.pokejava.application.dagger.modules.PicassoModule;
import com.sctaylor.pokejava.application.dagger.modules.PokeServiceModule;
import com.sctaylor.pokejava.application.dagger.scopes.PokeApplicationScope;
import com.sctaylor.pokejava.application.network.PokeService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by simon on 12/3/2017.
 */

@PokeApplicationScope
@Component(modules = {PokeServiceModule.class, PicassoModule.class})
public interface PokeApplicationComponent {

    Picasso getPicasso();

    PokeService getPokeService();
}
