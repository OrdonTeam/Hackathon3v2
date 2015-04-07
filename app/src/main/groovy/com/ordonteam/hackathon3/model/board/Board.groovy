package com.ordonteam.hackathon3.model.board

import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.view.GameDrawable
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.CompileStatic

@CompileStatic
class Board implements GameDrawable, Serializable {
    private static final long serialVersionUID = 42L

    long hashCode
    Dimension size
    List<Wall> walls = new ArrayList<>()

    @Override
    void draw(ScaledCanvas canvas) {
        walls.each {
            it.draw(canvas)
        }
    }

    List<Dimension> wallsAround(Dimension dimension) {
        //TODO: This location should be relative
        return walls*.location
    }
}
