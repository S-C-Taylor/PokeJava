package com.sctaylor.pokejava.application.dagger.modules;

import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sctaylor.pokejava.application.dagger.scopes.PokeApplicationScope;
import com.sctaylor.pokejava.application.network.PokeService;

import org.joda.time.DateTime;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by simon on 12/3/2017.
 */

@Module(includes = {NetworkModule.class})
public class PokeServiceModule {

    @Provides
    @PokeApplicationScope
    public PokeService pokeService(Retrofit retrofit){
        return retrofit.create(PokeService.class);
    }

    @Provides
    @PokeApplicationScope
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @PokeApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://pokeapi.co/api/v2/")
                .build();
    }



}
