package com.ordonteam.hackathon3.model.mobs

import com.ordonteam.hackathon3.model.user.UserBot
import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.board.Wall
import com.ordonteam.hackathon3.model.common.BaseGameObject
import com.ordonteam.hackathon3.model.common.MoveDirection
import com.ordonteam.hackathon3.view.PlayerPadView
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.CompileStatic

import static com.ordonteam.hackathon3.model.common.Dimension.xy

@CompileStatic
class GameObjects implements Serializable{
    static final long serialVersionUID = 42L

    final int turn
    final Set<BaseGameObject> gameObjects

    GameObjects(Set<BaseGameObject> gameObjects) {
        this(0, gameObjects)
    }

    GameObjects(int turn, Set<BaseGameObject> gameObjects) {
        this.turn = turn
        this.gameObjects = gameObjects
    }

    static GameObjects generateObjects(Board board, PlayerPadView playerPadView) {
        new GameObjects([
                new Zugar(xy(1, 1)), new Snorg(xy(2, 2)), new Fluppet(xy(3, 3)), new Toxifera(xy(4, 5)),
                new Wall(xy(6, 6)), new UserBot(xy(7, 7), playerPadView)
        ] as Set)
    }

    void drawAll(ScaledCanvas canvas) {
        gameObjects.each {
            it.draw(canvas)
        }
    }

    void add(BaseGameObject object) {
        gameObjects.add(object)
    }

    GameObjects moveAll() {

        Set<BaseGameObject> collect = gameObjects.collect { BaseGameObject gameObject ->
            MoveDirection direction = gameObject.move()
            BaseGameObject find = gameObjects.find {
                it.location == gameObject.location.to(direction)
            }
            if (find) {
                return gameObject
            } else {
                return gameObject.withNewLocation(direction)
            }
        } as Set
        return new GameObjects(turn + 1, collect)
    }

}
