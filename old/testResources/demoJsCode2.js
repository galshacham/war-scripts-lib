const {Game} = require("../resources/gameAPIs/game")

function doTurn(game) {
    console.log("QWEQ")
    if (game instanceof Game) {
        game.changeSoldierType(1, "RANGED")        
    } 
}

module.exports = {
    doTurn
}