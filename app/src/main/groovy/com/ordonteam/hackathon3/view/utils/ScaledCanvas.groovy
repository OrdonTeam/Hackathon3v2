package com.ordonteam.hackathon3.view.utils

import android.graphics.Canvas
import android.graphics.Paint
import com.ordonteam.hackathon3.model.Dimension
import com.ordonteam.hackathon3.view.common.Scale
import groovy.transform.CompileStatic

@CompileStatic
class ScaledCanvas {

    Scale scale
    Canvas canvas

    ScaledCanvas(Canvas canvas, Scale scale) {
        this.canvas = canvas
        this.scale = scale
    }

    void drawRectangle(Dimension dimension, Paint paint) {
        canvas.drawRect((float) (dimension.width * scale.x),
                (float) (dimension.height * scale.y),
                (float) ((dimension.width + 1) * scale.x),
                (float) ((dimension.height + 1) * scale.y), paint);
    }
}
