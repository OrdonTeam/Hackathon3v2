package com.ordonteam.hackathon3.model.common

import groovy.transform.Canonical
import groovy.transform.CompileStatic;

@CompileStatic
@Canonical
public class Dimension implements Serializable {
    static final long serialVersionUID = 42L

    final int width
    final int height

    Dimension(int width, int height) {
        //TODO: private constructor  - caching objects
        this.height = height
        this.width = width
    }

    int getX(){
        return width
    }

    int getY(){
        return height
    }

    static Dimension xy(int x, int y) {
        //TODO: Cache objects
        return new Dimension(x, y)
    }

    Dimension to(MoveDirection moveDirection) {
        return new Dimension(width+moveDirection.x,height+moveDirection.y)
    }
}
