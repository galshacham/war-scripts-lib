const {Game, MoveAction, Loc} = require("../../../../main/resources/apis/js/src/game")
let a = 0

function doTurn(game) {
    if (game instanceof Game) {
        
    }

    // game.actions.push(new MoveAction(16, new Loc(a, a)))
    // a++
    return game;
}

module.exports = {
    doTurn
}