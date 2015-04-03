package com.ordonteam.hackathon3.model.mobs

import android.graphics.Color
import android.graphics.Paint
import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.model.common.UnmovableGameObject
import com.ordonteam.hackathon3.view.utils.GamePaint
import groovy.transform.CompileStatic

@CompileStatic
class Toxifera extends UnmovableGameObject implements Serializable {
    static final long serialVersionUID = 42L

    Toxifera(Dimension location) {
        super(location)
    }

    @Override
    Paint getPaint() {
        return GamePaint.forColor(Color.YELLOW)
    }
}
