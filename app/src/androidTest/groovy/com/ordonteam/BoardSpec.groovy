package com.ordonteam

import com.ordonteam.hackathon3.model.Board
import com.ordonteam.hackathon3.model.Dimension
import junit.framework.TestCase
import spock.lang.Specification

class BoardSpec extends Specification{

    def "All players should have the same board on startup"(){
        given:
        def b1 = Board.generateBoard(new Dimension(100, 100))
        def b2 = Board.generateBoard(new Dimension(100, 100))


        expect:
        Board.chooseBoard(b1, b2) == Board.chooseBoard(b2, b1)
    }
}
