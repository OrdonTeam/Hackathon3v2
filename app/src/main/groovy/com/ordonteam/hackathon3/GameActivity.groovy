package com.ordonteam.hackathon3

import android.os.Bundle
import android.util.Log
import com.arasthel.swissknife.SwissKnife
import com.arasthel.swissknife.annotations.InjectView
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage
import com.ordonteam.hackathon3.controller.GameObjectsDispatcher
import com.ordonteam.hackathon3.model.Board
import com.ordonteam.hackathon3.view.GameViewController
import com.ordonteam.hackathon3.view.PlayerPadView
import groovy.transform.CompileStatic

@CompileStatic
class GameActivity extends RoomActivity {

    @InjectView(R.id.game_view)
    GameViewController gameViewController
    @InjectView(R.id.player_pad_view)
    PlayerPadView playerPadView
    private GameObjectsDispatcher dispatcher

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        SwissKnife.inject(this)
    }

    @Override
    void startGame(String myParticipantId) {
        dispatcher = new GameObjectsDispatcher(myParticipantId, gameViewController,this)
        def board = Board.generateBoard(3)
        dispatcher.fromGameController(board)
    }

    @Override
    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        dispatcher.fromNetwork(realTimeMessage.senderParticipantId,realTimeMessage.messageData)
    }

    @Override
    void onRoomCreationFailure(int statusCode) {
        Log.e("OrdonTeam", "onRoomCreationFailure")
        finish()
    }

    @Override
    void onConnectFailed(int i) {
        Log.e("OrdonTeam", "OnConnectFailed")
        finish()
    }

    @Override
    void onNotSignedIn(int errorCode) {
        Log.e("OrdonTeam", "onNotSignedIn")
        finish()
    }
}