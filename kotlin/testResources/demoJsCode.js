const {Game} = require("../main/executors/gameAPIs/game")

function doTurn(game) {
    if (game instanceof Game) {
        game.changeSoldierType(1, "RANGED")        
    } 
}

module.exports = {
    doTurn
}