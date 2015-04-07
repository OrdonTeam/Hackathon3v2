package com.ordonteam.hackathon3.model.common

import com.ordonteam.hackathon3.model.board.Board
import groovy.transform.CompileStatic

@CompileStatic
abstract class UnmovableGameObject extends BaseGameObject {
    UnmovableGameObject(Dimension location) {
        super(location)
    }

    @Override
    final MoveDirection move(Board board, GameObjects gameObjects) {
        return MoveDirection.NOWHERE
    }

    @Override
    final BaseGameObject withNewLocation(MoveDirection moveDirection) {
        if (moveDirection != MoveDirection.NOWHERE)
            throw new UnmovableGameObjectException()
        return this
    }

    static class UnmovableGameObjectException extends RuntimeException {
        UnmovableGameObjectException() {
            super('UnmovableGameObject cannot be moved!')
        }
    }
}
