package com.ordonteam.hackathon3.controller

import android.util.Log
import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.board.BoardChooser
import com.ordonteam.hackathon3.model.common.GameObjects
import com.ordonteam.hackathon3.view.GameViewController
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

@CompileStatic
class GameObjectsDispatcher {

    GameObjectsConsumer gameController = new GameController()
    GameObjectsConsumer gameViewController
    GameObjectsConsumer networkController
    Board board // Can be null but who cares
    final String myParticipantId

    GameObjectsDispatcher(String myParticipantId, GameViewController gameViewController, MessageSender sender) {
        this.myParticipantId = myParticipantId
        this.gameViewController = gameViewController
        this.networkController = new NetworkController(sender)
    }

    void fromGameController(Board newBoard) {
        board = new BoardChooser().chooseBoard(board, newBoard)
        gameViewController.newBoard(board)
        networkController.newBoard(board)
    }

    void fromGameController(GameObjects gameObjects) {
        gameViewController.newObjects(myParticipantId, gameObjects)
        networkController.newObjects(myParticipantId, gameObjects)
    }

    @CompileDynamic
    void fromNetwork(String participantId, byte[] bytes) {
        new ByteArrayInputStream(bytes).withObjectInputStream { ObjectInputStream stream ->
            def object = stream.readObject()
            fromNetwork(participantId, object)
        }
    }

    void fromNetwork(String participantId, Board newBoard) {
        Log.e('onRealTimeMessageReceived ->board', 'new Board')
        board = new BoardChooser().chooseBoard(board, newBoard)
        gameController.newBoard(board)
        gameViewController.newBoard(board)
    }

    void fromNetwork(String participantId, GameObjects gameObjects) {
        Log.e('onRealTimeMessageReceived ->gameObjects', 'new GameObjects')
        gameController.newObjects(participantId, gameObjects)
        gameViewController.newObjects(participantId, gameObjects)
    }
}
