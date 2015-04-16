package com.ordonteam

import com.ordonteam.hackathon3.model.common.MoveDirection
import spock.lang.Specification

import static com.ordonteam.hackathon3.model.common.MoveDirection.UP_LEFT
import static com.ordonteam.hackathon3.model.common.MoveDirection.UP_RIGHT
import static com.ordonteam.hackathon3.model.common.MoveDirection.UP
import static com.ordonteam.hackathon3.model.common.MoveDirection.DOWN
import static com.ordonteam.hackathon3.model.common.MoveDirection.DOWN_RIGHT
import static com.ordonteam.hackathon3.model.common.MoveDirection.DOWN_LEFT
import static com.ordonteam.hackathon3.model.common.MoveDirection.NOWHERE
import static com.ordonteam.hackathon3.model.common.MoveDirection.RIGHT
import static com.ordonteam.hackathon3.model.common.MoveDirection.LEFT

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
