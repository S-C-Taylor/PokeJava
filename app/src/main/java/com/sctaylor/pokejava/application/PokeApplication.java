package com.sctaylor.pokejava.application;

import android.app.Activity;
import android.app.Application;

import com.sctaylor.pokejava.application.dagger.components.DaggerPokeApplicationComponent;
import com.sctaylor.pokejava.application.dagger.components.PokeApplicationComponent;
import com.sctaylor.pokejava.application.dagger.modules.ContextModule;
import com.sctaylor.pokejava.application.network.PokeService;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

/**
 * Created by simon on 12/3/2017.
 */

public class PokeApplication extends Application {

    private PokeService pokeService;
    private Picasso picasso;
    private PokeApplicationComponent component;

    public static PokeApplication get(Activity activity) {
        return (PokeApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        component = DaggerPokeApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        pokeService = component.getPokeService();
        picasso = component.getPicasso();
    }

    public PokeApplicationComponent getComponent(){
        return this.component;
    }
}
