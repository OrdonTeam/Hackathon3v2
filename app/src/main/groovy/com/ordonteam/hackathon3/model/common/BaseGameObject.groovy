package com.ordonteam.hackathon3.model.common

import android.graphics.Paint
import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.view.GameDrawable
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
abstract class BaseGameObject implements GameDrawable, Serializable {
    private static final long serialVersionUID = 42L

    final Dimension location

    BaseGameObject(Dimension location) {
        this.location = location
    }

    abstract MoveDirection move(Board board, GameObjects gameObjects)

    void draw(ScaledCanvas canvas) {
        canvas.drawRectangle(location, paint)
    }

    abstract BaseGameObject withNewLocation(MoveDirection moveDirection)

    abstract Paint getPaint()
}
