package com.ordonteam.hackathon3.model.common

import com.ordonteam.hackathon3.model.board.Board
import groovy.transform.CompileStatic

@CompileStatic
abstract class UnmovableGameObject extends SingleGameObject {
    UnmovableGameObject(Dimension location) {
        super(location)
    }

    @Override
    final MoveDirection move(Board board, MultipleGameObjects gameObjects) {
        return MoveDirection.NOWHERE
    }

    @Override
    final SingleGameObject withNewLocation(MoveDirection moveDirection) {
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
