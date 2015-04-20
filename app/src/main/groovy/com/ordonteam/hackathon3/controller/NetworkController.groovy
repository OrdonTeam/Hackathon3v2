package com.ordonteam.hackathon3.controller

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.common.MultipleGameObjects
import groovy.transform.CompileStatic

@CompileStatic
class NetworkController {

    MessageSender sender

    NetworkController(MessageSender sender) {
        this.sender = sender
    }

    void newObjects(MultipleGameObjects gameObjects) {
        sender.sendUnreliableMessageToOthers(persist(gameObjects))
    }

    void newBoard(Board board) {
        sender.sendUnreliableMessageToOthers(persist(board))
    }

    static byte[] persist(Object objectToPersist) {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream()
        byteOutputStream.withObjectOutputStream { ObjectOutputStream stream ->
            stream.writeObject(objectToPersist)
        }
        return byteOutputStream.toByteArray()
    }
}
