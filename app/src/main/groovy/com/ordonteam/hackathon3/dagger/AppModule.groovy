package com.ordonteam.hackathon3.dagger;

import android.content.Context
import dagger.Module;
import dagger.Provides;

@Module(
        includes = [
                GoogleModule,
        ],
        library = true
)
@SuppressWarnings("unused")
public final class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    Context provideContext() {
        return context;
    }

}