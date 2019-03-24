package com.stdio.hue.network.di;

import android.accounts.AuthenticatorException;

import com.stdio.hue.network.BuildConfig;
import com.stdio.hue.network.service.MovieService;
import com.stdio.hue.network.service.TVService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vanthien113 on 10/17/18.
 */

@Module
public class NetworkModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(15, TimeUnit.SECONDS);
        httpClient.readTimeout(15, TimeUnit.SECONDS);
        httpClient.writeTimeout(15, TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        httpClient.addInterceptor(chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            try {
                int code = response.code();
                if (code < 200 || code > 299) {
                    if (code == 401) {
                        throw new AuthenticatorException();
                    }
                }
            } catch (Exception e) {
                if (e instanceof IOException) {
                    throw new IOException(e.getMessage());
                }
            }
            return response;
        });
        httpClient.addInterceptor(logging);
        return httpClient;
    }

    @Provides
    @Named("Base")
    Retrofit providesRetrofit(OkHttpClient.Builder httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Provides
    public MovieService provideMovieService(@Named("Base") Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    public TVService provideTVService(@Named("Base") Retrofit retrofit) {
        return retrofit.create(TVService.class);
    }
}
