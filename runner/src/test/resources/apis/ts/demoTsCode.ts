import Game from "../../../../main/resources/apis/ts/src/Game"
import Loc from "../../../../main/resources/apis/ts/src/classes/Loc"

export function doTurn(game: Game) {
    game.moveSoldier(1, new Loc(1000, 1000))
    // game.moveSoldier(1, game.getDirections(1, new Loc(1000, 1000))[0])

    // game.actions.push(new MoveAction(16, new Loc(a, a)))
    // a++
    return game;
}
