package com.ordonteam.hackathon3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.games.Games
import com.google.android.gms.games.GamesActivityResultCodes
import com.google.android.gms.games.GamesStatusCodes
import com.google.android.gms.games.multiplayer.Multiplayer
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer
import com.google.android.gms.games.multiplayer.realtime.Room
import com.google.android.gms.games.multiplayer.realtime.RoomConfig
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
import groovy.transform.CompileStatic

@CompileStatic
abstract class RoomActivity extends LoginActivity implements RoomUpdateListener, RealTimeMessageReceivedListener, RoomStatusUpdateListener {

    public static final int RC_WAITING_ROOM = 9007
    public static final String ORDONTEAM_TAG = 'OrdonTeam'
    protected String roomId

    @Override
    void onConnected(Bundle bundle) {
        Bundle am = RoomConfig.createAutoMatchCriteria(1, 1, 0)
        RoomConfig roomConfig = RoomConfig.builder(this).setMessageReceivedListener(this).setRoomStatusUpdateListener(this).setAutoMatchCriteria(am).build()
        googleApiClientWrapper.createRoom(roomConfig)
    }

    @Override
    void onRoomCreated(int statusCode, Room room) {
        Log.i(ORDONTEAM_TAG, 'onRoomCreated')
        onCreationCallback(statusCode, room)
    }

    @Override
    void onJoinedRoom(int statusCode, Room room) {
        Log.i(ORDONTEAM_TAG, 'onJoinedRoom')
        onCreationCallback(statusCode, room)
    }

    private void onCreationCallback(int statusCode, Room room) {
        Log.i(ORDONTEAM_TAG, 'onCreationCallback')
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            onRoomCreationFailure(statusCode)
        } else {
            roomId = room.roomId
            Intent i = googleApiClientWrapper.getWaitingRoomIntent(room)
            startActivityForResult(i, RC_WAITING_ROOM)
        }
    }

    void sendUnreliableMessageToOthers(byte[] bytes) {
        Log.i(ORDONTEAM_TAG, "sendUnreliableMessageToOthers with ${bytes.length} bytes")
        if (bytes.length > Multiplayer.MAX_UNRELIABLE_MESSAGE_LEN)
            throw new RuntimeException('Message to long to send')
        int result = googleApiClientWrapper.sendUnreliableMessageToOthers(bytes, roomId)
        if (result == RealTimeMultiplayer.REAL_TIME_MESSAGE_FAILED)
            throw new RuntimeException('Error while sending message')
        Log.i(ORDONTEAM_TAG, 'sendUnreliableMessageToOthers result OK')
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == RC_WAITING_ROOM) {
            if (responseCode == RESULT_OK) {
                startGame(googleApiClientWrapper.currentPlayerId)
            } else if (responseCode in [RESULT_CANCELED, GamesActivityResultCodes.RESULT_LEFT_ROOM]) {
                googleApiClientWrapper.leave(this,roomId)
                // java.lang.IllegalStateException: GoogleApiClient must be connected. when exiting from waiting room
            }
        } else {
            onActivityResult(requestCode, responseCode, data)
        }
    }

    abstract void startGame(String myParticipantId)

    void onRoomCreationFailure(int statusCode) {
        Log.e(ORDONTEAM_TAG, "onRoomCreationFailure $statusCode")
        finish()
    }

    @Override
    void onConnectFailed(int i) {
        Log.e(ORDONTEAM_TAG, "OnConnectFailed $i")
        finish()
    }

    @Override
    void onNotSignedIn(int errorCode) {
        Log.e(ORDONTEAM_TAG, "onNotSignedIn $errorCode")
        finish()
    }

    @Override
    void onLeftRoom(int i, String s) {
        Log.i(ORDONTEAM_TAG, "onLeftRoom $i $s")
    }

    @Override
    void onRoomConnected(int statusCode, Room room) {
        Log.i(ORDONTEAM_TAG, "onRoomConnected $statusCode $room")
    }

    @Override
    void onRoomConnecting(Room room) {
        Log.d(ORDONTEAM_TAG, "onRoomConnecting $room")
    }

    @Override
    void onRoomAutoMatching(Room room) {
        Log.d(ORDONTEAM_TAG, "onRoomAutoMatching $room")
    }

    @Override
    void onPeerInvitedToRoom(Room room, List<String> strings) {
        Log.d(ORDONTEAM_TAG, "onPeerInvitedToRoom $room $strings")
    }

    @Override
    void onPeerDeclined(Room room, List<String> strings) {
        Log.d(ORDONTEAM_TAG, "onPeerDeclined $room $strings")
    }

    @Override
    void onPeerJoined(Room room, List<String> strings) {
        Log.d(ORDONTEAM_TAG, "onPeerJoined $room $strings")
    }

    @Override
    void onPeerLeft(Room room, List<String> strings) {
       Log.d(ORDONTEAM_TAG, "onPeerLeft $room $strings")
    }

    @Override
    void onConnectedToRoom(Room room) {
        Log.d(ORDONTEAM_TAG, "onConnectedToRoom $room")
    }

    @Override
    void onDisconnectedFromRoom(Room room) {
        Log.d(ORDONTEAM_TAG, "onDisconnectedFromRoom $room")
    }

    @Override
    void onPeersConnected(Room room, List<String> strings) {
        Log.d(ORDONTEAM_TAG, "onPeersConnected $room $strings")
    }

    @Override
    void onPeersDisconnected(Room room, List<String> strings) {
        Log.d(ORDONTEAM_TAG, "onPeersDisconnected $room $strings")
    }

    @Override
    void onP2PConnected(String s) {
        Log.d(ORDONTEAM_TAG, "onP2PConnected $s")
    }

    @Override
    void onP2PDisconnected(String s) {
        Log.d(ORDONTEAM_TAG, "onP2PDisconnected $s")
    }
}
