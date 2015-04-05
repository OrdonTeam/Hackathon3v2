package com.ordonteam.hackathon3.model.user

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.BaseGameObject
import com.ordonteam.hackathon3.model.common.GameObjects
import com.ordonteam.hackathon3.model.common.MoveDirection
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class OtherUserBot extends UserBot implements Serializable{
    static final long serialVersionUID = 42L

    @Override
    MoveDirection move(Board board, GameObjects gameObjects) {
        return MoveDirection.NOWHERE
    }

    @Override
    BaseGameObject withNewLocation(MoveDirection moveDirection) {
        return new OtherUserBot(location.to(moveDirection))
    }
}
