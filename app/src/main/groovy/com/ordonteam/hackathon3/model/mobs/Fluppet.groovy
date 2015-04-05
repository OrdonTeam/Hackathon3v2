package com.ordonteam.hackathon3.model.mobs

import android.graphics.Color
import android.graphics.Paint
import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.BaseGameObject
import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.model.common.GameObjects
import com.ordonteam.hackathon3.model.common.MoveDirection
import com.ordonteam.hackathon3.view.utils.GamePaint
import groovy.transform.CompileStatic

@CompileStatic
class Fluppet extends BaseGameObject implements Serializable {
    static final long serialVersionUID = 42L

    Fluppet(Dimension location) {
        super(location)
    }

    @Override
    MoveDirection move(Board board, GameObjects gameObjects) {
        board.wallsAround(location)
        gameObjects.objectsAroundLocation(location)
        return MoveDirection.RIGHT
    }

    @Override
    BaseGameObject withNewLocation(MoveDirection moveDirection) {
        return new Fluppet(location.to(moveDirection))
    }

    @Override
    Paint getPaint() {
        return GamePaint.forColor(Color.BLUE)
    }
}
