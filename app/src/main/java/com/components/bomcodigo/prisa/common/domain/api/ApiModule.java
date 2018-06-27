package com.components.bomcodigo.prisa.common.domain.api;

import android.content.Context;


import com.components.bomcodigo.prisa.common.domain.api.deserealizers.DateUtils;
import com.components.bomcodigo.prisa.di.AppModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;


import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


@Module(includes = {AppModule.class})
public class ApiModule {


    @Provides
    Api build(Retrofit retrofit){
        return retrofit.create(Api.class);
    }



    @Provides
    Retrofit provideRetrofit(Gson gson,
                             OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl("http://www.prisa.com.br/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }



    @Provides
    OkHttpClient provideOkhttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    Cache provideHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class,new DateUtils.DateTypeDeserializer())
                .setLenient()
                .create();
    }
}
