package com.ordonteam.functional.dagger

import android.content.Intent
import com.google.android.gms.games.multiplayer.realtime.Room
import com.google.android.gms.games.multiplayer.realtime.RoomConfig
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
import com.ordonteam.hackathon3.LoginActivity
import com.ordonteam.hackathon3.google.GoogleApiClientWrapper

final class GoogleApiClientWrapperStub implements GoogleApiClientWrapper {
    private LoginActivity loginActivity

    GoogleApiClientWrapperStub(LoginActivity loginActivity) {
        this.loginActivity = loginActivity
    }

    @Override
    boolean isConnected() {
        return false
    }

    @Override
    boolean isConnecting() {
        return false
    }

    @Override
    void connect() {

    }

    @Override
    void disconnect() {

    }

    @Override
    void createRoom(RoomConfig roomConfig) {

    }

    @Override
    Intent getWaitingRoomIntent(Room room) {
        return null
    }

    @Override
    int sendUnreliableMessageToOthers(byte[] bytes, String roomId) {
        return 0
    }

    @Override
    String getCurrentPlayerId() {
        return null
    }

    @Override
    void leave(RoomUpdateListener roomActivity, String roomId) {

    }
}
