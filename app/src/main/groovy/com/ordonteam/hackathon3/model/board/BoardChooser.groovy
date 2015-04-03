package com.ordonteam.hackathon3.model.board

import groovy.transform.CompileStatic

@CompileStatic
final class BoardChooser {

    Board chooseBoard(Board firstBoard, Board secondBoard) {
        if (!firstBoard) return secondBoard
        if (!secondBoard) return firstBoard
        return firstBoard.hashCode > secondBoard.hashCode ? firstBoard : secondBoard
    }
}
