const {Game} = require("../../resources/botRunners/js/src/game")

function doTurn(game) {
    if (game instanceof Game) {
        // game.getAllMyCastles()[0].changeSoldierType("RANGED")
    }

    return game;
}

module.exports = {
    doTurn
}