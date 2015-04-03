package com.ordonteam.hackathon3.model.board

import android.graphics.Color
import android.graphics.Paint
import com.ordonteam.hackathon3.model.common.MoveDirection
import com.ordonteam.hackathon3.model.common.BaseGameObject
import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.view.utils.GamePaint
import groovy.transform.CompileStatic

@CompileStatic
class Wall extends BaseGameObject implements Serializable {
    static final long serialVersionUID = 42L
    static final Paint paint = GamePaint.forColor(Color.GRAY)

    Wall(Dimension location) {
        super(location)
    }

    @Override
    BaseGameObject withNewLocation(MoveDirection moveDirection) {
        return new Wall(location.to(moveDirection))
    }

    @Override
    Paint getPaint() {
        return paint
    }
}
