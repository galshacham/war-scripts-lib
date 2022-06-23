const {Game} = require("../resources/gameAPIs/game")

function doTurn(game) {
    if (game instanceof Game) {
        game.changeSoldierType(0, "RANGED")
    } 
}

module.exports = {
    doTurn
}