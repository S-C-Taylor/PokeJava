package com.sctaylor.pokejava.application.dagger.modules;

import android.content.Context;

import com.sctaylor.pokejava.application.dagger.scopes.PokeApplicationScope;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by simon on 12/3/2017.
 */

@Module(includes = {ContextModule.class})
public class NetworkModule {

    @Provides
    @PokeApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache, Interceptor networkInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @PokeApplicationScope
    public HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    @Provides
    @PokeApplicationScope
    public Interceptor networkInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed( chain.request() );
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge( 60, TimeUnit.MINUTES )
                        .build();

                return response.newBuilder()
                        .header("Cache-Control", cacheControl.toString() )
                        .build();
            }
        };
    }

    @Provides
    @PokeApplicationScope
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10 * 1000 * 1000); //10mb cache
    }

    @Provides
    @PokeApplicationScope
    public File cacheFile(Context context){
        return new File(context.getCacheDir(), "okhttp_cache");
    }
}
