package com.ordonteam

import com.ordonteam.hackathon3.model.Dimension
import com.ordonteam.hackathon3.model.Wall
import spock.lang.Specification

class SerializationTest extends Specification{

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
