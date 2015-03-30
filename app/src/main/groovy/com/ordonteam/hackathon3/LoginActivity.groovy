package com.ordonteam.hackathon3

import android.app.Activity
import android.content.Intent
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.games.Games
import com.google.android.gms.plus.Plus
import groovy.transform.CompileStatic

@CompileStatic
abstract class LoginActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001

    GoogleApiClient client

    @Override
    protected void onStart() {
        super.onStart()
        if (!client) {
            client = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                    .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                    .build();
        }
        if (!client.isConnected() && !client.isConnecting()) {
            client.connect()
        }
    }

    @Override
    protected void onStop() {
        super.onStop()
        if (client?.isConnected())
            client.disconnect()
    }

    @Override
    void onConnectionSuspended(int i) {
        client.connect()
    }

    @Override
    void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            connectionResult.startResolutionForResult(this, RC_SIGN_IN)
        } else {
            onConnectFailed(connectionResult.errorCode)
        }
    }

    abstract void onConnectFailed(int i)

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode == RESULT_OK) {
                client.connect()
            } else {
                onNotSignedIn(responseCode)
            }
        } else {
            super.onActivityResult(requestCode, responseCode, data)
        }
    }

    abstract void onNotSignedIn(int errorCode)
}
