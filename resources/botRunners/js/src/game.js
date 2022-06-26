class Game {
    side = -1
    mapData = {}
    castles = {}

    constructor(jsonGameState, side) {
        const gameState = JSON.parse(jsonGameState);
        this.mapData = gameState.mapData;
        this.castles = gameState.gameObjects.map(castleData => new Castle({...castleData}));
        this.side = side;
    }
}

class Castle {
    id
    soldierType
    side

    constructor({id, soldierType, side}) {
        this.id = id;
        this.soldierType = soldierType;
        this.side = side;
    }

    changeSoldierType(soldierType) {
        this.actions.push({
            actionType: "CHANGE_SOLDIER_TYPE",
            soldierType
        });
    }
}

module.exports = {
    Game,
    Castle
}