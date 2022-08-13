const {Game} = require("../../../main/resources/botRunners/js/src/game")

function doTurn(game) {
    if (game instanceof Game) {
        game.changeSoldierType(0 , "RANGED")
    }

    return game;
}

module.exports = {
    doTurn
}