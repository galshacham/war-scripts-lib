const {Game} = require("../../resources/gameAPIs/game")

function doTurn(game) {
        game.changeSoldierType(0, "RANGED")

    return game;
}

module.exports = {
    doTurn
}