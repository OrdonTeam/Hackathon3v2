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
        dispatcher = new GameObjectsDispatcher(gameViewController,this)
    }

    @Override
    void onP2PConnected(String s) {
        super.onP2PConnected(s)
        def board = BoardGenerator.forDimension(Dimension.xy(BOARD_SIZE, BOARD_SIZE))
        dispatcher.fromGameController(board)
    }

    @Override
    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        Log.e('onRealTimeMessageReceived', "${realTimeMessage.messageData.length}")
        dispatcher.fromNetwork(realTimeMessage.senderParticipantId, realTimeMessage.messageData)
    }

    @Override
    void startGame(String myParticipantId) {
        Log.e('StartGame invoked',"myParticipantId: ${myParticipantId}")
    }
}
