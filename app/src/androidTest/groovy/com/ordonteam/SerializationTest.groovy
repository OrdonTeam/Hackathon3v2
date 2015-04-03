package com.ordonteam

import com.google.android.gms.games.multiplayer.Multiplayer
import com.ordonteam.hackathon3.model.Dimension
import com.ordonteam.hackathon3.model.Wall
import spock.lang.Specification

import static com.ordonteam.hackathon3.model.Board.generateBoard

class SerializationTest extends Specification{

    def "Sending object should not be bigger than 1168 bytes"() {
        given:
        def board = generateBoard(3)

        when:
        byte[] bytes = persist(board)

        then:
        bytes.length <= Multiplayer.MAX_UNRELIABLE_MESSAGE_LEN
    }

    def "Paint should be serializable"(){
        Wall wall = new Wall(new Dimension(1, 1))
        Wall unpersistedWall

        when:
        new ByteArrayInputStream(persist(wall)).withObjectInputStream { ObjectInputStream stream ->
            unpersistedWall = stream.readObject() as Wall
        }

        then:
        unpersistedWall == wall

    }

    static byte[] persist(Object objectToPersist) {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        byteOutputStream.withObjectOutputStream { ObjectOutputStream stream ->
            stream.writeObject(objectToPersist)
        }
        return byteOutputStream.toByteArray()
    }

}
