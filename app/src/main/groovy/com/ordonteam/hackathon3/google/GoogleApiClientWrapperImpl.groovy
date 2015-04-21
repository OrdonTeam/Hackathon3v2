package com.ordonteam.hackathon3.google

import android.content.Intent
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.games.Games
import com.google.android.gms.games.multiplayer.realtime.Room
import com.google.android.gms.games.multiplayer.realtime.RoomConfig
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
import groovy.transform.CompileStatic

@CompileStatic
class GoogleApiClientWrapperImpl implements GoogleApiClientWrapper {
    GoogleApiClient client

    GoogleApiClientWrapperImpl(GoogleApiClient client) {
        this.client = client
    }

    boolean isConnected() {
        client.isConnected()
    }

    boolean isConnecting() {
        client.isConnecting()
    }

    void connect() {
        client.connect()
    }

    void disconnect() {
        client.disconnect()
    }

    void createRoom(RoomConfig roomConfig) {
        Games.RealTimeMultiplayer.create(client, roomConfig)
    }

    Intent getWaitingRoomIntent(Room room) {
        return Games.RealTimeMultiplayer.getWaitingRoomIntent(client, room, Integer.MAX_VALUE)
    }

    int sendUnreliableMessageToOthers(byte[] bytes, String roomId) {
        return Games.RealTimeMultiplayer.sendUnreliableMessageToOthers(client, bytes, roomId)
    }

    String getCurrentPlayerId() {
        return Games.Players.getCurrentPlayerId(client)
    }

    void leave(RoomUpdateListener listener, String roomId) {
        Games.RealTimeMultiplayer.leave(client, listener, roomId)
    }
}
