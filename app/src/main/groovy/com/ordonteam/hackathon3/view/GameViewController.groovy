package com.ordonteam.hackathon3.view

import android.graphics.Canvas
import android.util.Log
import android.widget.LinearLayout
import com.ordonteam.hackathon3.controller.GameObjectsConsumer
import com.ordonteam.hackathon3.model.Board
import com.ordonteam.hackathon3.model.GameObjects
import com.ordonteam.hackathon3.view.common.Scale
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import java.util.concurrent.ConcurrentHashMap

@CompileStatic
@InheritConstructors
class GameViewController extends LinearLayout implements GameObjectsConsumer {
    private Map<String, GameObjects> gameObjectsMap = new ConcurrentHashMap<>()
    Board board

    @Override
    void newBoard(Board board) {
        this.board = board
        postInvalidate()
    }

    void newObjects(String participantId, GameObjects gameObjects) {
        gameObjectsMap.put(participantId, gameObjects)
        postInvalidate()
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas)
        ScaledCanvas scaledCanvas = new ScaledCanvas(canvas, new Scale(20, 20))
        board?.draw(scaledCanvas)
        Log.e('Drawing board',"with hackCode = ${board?.hashCode}")
        gameObjectsMap.values().each {
            it.drawAll(scaledCanvas)
        }
    }
}
