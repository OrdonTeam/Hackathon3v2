package com.ordonteam.hackathon3.view.common

import com.ordonteam.hackathon3.model.Dimension
import com.ordonteam.hackathon3.view.GameDrawable
import groovy.transform.CompileStatic

@CompileStatic
public interface DimensionAble extends GameDrawable {

    public Dimension getDimension()
}
