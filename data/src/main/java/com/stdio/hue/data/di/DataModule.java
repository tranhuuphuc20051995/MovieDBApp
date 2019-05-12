package com.stdio.hue.data.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.stdio.hue.data.usecases.MovieUseCase;
import com.stdio.hue.data.usecases.TVShowUseCase;
import com.stdio.hue.data.usecases.impl.MovieUseCaseImpl;
import com.stdio.hue.data.usecases.impl.TVShowUseCaseImpl;
import com.stdio.hue.network.di.NetworkModule;
import com.stdio.hue.network.service.MovieService;
import com.stdio.hue.network.service.TVService;

import java.text.DateFormat;
import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vanthien113 on 10/17/18.
 */

@Module(includes = {NetworkModule.class})
@Singleton
public class DataModule {
    @Provides
    public Gson providesGson() {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, context) -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG);
        return builder.create();
    }

    @Provides
    public MovieUseCase providesMovieUseCase(Gson gson, MovieService movieService) {
        return new MovieUseCaseImpl(gson, movieService);
    }

    @Provides
    public TVShowUseCase providesTVShowUseCase(Gson gson, TVService tvService) {
        return new TVShowUseCaseImpl(gson, tvService);
    }

}
