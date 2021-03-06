package com.ordonteam.hackathon3.view.common

import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.view.GameDrawable
import groovy.transform.CompileStatic

@CompileStatic
interface DimensionAble extends GameDrawable {

    Dimension getDimension()
}
