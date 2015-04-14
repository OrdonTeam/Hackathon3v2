package com.ordonteam.hackathon3.controller

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.MultipleGameObjects
import groovy.transform.CompileStatic

@CompileStatic
interface GameObjectsConsumer {

    void newObjects(String participantId, MultipleGameObjects gameObjects)

    void newBoard(Board board)

}
