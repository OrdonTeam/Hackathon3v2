package com.ordonteam.hackathon3.model.user

import com.ordonteam.hackathon3.model.common.BaseGameObject
import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.model.common.MoveDirection
import com.ordonteam.hackathon3.view.PlayerPadView
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class OtherUserBot extends UserBot {

    @Override
    MoveDirection move() {
        return MoveDirection.NOWHERE
    }

    @Override
    BaseGameObject withNewLocation(MoveDirection moveDirection) {
        return new OtherUserBot(location.to(moveDirection))
    }
}
