package com.ordonteam.hackathon3.view.utils

import android.graphics.Paint
import groovy.transform.CompileStatic

@CompileStatic
class GamePaint {
    static Paint forColor(int color) {
        Paint paint = new Paint()
        paint.setColor(color)
        return paint
    }
}
