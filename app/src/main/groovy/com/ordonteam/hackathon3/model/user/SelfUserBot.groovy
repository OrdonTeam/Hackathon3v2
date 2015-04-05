package com.ordonteam.hackathon3.model.user

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.BaseGameObject
import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.model.common.GameObjects
import com.ordonteam.hackathon3.model.common.MoveDirection
import com.ordonteam.hackathon3.view.PlayerPadView
import groovy.transform.CompileStatic

@CompileStatic
class SelfUserBot extends UserBot{

    private transient PlayerPadView playerPadView

    SelfUserBot(Dimension location, PlayerPadView playerPadView) {
        super(location)
        this.playerPadView = playerPadView
    }

    @Override
    MoveDirection move(Board board, GameObjects gameObjects) {
        return playerPadView.getCurrentInclination()
    }

    @Override
    BaseGameObject withNewLocation(MoveDirection moveDirection) {
        return new SelfUserBot(location.to(moveDirection), playerPadView)
    }
}
