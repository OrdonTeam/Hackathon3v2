package com.ordonteam

import com.ordonteam.hackathon3.model.common.MoveDirection
import spock.lang.Specification

import static com.ordonteam.hackathon3.model.common.MoveDirection.*

class MoveDirectionSpec extends Specification {

    def "Inverted move should return proper direction"(){
        when:
        MoveDirection result = move.invert()

        then:
            result == invertedMove

        where:
        move       |   invertedMove
        UP         |   DOWN
        LEFT       |   RIGHT
        DOWN       |   UP
        RIGHT      |   LEFT
        DOWN_LEFT  |   UP_RIGHT
        UP_RIGHT   |   DOWN_LEFT
        DOWN_RIGHT |   UP_LEFT
        UP_LEFT    |   DOWN_RIGHT
        NOWHERE    |   NOWHERE
    }

}
