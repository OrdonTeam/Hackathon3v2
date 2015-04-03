package com.ordonteam.hackathon3.view.utils

import android.graphics.Paint
import groovy.transform.CompileStatic

@CompileStatic
class GamePaint {
    private static final Map<Integer, Paint> paints = new HashMap<>()

    static Paint forColor(int color) {
        if (paints.containsKey(color)) {
            return paints.get(color)
        } else {
            Paint paint = new Paint()
            paint.setColor(color)
            paints.put(color, paint)
            return paint
        }
    }
}
