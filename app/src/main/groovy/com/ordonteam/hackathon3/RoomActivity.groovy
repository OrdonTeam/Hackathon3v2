package com.ordonteam.hackathon3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.games.Games
import com.google.android.gms.games.GamesStatusCodes
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
        Log.i("OrdonTeam","onRoomCreated roomId: ${room.getRoomId()}")
        onCreationCallback(statusCode, room)
    }

    @Override
    void onJoinedRoom(int statusCode, Room room) {
        Log.i("OrdonTeam","onJoinedRoom roomId: ${room.getRoomId()}")
        onCreationCallback(statusCode, room)
    }

    private void onCreationCallback(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            onRoomCreationFailure(statusCode)
        } else {
            roomId = room.roomId
            Log.i("OrdonTeam","onCreationCallback roomId: $roomId")
            Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(client, room, Integer.MAX_VALUE);
            startActivityForResult(i, RC_WAITING_ROOM);
        }
    }

    int sendUnreliableMessageToOthers(byte[] bytes) {
        Log.i("OrdonTeam","sendUnreliableMessageToOthers roomId: $roomId")
        Games.RealTimeMultiplayer.sendUnreliableMessageToOthers(client, bytes, roomId)
    }

    abstract void onRoomCreationFailure(int statusCode)

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == RC_WAITING_ROOM) {
            if (responseCode == RESULT_OK) {
                startGame(Games.Players.getCurrentPlayerId(client))
            } else if (responseCode in [RESULT_CANCELED, com.google.android.gms.games.GamesActivityResultCodes.RESULT_LEFT_ROOM]) {
                Games.RealTimeMultiplayer.leave(client, this, roomId); // java.lang.IllegalStateException: GoogleApiClient must be connected. when exiting from waiting room
            }
        } else {
            onActivityResult(requestCode, responseCode, data)
        }
    }

    abstract void startGame(String myParticipantId)

    @Override
    void onLeftRoom(int i, String s) {

    }

    @Override
    void onRoomConnected(int statusCode, Room room) {
        Log.i("OrdonTeam","onRoomConnected roomId: ${room.getRoomId()}")

    }


    @Override
    void onRoomConnecting(Room room) {
        Log.d("OrdonTeam", "onRoomConnecting to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onRoomAutoMatching(Room room) {
        Log.d("OrdonTeam", "onRoomAutoMatching to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onPeerInvitedToRoom(Room room, List<String> strings) {
        Log.d("OrdonTeam", "onPeerInvitedToRoom to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onPeerDeclined(Room room, List<String> strings) {
        Log.d("OrdonTeam", "onPeerDeclined to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onPeerJoined(Room room, List<String> strings) {
        Log.d("OrdonTeam", "onPeerJoined to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onPeerLeft(Room room, List<String> strings) {
        Log.d("OrdonTeam", "onPeerLeft to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onConnectedToRoom(Room room) {
        // get room ID, participants and my ID:
// print out the list of participants (for debug purposes)
        Log.d("OrdonTeam", "onConnectedToRoom to Room ID: ${room.getRoomId()}");
    }

    @Override
    void onDisconnectedFromRoom(Room room) {
        Log.d("OrdonTeam", "onDisconnectedFromRoom to Room ID: ${room.getRoomId()}");
    }

    @Override
    void onPeersConnected(Room room, List<String> strings) {
        Log.d("OrdonTeam", "onPeersConnected to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onPeersDisconnected(Room room, List<String> strings) {
        Log.d("OrdonTeam", "onPeersDisconnected to Room ID: ${room.getRoomId()}");

    }

    @Override
    void onP2PConnected(String s) {

    }

    @Override
    void onP2PDisconnected(String s) {

    }
}
