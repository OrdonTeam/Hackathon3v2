package com.ordonteam.hackathon3.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.ordonteam.hackathon3.controller.GameObjectsConsumer
import com.ordonteam.hackathon3.controller.PlayerIdentifier
import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.MultipleGameObjects
import com.ordonteam.hackathon3.view.common.Scale
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.CompileStatic

import java.util.concurrent.ConcurrentHashMap

@CompileStatic
class GameViewController extends LinearLayout implements GameObjectsConsumer {
    private Map<PlayerIdentifier, MultipleGameObjects> gameObjectsMap = new ConcurrentHashMap<>()
    Board board

    GameViewController(Context context) {
        super(context)
    }

    GameViewController(Context context, AttributeSet attrs) {
        super(context, attrs)
    }

    GameViewController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr)
    }

    GameViewController(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes)
    }

    @Override
    void newBoard(Board board) {
        this.board = board
        postInvalidate()
    }

    void newObjects(PlayerIdentifier participantId, MultipleGameObjects gameObjects) {
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
