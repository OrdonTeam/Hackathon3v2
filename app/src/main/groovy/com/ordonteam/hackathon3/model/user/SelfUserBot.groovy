package com.ordonteam.hackathon3.model.user

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.model.common.MultipleGameObjects
import com.ordonteam.hackathon3.model.common.MoveDirection
import com.ordonteam.hackathon3.model.common.SingleGameObject
import com.ordonteam.hackathon3.view.PlayerPadView
import groovy.transform.CompileStatic

@CompileStatic
class SelfUserBot extends UserBot implements Serializable {
    private static final long serialVersionUID = 42L

    private final transient PlayerPadView playerPadView

    SelfUserBot(Dimension location, PlayerPadView playerPadView) {
        super(location)
        this.playerPadView = playerPadView
    }

    @Override
    MoveDirection move(Board board, MultipleGameObjects gameObjects) {
        return playerPadView.currentInclination
    }

    @Override
    SingleGameObject withNewLocation(MoveDirection moveDirection) {
        return new SelfUserBot(location.to(moveDirection), playerPadView)
    }
}
