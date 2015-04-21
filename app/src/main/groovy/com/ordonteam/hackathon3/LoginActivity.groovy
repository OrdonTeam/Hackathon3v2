package com.ordonteam.hackathon3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.ordonteam.hackathon3.dagger.Injector
import com.ordonteam.hackathon3.google.GoogleApiClientWrapper
import com.ordonteam.hackathon3.google.GoogleApiClientWrapperProvider
import groovy.transform.CompileStatic

import javax.inject.Inject

@CompileStatic
abstract class LoginActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001

    @Inject
    protected GoogleApiClientWrapperProvider googleApiClientWrapperProvider
    GoogleApiClientWrapper googleApiClientWrapper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        Injector.inject(this, this)
    }

    @Override
    protected void onStart() {
        super.onStart()
        if(!googleApiClientWrapper){
            googleApiClientWrapper = googleApiClientWrapperProvider.provide(this)
        }
        if (!googleApiClientWrapper.isConnected() && !googleApiClientWrapper.isConnecting()) {
            googleApiClientWrapper.connect()
        }
    }

    @Override
    protected void onStop() {
        super.onStop()
        if (googleApiClientWrapper?.isConnected())
            googleApiClientWrapper.disconnect()
    }

    @Override
    void onConnectionSuspended(int i) {
        googleApiClientWrapper.connect()
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
                googleApiClientWrapper.connect()
            } else {
                onNotSignedIn(responseCode)
            }
        } else {
            super.onActivityResult(requestCode, responseCode, data)
        }
    }

    abstract void onNotSignedIn(int errorCode)
}
