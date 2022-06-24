const {Game} = require("../resources/gameAPIs/game")

function doTurn(game) {
    console.log("THIS IS GAME!")
    if (game instanceof Game) {
        game.changeSoldierType(0, "RANGED")
    } 
}

module.exports = {
    doTurn
}