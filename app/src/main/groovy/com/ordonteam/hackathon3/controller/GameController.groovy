package com.ordonteam.hackathon3.controller

import android.util.Log
import com.ordonteam.hackathon3.model.Board
import com.ordonteam.hackathon3.model.GameObjects
import com.ordonteam.hackathon3.view.GroovyLock
import groovy.transform.CompileStatic

import static com.ordonteam.hackathon3.utils.ThreadUtil.startInteruptableThread

@CompileStatic
class GameController implements GameObjectsConsumer{
    GameObjects gameObjects
    private Thread thread
    private GameObjectsDispatcher dispatcher
    final GroovyLock lock = new GroovyLock()
    Board board

    void setDispather(GameObjectsDispatcher dispatcher){
        this.dispatcher = dispatcher
    }

    void moveAll() {
        lock.withLock {
            gameObjects = gameObjects.moveAll()
        }
        dispatcher.fromGameController(gameObjects)
    }

    void onResume() {
        thread = startInteruptableThread({
            while (true) {
                moveAll()
                Log.e("move", "all")
                Thread.sleep(1000)
            }
        })
    }

    void onPause() {
        thread.interrupt()
    }

    void newObjects(String participantId, GameObjects gameObjects){
        lock.withLock {
            this.gameObjects = gameObjects
        }
    }

    @Override
    void newBoard(Board board) {
        this.board = board
    }
}
