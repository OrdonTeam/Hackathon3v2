package com.ordonteam.hackathon3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.games.Games
import com.google.android.gms.games.GamesActivityResultCodes
import com.google.android.gms.games.GamesStatusCodes
import com.google.android.gms.games.multiplayer.Multiplayer
import com.google.android.gms.games.multiplayer.realtime.*
import com.ordonteam.hackathon3.controller.MessageSender
import groovy.transform.CompileStatic

@CompileStatic
abstract class RoomActivity extends LoginActivity implements RoomUpdateListener, RealTimeMessageReceivedListener, MessageSender, RoomStatusUpdateListener {

    public static final int RC_WAITING_ROOM = 9007
    protected String roomId

    @Override
    void onConnected(Bundle bundle) {
        Bundle am = RoomConfig.createAutoMatchCriteria(1, 1, 0);
        RoomConfig roomConfig = RoomConfig.builder(this).setMessageReceivedListener(this).setRoomStatusUpdateListener(this).setAutoMatchCriteria(am).build();
        Games.RealTimeMultiplayer.create(client, roomConfig);
    }

    @Override
    void onRoomCreated(int statusCode, Room room) {
        Log.i("OrdonTeam", "onRoomCreated")
        onCreationCallback(statusCode, room)
    }

    @Override
    void onJoinedRoom(int statusCode, Room room) {
        Log.i("OrdonTeam", "onJoinedRoom")
        onCreationCallback(statusCode, room)
    }

    private void onCreationCallback(int statusCode, Room room) {
        Log.i("OrdonTeam", "onCreationCallback")
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            onRoomCreationFailure(statusCode)
        } else {
            roomId = room.roomId
            Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(client, room, Integer.MAX_VALUE);
            startActivityForResult(i, RC_WAITING_ROOM);
        }
    }

    void sendUnreliableMessageToOthers(byte[] bytes) {
        Log.i("OrdonTeam", "sendUnreliableMessageToOthers with ${bytes.length} bytes")
        if (bytes.length > Multiplayer.MAX_UNRELIABLE_MESSAGE_LEN)
            throw new RuntimeException("Message to long to send")
        int result = Games.RealTimeMultiplayer.sendUnreliableMessageToOthers(client, bytes, roomId)
        if (result == RealTimeMultiplayer.REAL_TIME_MESSAGE_FAILED)
            throw new RuntimeException("Error while sending message")
        Log.i("OrdonTeam", "sendUnreliableMessageToOthers result OK")
    }

    abstract void onRoomCreationFailure(int statusCode)

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == RC_WAITING_ROOM) {
            if (responseCode == RESULT_OK) {
                startGame(Games.Players.getCurrentPlayerId(client))
            } else if (responseCode in [RESULT_CANCELED, GamesActivityResultCodes.RESULT_LEFT_ROOM]) {
                Games.RealTimeMultiplayer.leave(client, this, roomId);
                // java.lang.IllegalStateException: GoogleApiClient must be connected. when exiting from waiting room
            }
        } else {
            onActivityResult(requestCode, responseCode, data)
        }
    }

    abstract void startGame(String myParticipantId)

    @Override
    void onLeftRoom(int i, String s) {
        Log.i("OrdonTeam", "onLeftRoom")
    }

    @Override
    void onRoomConnected(int statusCode, Room room) {
        Log.i("OrdonTeam", "onRoomConnected")
    }


    @Override
    void onRoomConnecting(Room room) {
        Log.d("RoomActivity", "onRoomConnecting");
    }

    @Override
    void onRoomAutoMatching(Room room) {
        Log.d("RoomActivity", "onRoomAutoMatching");
    }

    @Override
    void onPeerInvitedToRoom(Room room, List<String> strings) {
        Log.d("RoomActivity", "onPeerInvitedToRoom");
    }

    @Override
    void onPeerDeclined(Room room, List<String> strings) {
        Log.d("RoomActivity", "onPeerDeclined");
    }

    @Override
    void onPeerJoined(Room room, List<String> strings) {
        Log.d("RoomActivity", "onPeerJoined");
    }

    @Override
    void onPeerLeft(Room room, List<String> strings) {
        Log.d("RoomActivity", "onPeerLeft");
    }

    @Override
    void onConnectedToRoom(Room room) {
        Log.d("RoomActivity", "onConnectedToRoom");
    }

    @Override
    void onDisconnectedFromRoom(Room room) {
        Log.d("RoomActivity", "onDisconnectedFromRoom");
    }

    @Override
    void onPeersConnected(Room room, List<String> strings) {
        Log.d("RoomActivity", "onPeersConnected");
    }

    @Override
    void onPeersDisconnected(Room room, List<String> strings) {
        Log.d("RoomActivity", "onPeersDisconnected");
    }

    @Override
    void onP2PConnected(String s) {
        Log.d("RoomActivity", "onP2PConnected");
    }

    @Override
    void onP2PDisconnected(String s) {
        Log.d("RoomActivity", "onP2PDisconnected");
    }
}
