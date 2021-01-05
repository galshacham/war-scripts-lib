class Game {
    side = ""
    mapData = {}
    castles = {}

    // Should add Proxy
    actions = []

    constructor(jsonGameState, side) {
        const gameState = JSON.parse(jsonGameState); 
        this.mapData = gameState.mapData;
        this.castles = gameState.castles;
        this.side = side;  
    }

    changeSoldierType(castleId, soldierType) {
        this.actions.push({
            // Do I really need action id? its might be hard 
            actionType: "CHANGE_SOLDIER_TYPE",
            actionId: 1,
            side: this.side,
            affectedId: castleId,
            soldierType
        });
    }
}

module.exports = {
    Game
}