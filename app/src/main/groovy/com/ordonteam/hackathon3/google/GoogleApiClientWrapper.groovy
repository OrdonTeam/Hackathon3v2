package com.ordonteam.hackathon3.google

import android.content.Intent
import com.google.android.gms.games.multiplayer.realtime.Room
import com.google.android.gms.games.multiplayer.realtime.RoomConfig
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
import groovy.transform.CompileStatic

@CompileStatic
interface GoogleApiClientWrapper {

    boolean isConnected()

    boolean isConnecting()

    void connect()

    void disconnect()

    void createRoom(RoomConfig roomConfig)

    Intent getWaitingRoomIntent(Room room)

    int sendUnreliableMessageToOthers(byte[] bytes, String roomId)

    String getCurrentPlayerId()

    void leave(RoomUpdateListener roomActivity, String roomId)
}