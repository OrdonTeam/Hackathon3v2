package com.ordonteam.hackathon3.view.common

import com.ordonteam.hackathon3.model.Dimension
import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class Scale {

    final float x
    final float y

    Scale(float x, float y) {
        this.x = x
        this.y = y
    }

    Scale(Dimension screenDimension, Dimension drawableDimension){
        x = drawableDimension.width / screenDimension.width
        y = drawableDimension.height / screenDimension.height
    }

    float scaleAsX(float x){
        return this.x * x
    }

    float scaleAsY(float y){
        return this.y * y
    }

}
