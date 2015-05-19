package com.ordonteam.functional.dagger

import com.ordonteam.hackathon3.LoginActivity
import com.ordonteam.hackathon3.google.GoogleApiClientWrapper
import com.ordonteam.hackathon3.google.GoogleApiClientWrapperProvider

class GoogleApiClientWrapperProviderStub extends GoogleApiClientWrapperProvider {
    @Override
    GoogleApiClientWrapper provide(LoginActivity loginActivity) {
        return new GoogleApiClientWrapperStub(loginActivity)
    }
}
