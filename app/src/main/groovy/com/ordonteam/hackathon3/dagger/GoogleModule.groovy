package com.ordonteam.hackathon3.dagger;

import com.ordonteam.hackathon3.GameActivity;
import com.ordonteam.hackathon3.google.GoogleApiClientWrapperProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        injects = [
                GameActivity,
        ],
        library = true
)
@SuppressWarnings("unused")
public final class GoogleModule {

    @Provides
    @Singleton
    GoogleApiClientWrapperProvider provideDoctorsService() {
        return new GoogleApiClientWrapperProvider();
    }
}
