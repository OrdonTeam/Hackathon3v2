package com.ordonteam.hackathon3.controller

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.GameObjects
import groovy.transform.CompileStatic

@CompileStatic
interface GameObjectsConsumer {

    void newObjects(String participantId, GameObjects gameObjects)

    void newBoard(Board board)

}
