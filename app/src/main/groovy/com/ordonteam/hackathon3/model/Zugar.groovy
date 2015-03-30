package com.ordonteam.hackathon3.model

import android.graphics.Color
import android.graphics.Paint
import com.ordonteam.hackathon3.view.utils.GamePaint
import groovy.transform.CompileStatic

@CompileStatic
class Zugar extends BaseGameObject implements Serializable {
    static final long serialVersionUID = 42L
    static final Paint paint = GamePaint.forColor(Color.GREEN)

    Zugar(Dimension location) {
        super(location)
    }

    @Override
    BaseGameObject withNewLocation(MoveDirection moveDirection) {
        return new Zugar(location.to(moveDirection))
    }

    @Override
    Paint getPaint(){
        return paint
    }
}
