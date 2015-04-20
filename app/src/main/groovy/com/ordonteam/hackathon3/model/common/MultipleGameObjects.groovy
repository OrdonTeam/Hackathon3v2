package com.ordonteam.hackathon3.model.common

import com.ordonteam.hackathon3.controller.PlayerIdentifier
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
class MultipleGameObjects implements Serializable {
    private static final long serialVersionUID = 42L

    final int turn
    final Set<SingleGameObject> gameObjects
    Map<PlayerIdentifier,MultipleGameObjects> fromOthers

    MultipleGameObjects(Set<SingleGameObject> gameObjects) {
        this(0, gameObjects)
    }

    MultipleGameObjects(int turn, Set<SingleGameObject> gameObjects) {
        this.turn = turn
        this.gameObjects = gameObjects
    }

    static MultipleGameObjects generateObjects(Board board, PlayerPadView playerPadView) {
        new MultipleGameObjects([
                new Zugar(xy(1, 1)), new Snorg(xy(2, 2)), new Fluppet(xy(3, 3)), new Toxifera(xy(4, 5)),
                new Wall(xy(6, 6)), new SelfUserBot(xy(7, 7), playerPadView)
        ] as Set)
    }

    void drawAll(ScaledCanvas canvas) {
        gameObjects.each {
            it.draw(canvas)
        }
    }

    void add(SingleGameObject object) {
        gameObjects.add(object)
    }

    MultipleGameObjects moveAll(Board board, Map<PlayerIdentifier,MultipleGameObjects> fromOthers) {
        this.fromOthers = fromOthers
        List<? extends SingleGameObject> allGameObjects = allGameObjects(fromOthers.values(), board)
        Set<SingleGameObject> collect = gameObjects.collect(this.&moveSingleObject.curry(board, allGameObjects)) as Set
        return new MultipleGameObjects(turn + 1, collect)
    }

    private List<? extends SingleGameObject> allGameObjects(Collection<MultipleGameObjects> fromOthers, Board board) {
        List<? extends SingleGameObject> allGameObjects = []
        allGameObjects.addAll(gameObjects)
        //allGameObjects.addAll(fromOthers*.gameObjects.flatten())
        fromOthers.each {
            allGameObjects.addAll(it.gameObjects)
        }
        allGameObjects.addAll(board.walls)
        return allGameObjects
    }

    private SingleGameObject moveSingleObject(Board board, SingleGameObject gameObject) {
        MoveDirection direction = gameObject.move(board, this)
        Dimension newDirection = gameObject.location.to(direction)

        SingleGameObject find = allGameObjects(fromOthers.values(), board).find {
            it.location == newDirection
        }
        if (find) {
            triggerCollision(gameObject, find)
            return gameObject
        }
        return gameObject.withNewLocation(direction)
    }

    void triggerCollision(SingleGameObject first, SingleGameObject second) {
        //first.collide(second)
        //second.collide(first)
    }

    Set<SingleGameObject> objectsAroundLocation(Dimension dimension) {
        //TODO: Location of returned objects should be relative
        //NOTE: This method is called frequently and should NOT create new objects
        //NOTE2: at this point we must know all game objects including whether they are owned by this player or some other
        return gameObjects
    }
}
