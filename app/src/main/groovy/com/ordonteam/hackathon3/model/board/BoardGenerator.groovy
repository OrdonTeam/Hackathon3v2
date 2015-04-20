package com.ordonteam.hackathon3.model.board

import com.ordonteam.hackathon3.model.common.Dimension
import groovy.transform.CompileStatic

import static com.ordonteam.hackathon3.model.common.Dimension.xy

@CompileStatic
final class BoardGenerator {

    private static Board generatedBoard

    static Board generateBoard(Dimension size) {
        Board generatedBoard = new Board(size: size)
        5.times {
            generatedBoard.walls.add(new Wall(xy(new Random().nextInt(size.x), new Random().nextInt(size.y))))
        }
        generatedBoard.hashCode = new Random().nextLong()
        return generatedBoard
    }

    static Board forDimension(Dimension dimension) {
        return generatedBoard ?: generateBoard(dimension)
    }
}
