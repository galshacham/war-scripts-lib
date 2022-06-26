class Game {
    player // int
    mapData // MapData
    gameObjects // GameObject[]

    constructor(jsonGameState, player) {
        const gameState = JSON.parse(jsonGameState);
        this.mapData = new MapData(gameState.mapData.rows, gameState.mapData.cols, gameState.mapData.players);
        this.gameObjects = gameState.gameObjects.map(gameObject => {
            const {id, player, loc: {row, col}} = gameObject
            switch (gameObject.objectType) {
                case CASTLE_TYPE :
                    return new Castle(id, player, new Loc(row, col), gameObject.creatingSoldierType)
            }
        });
        this.player = player;
    }

    getAllMyCastles() {
        return this.gameObjects.filter(gameObject => gameObject instanceof Castle && gameObject.player === this.player)
    }
}

const CASTLE_TYPE = "CASTLE";

class MapData {
    rows // int
    cols // int
    players // int[]

    constructor(rows, cols, players) {
        this.rows = rows
        this.cols = cols
        this.players = players
    }
}

class Loc {
    row // int
    col // int

    constructor(row, col) {
        this.row = row
        this.col = col
    }
}

class GameObject {
    id // string
    player // int
    loc // Loc

    constructor(id, player, loc) {
        this.id = id;
        this.player = player;
        this.loc = loc;
    }
}

class Castle extends GameObject {
    soldierType // string

    constructor(id, player, loc, soldierType) {
        super(id, player, loc);
        this.id = id;
        this.soldierType = soldierType;
        this.player = player;
        this.loc = loc;
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
    Castle,
    Loc,
    MapData
}