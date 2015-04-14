package com.ordonteam.hackathon3.model.user

import android.graphics.Color
import android.graphics.Paint
import com.ordonteam.hackathon3.model.common.SingleGameObject
import com.ordonteam.hackathon3.view.utils.GamePaint
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
abstract class UserBot extends SingleGameObject implements Serializable {
    private static final long serialVersionUID = 42L

    @Override
    Paint getPaint() {
        return GamePaint.forColor(Color.CYAN)
    }
}
