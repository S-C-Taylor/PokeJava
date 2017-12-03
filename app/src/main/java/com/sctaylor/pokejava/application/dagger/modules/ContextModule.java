package com.sctaylor.pokejava.application.dagger.modules;

import android.content.Context;

import com.sctaylor.pokejava.application.dagger.scopes.PokeApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by simon on 12/3/2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @PokeApplicationScope
    public Context context(){
        return this.context;
    }
}
