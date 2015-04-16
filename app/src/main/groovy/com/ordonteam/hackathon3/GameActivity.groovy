package com.ordonteam.hackathon3

import android.os.Bundle
import android.util.Log
import com.arasthel.swissknife.SwissKnife
import com.arasthel.swissknife.annotations.InjectView
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage
import com.ordonteam.hackathon3.controller.GameObjectsDispatcher
import com.ordonteam.hackathon3.model.board.BoardGenerator
import com.ordonteam.hackathon3.model.common.Dimension
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

    private static final int BOARD_SIZE = 9

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        SwissKnife.inject(this)
    }

    @Override
    void startGame(String myParticipantId) {
        dispatcher = new GameObjectsDispatcher(myParticipantId, gameViewController,this)
        def board = new BoardGenerator().generateBoard(Dimension.xy(BOARD_SIZE, BOARD_SIZE))
        Thread.sleep(1000) //I observed that sometimes player received a message before he invoke startGame metod
        // so we need to wait for all, I wonder if we should send some kind of 'ready' message to inform others that all players are ready,
        // because this one second can be not enough
        dispatcher.fromGameController(board)
    }

    @Override
    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        Log.e('onRealTimeMessageReceived', "${realTimeMessage.messageData.length}")
        dispatcher.fromNetwork(realTimeMessage.senderParticipantId, realTimeMessage.messageData)
    }
}
