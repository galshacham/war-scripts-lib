const {Game} = require("../resources/botRunners/js/src/game")

function doTurn(game) {
    console.log("QWEQ")
    if (game instanceof Game) {
        game.changeSoldierType(1, "RANGED")        
    } 
}

module.exports = {
    doTurn
}