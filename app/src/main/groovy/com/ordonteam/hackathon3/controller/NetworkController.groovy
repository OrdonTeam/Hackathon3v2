package com.ordonteam.hackathon3.controller

import com.ordonteam.hackathon3.event.Bus
import com.ordonteam.hackathon3.event.SendBytesEvent
import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.MultipleGameObjects
import groovy.transform.CompileStatic

@CompileStatic
class NetworkController {

    void newObjects(MultipleGameObjects gameObjects) {
        Bus.informPassengers(new SendBytesEvent(persist(gameObjects)))
    }

    void newBoard(Board board) {
        Bus.informPassengers(new SendBytesEvent(persist(board)))
    }

    static byte[] persist(Object objectToPersist) {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream()
        byteOutputStream.withObjectOutputStream { ObjectOutputStream stream ->
            stream.writeObject(objectToPersist)
        }
        return byteOutputStream.toByteArray()
    }
}
