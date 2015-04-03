package com.ordonteam

import com.ordonteam.hackathon3.model.board.BoardChooser
import com.ordonteam.hackathon3.model.board.BoardGenerator
import com.ordonteam.hackathon3.model.common.Dimension
import spock.lang.Specification

class BoardSpec extends Specification{

    def "All players should have the same board on startup"(){
        given:
        def b1 = new BoardGenerator().generateBoard(Dimension.xy(9, 9))
        def b2 = new BoardGenerator().generateBoard(Dimension.xy(9, 9))

        expect:
        new BoardChooser().chooseBoard(b1, b2) == new BoardChooser().chooseBoard(b2, b1)
    }
}
