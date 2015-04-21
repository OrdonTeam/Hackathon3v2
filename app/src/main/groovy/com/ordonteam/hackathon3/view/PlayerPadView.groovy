package com.ordonteam.hackathon3.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ordonteam.hackathon3.model.common.MoveDirection
import groovy.transform.CompileStatic

@CompileStatic
class PlayerPadView extends LinearLayout {

    PlayerPadView(Context context) {
        super(context)
    }

    PlayerPadView(Context context, AttributeSet attrs) {
        super(context, attrs)
    }

    PlayerPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr)
    }

    PlayerPadView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes)
    }

    MoveDirection getCurrentInclination() {
        return MoveDirection.DOWN
    }
}
