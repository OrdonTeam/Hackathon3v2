package com.ordonteam.functional.dagger;

import com.ordonteam.hackathon3.google.GoogleApiClientWrapperProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes =, overrides = true, library = true)
@SuppressWarnings("unused")
public class BaseTestModule {
    @Provides
    @Singleton
    public GoogleApiClientWrapperProvider provideDoctorsService() {
        return new GoogleApiClientWrapperProviderStub();
    }

}
