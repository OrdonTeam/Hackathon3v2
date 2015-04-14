package com.ordonteam.hackathon3.model.user

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.MultipleGameObjects
import com.ordonteam.hackathon3.model.common.MoveDirection
import com.ordonteam.hackathon3.model.common.SingleGameObject
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class OtherUserBot extends UserBot implements Serializable {
    private static final long serialVersionUID = 42L

    @Override
    MoveDirection move(Board board, MultipleGameObjects gameObjects) {
        return MoveDirection.NOWHERE
    }

    @Override
    SingleGameObject withNewLocation(MoveDirection moveDirection) {
        return new OtherUserBot(location.to(moveDirection))
    }
}
