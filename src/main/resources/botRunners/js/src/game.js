class Game {
    player; // int
    mapData; // MapData
    gameObjects; // GameObject[]
    logs; // String[]

    constructor(jsonGameState, player) {
        const gameState = JSON.parse(jsonGameState);
        this.logs = []
        this.mapData = new MapData(
            gameState.mapData.rows,
            gameState.mapData.cols,
            gameState.mapData.players
        );
        this.gameObjects = gameState.gameObjects.map((gameObject) => {
            const {
                id,
                player,
                loc: {row, col},
            } = gameObject;
            switch (gameObject.objectType) {
                case CASTLE_TYPE:
                    return new Castle(
                        id,
                        player,
                        new Loc(row, col),
                        gameObject.creatingSoldierType,
                    );
            }
        });
        this.player = player;
    }

    getAllMyCastles() {
        return this.gameObjects.filter((gameObject) => gameObject instanceof Castle && gameObject.player === this.player);
    }

    log(message) {
        this.logs.push(message);
    }
}

const CASTLE_TYPE = "CASTLE";

class MapData {
    rows; // int
    cols; // int
    players; // int[]

    constructor(rows, cols, players) {
        this.rows = rows;
        this.cols = cols;
        this.players = players;
    }
}

class Loc {
    row; // int
    col; // int

    constructor(row, col) {
        this.row = row;
        this.col = col;
    }
}

const CHANGE_SOLDIER_TYPE = "CHANGE_SOLDIER_TYPE";

class AbstractAction {
    actionType // string
    constructor(actionType) {
        this.actionType = actionType
    }
}

class ChangeSoldierTypeAction extends AbstractAction {
    creatingSoldierType; // string

    constructor(creatingSoldierType) {
        super(CHANGE_SOLDIER_TYPE);
        this.creatingSoldierType = creatingSoldierType;
    }
}

class GameObject {
    objectType // string
    id; // string
    player; // int
    loc; // Loc
    action; // ?Action

    constructor(id, player, loc, objectType) {
        this.objectType = objectType;
        this.id = id;
        this.player = player;
        this.loc = loc;
    }
}

class Castle extends GameObject {
    creatingSoldierType; // string

    constructor(id, player, loc, soldierType) {
        super(id, player, loc, CASTLE_TYPE);
        this.id = id;
        this.creatingSoldierType = soldierType;
        this.player = player;
        this.loc = loc;
    }

    changeSoldierType(soldierType) {
        this.action = new ChangeSoldierTypeAction(soldierType);
    }
}

module.exports = {
    Game,
    Castle,
    Loc,
    MapData,
    ChangeSoldierTypeAction,
};
