package com.ordonteam.hackathon3.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                GoogleModule.class,
        },
        library = true)
@SuppressWarnings("unused")
public final class AppModule {
    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    private final Context context;
}
