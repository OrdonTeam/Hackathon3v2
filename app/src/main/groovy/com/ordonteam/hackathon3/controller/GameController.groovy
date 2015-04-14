package com.ordonteam.hackathon3.controller

import android.util.Log
import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.MultipleGameObjects
import com.ordonteam.hackathon3.view.GroovyLock
import groovy.transform.CompileStatic

import java.util.concurrent.ConcurrentHashMap

import static com.ordonteam.hackathon3.utils.ThreadUtil.startInteruptableThread

@CompileStatic
class GameController implements GameObjectsConsumer {
    private MultipleGameObjects gameObjects
    private Map<String,MultipleGameObjects> otherPlayersObjects = new ConcurrentHashMap<>()
    private Thread thread
    private GameObjectsDispatcher dispatcher
    final GroovyLock lock = new GroovyLock()
    private Board board

    void setDispather(GameObjectsDispatcher dispatcher) {
        this.dispatcher = dispatcher
    }

    void moveAll() {
        lock.withLock {
            gameObjects = gameObjects.moveAll(board, otherPlayersObjects)
        }
        dispatcher.fromGameController(gameObjects)
    }

    void onResume() {
        thread = startInteruptableThread( {
            while (true) {
                moveAll()
                Log.e('move', 'all')
                Thread.sleep(1000)
            }
        })
    }

    void onPause() {
        thread.interrupt()
    }

    void newObjects(String participantId, MultipleGameObjects gameObjects) {
        lock.withLock {
            this.otherPlayersObjects.put(participantId,gameObjects)
        }
    }

    @Override
    void newBoard(Board board) {
        this.board = board
    }
}
