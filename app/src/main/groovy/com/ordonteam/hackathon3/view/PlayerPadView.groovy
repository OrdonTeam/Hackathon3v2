package com.ordonteam.hackathon3.view

import android.widget.LinearLayout
import com.ordonteam.hackathon3.model.MoveDirection
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class PlayerPadView extends LinearLayout{

    MoveDirection getCurrentInclination() {
        return MoveDirection.DOWN
    }
}