package com.ordonteam.hackathon3.google

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.games.Games
import com.google.android.gms.plus.Plus
import com.ordonteam.hackathon3.LoginActivity
import groovy.transform.CompileStatic

@CompileStatic
class GoogleApiClientWrapperProvider {
    GoogleApiClientWrapper provide(LoginActivity loginActivity) {
        return new GoogleApiClientWrapper(new GoogleApiClient.Builder(loginActivity)
                .addConnectionCallbacks(loginActivity)
                .addOnConnectionFailedListener(loginActivity)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build())
    }
}
