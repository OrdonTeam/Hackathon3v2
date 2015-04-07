package com.ordonteam.hackathon3.model.board

import com.ordonteam.hackathon3.model.common.Dimension
import groovy.transform.CompileStatic

import static com.ordonteam.hackathon3.model.common.Dimension.xy

@CompileStatic
final class BoardGenerator {

    Board generateBoard(Dimension size) {
        Board board = new Board(size: size)
        5.times {
            board.walls.add(new Wall(xy(new Random().nextInt(size.x), new Random().nextInt(size.y))))
        }
        board.hashCode = new Random().nextLong()
        return board
    }
}
