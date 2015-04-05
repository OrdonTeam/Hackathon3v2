package com.ordonteam.hackathon3.model.common

import com.ordonteam.hackathon3.model.board.Board
import com.ordonteam.hackathon3.model.board.Wall
import com.ordonteam.hackathon3.model.mobs.Fluppet
import com.ordonteam.hackathon3.model.mobs.Snorg
import com.ordonteam.hackathon3.model.mobs.Toxifera
import com.ordonteam.hackathon3.model.mobs.Zugar
import com.ordonteam.hackathon3.model.user.SelfUserBot
import com.ordonteam.hackathon3.view.PlayerPadView
import com.ordonteam.hackathon3.view.utils.ScaledCanvas
import groovy.transform.CompileStatic

import static com.ordonteam.hackathon3.model.common.Dimension.xy

@CompileStatic
class GameObjects implements Serializable {
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
                new Wall(xy(6, 6)), new SelfUserBot(xy(7, 7), playerPadView)
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

    GameObjects moveAll(Board board, GameObjects fromOthers) {
        List<? extends BaseGameObject> allGameObjects = allGameObjects(fromOthers, board)
        Set<BaseGameObject> collect = gameObjects.collect(this.&moveSingleObject.curry(board, allGameObjects)) as Set
        return new GameObjects(turn + 1, collect)
    }

    private List<? extends BaseGameObject> allGameObjects(GameObjects fromOthers, Board board) {
        List<? extends BaseGameObject> allGameObjects = []
        allGameObjects.addAll(gameObjects)
        allGameObjects.addAll(fromOthers.gameObjects)
        allGameObjects.addAll(board.walls)
        return allGameObjects
    }

    private BaseGameObject moveSingleObject(Board board, List<BaseGameObject> allGameObjects, BaseGameObject gameObject) {
        MoveDirection direction = gameObject.move(board, this)
        Dimension newDirection = gameObject.location.to(direction)

        BaseGameObject find = allGameObjects.find {
            it.location == newDirection
        }
        if (find) {
            triggerCollision(gameObject, find)
            return gameObject
        } else {
            return gameObject.withNewLocation(direction)
        }
    }

    void triggerCollision(BaseGameObject first, BaseGameObject second) {
        //first.collide(second)
        //second.collide(first)
    }

    Set<BaseGameObject> objectsAroundLocation(Dimension dimension) {
        //TODO: Location of returned objects should be relative
        //NOTE: This method is called frequently and should NOT create new objects
        return gameObjects
    }
}