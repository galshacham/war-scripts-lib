const {Game} = require("../src/Streamers/gameAPIs/game")

function doTurn(game) {
    if (game instanceof Game) {
        game.changeSoldierType(1, "RANGED")        
    } 
    throw Error("Some error");
}

module.exports = {
    doTurn
}