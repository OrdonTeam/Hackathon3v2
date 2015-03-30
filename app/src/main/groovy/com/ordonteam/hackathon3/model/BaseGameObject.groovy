package com.ordonteam.hackathon3.model

import android.graphics.Paint
import com.ordonteam.hackathon3.view.GameDrawable
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
abstract class BaseGameObject implements GameDrawable, Serializable{
    static final long serialVersionUID = 42L

    final Dimension location

    BaseGameObject(Dimension location) {
        this.location = location
    }

    MoveDirection move() {
        return MoveDirection.NOWHERE
    }
    
    void draw(ScaledCanvas canvas) {
        canvas.drawRectangle(location,getPaint())
    }

    abstract BaseGameObject withNewLocation(MoveDirection moveDirection);

    abstract Paint getPaint()
}
