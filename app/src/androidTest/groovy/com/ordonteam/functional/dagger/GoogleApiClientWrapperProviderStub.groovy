package com.ordonteam.functional.dagger

import com.ordonteam.hackathon3.LoginActivity
import com.ordonteam.hackathon3.google.GoogleApiClientWrapper
import com.ordonteam.hackathon3.google.GoogleApiClientWrapperProvider

public class GoogleApiClientWrapperProviderStub extends GoogleApiClientWrapperProvider {
    @Override
    public GoogleApiClientWrapper provide(LoginActivity loginActivity) {
        return new GoogleApiClientWrapperStub(loginActivity);
    }
}
