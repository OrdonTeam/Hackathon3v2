package com.ordonteam

import com.google.android.gms.games.multiplayer.Multiplayer
import com.ordonteam.hackathon3.model.board.BoardGenerator
import com.ordonteam.hackathon3.model.board.Wall
import com.ordonteam.hackathon3.model.common.Dimension
import spock.lang.Specification

class SerializationTest extends Specification {

    def "Sending object should not be bigger than 1168 bytes"() {
        given:
        def board = new BoardGenerator().generateBoard(Dimension.xy(9, 9))

        when:
        byte[] bytes = persist(board)

        then:
        bytes.length <= Multiplayer.MAX_UNRELIABLE_MESSAGE_LEN
    }

    def "Paint should be serializable"() {
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
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream()
        byteOutputStream.withObjectOutputStream { ObjectOutputStream stream ->
            stream.writeObject(objectToPersist)
        }
        return byteOutputStream.toByteArray()
    }

}
