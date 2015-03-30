package com.ordonteam.hackathon3.controller

import com.ordonteam.hackathon3.model.Board
import com.ordonteam.hackathon3.model.GameObjects
import groovy.transform.CompileStatic

@CompileStatic
class NetworkController implements GameObjectsConsumer{

    MessageSender sender

    NetworkController(MessageSender sender) {
        this.sender = sender
    }

    @Override
    void newObjects(String participantId, GameObjects gameObjects) {
        sender.sendUnreliableMessageToOthers(persist(gameObjects))
    }

    @Override
    void newBoard(Board board) {
        sender.sendUnreliableMessageToOthers(persist(board))
    }

    static byte[] persist(Object objectToPersist) {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        byteOutputStream.withObjectOutputStream { ObjectOutputStream stream ->
            stream.writeObject(objectToPersist)
        }
        return byteOutputStream.toByteArray()
    }
}
