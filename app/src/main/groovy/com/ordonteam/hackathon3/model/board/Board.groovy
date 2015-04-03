package com.ordonteam.hackathon3.model.board

import com.ordonteam.hackathon3.model.common.Dimension
import com.ordonteam.hackathon3.view.GameDrawable
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

import static Dimension.xy

@CompileStatic
class Board implements GameDrawable, Serializable {
    static final long serialVersionUID = 42L

    long hashCode
    Dimension size
    List<Wall> walls = new ArrayList<>()

    @Override
    void draw(ScaledCanvas canvas) {
        walls.each {
            it.draw(canvas)
        }
    }

    static Board generateBoard(int playerNumber) {
        return generateBoard(Dimension.xy(10, 10))
    }

//  TODO: remove compile dynamic
    @CompileDynamic
    static Board generateBoard(Dimension size) {
        Board board = new Board(size: size)
        (0..<size.x).each { int x ->
            board.walls.add(new Wall(xy(x, 0)))
            board.walls.add(new Wall(xy(x, size.y - 1)))
        }
        (1..<(size.y - 1)).each { int y ->
            board.walls.add(new Wall(xy(0, y)))
            board.walls.add(new Wall(xy(size.x - 1, y)))
        }
        board.walls.add(new Wall(xy(new Random().nextInt(size.x-2)+1,new Random().nextInt(size.y-2)+1)))
        board.hashCode = new Random().nextLong()
        return board
    }

    @CompileDynamic
    static Board chooseBoard(Board firstBoard, Board secondBoard) {
        if (!firstBoard) return secondBoard
        if (!secondBoard) return firstBoard
        return firstBoard.hashCode > secondBoard.hashCode ? firstBoard : secondBoard
    }
}
