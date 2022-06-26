class Game {
    side = -1
    mapData = {}

    constructor(jsonGameState, side) {
        const gameState = JSON.parse(jsonGameState);
        this.mapData = new MapData(gameState.mapData.rows, gameState.mapData.cols, gameState.mapData.players);
        this.gameObjects = gameState.gameObjects.map(gameObject => {
            const {id, side, loc: {row, col}} = gameObject
            switch (gameObject.objectType) {
                case CASTLE_TYPE :
                    return new Castle(id, side, new Loc(row, col), gameObject.creatingSoldierType)
            }
        });
        this.side = side;
    }

    getAllMyCastles() {
        return this.gameObjects.filter(gameObject => gameObject instanceof Castle && gameObject.side === this.side)
    }
}

const CASTLE_TYPE = "CASTLE";

class MapData {
    rows
    cols
    players

    constructor(rows, cols, players) {
        this.rows = rows
        this.cols = cols
        this.players = players
    }
}

class Loc {
    row
    col

    constructor(row, col) {
        this.row = row
        this.col = col
    }
}

class Castle {
    id
    soldierType
    side
    loc

    constructor(id, side, loc, soldierType) {
        this.id = id;
        this.soldierType = soldierType;
        this.side = side;
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