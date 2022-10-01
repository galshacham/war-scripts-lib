class Game {
    owner; // int
    mapData; // MapData
    gameObjects; // GameObject[]
    logs; // String[]
    actions; // AbstractAction[]

    constructor(jsonGameState, owner) {
        const gameState = JSON.parse(jsonGameState);
        this.logs = []
        this.actions = []
        this.mapData = new MapData(
            gameState.mapData.rows,
            gameState.mapData.cols,
            gameState.mapData.players
        );
        this.gameObjects = gameState.gameObjects.map((gameObject) => {
            const {
                id,
                owner,
                loc: {row, col},
            } = gameObject;
            switch (gameObject.objectType) {
                case CASTLE_TYPE:
                    return new Castle(
                        id,
                        owner,
                        new Loc(row, col),
                        gameObject.creatingSoldierType,
                    );
            }
        });
        this.owner = owner;
    }

    getAllMyCastles() {
        return this.gameObjects.filter((gameObject) => gameObject instanceof Castle && gameObject.owner === this.owner);
    }

    changeSoldierType(castleId, soldierType) {
        this.actions.push(new ChangeSoldierTypeAction(castleId, soldierType));
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
    activatorId // int
    actionType // string
    constructor(actionType, activatorId) {
        this.actionType = actionType
        this.activatorId = activatorId
    }
}

class ChangeSoldierTypeAction extends AbstractAction {
    creatingSoldierType; // string

    constructor(activatorId, creatingSoldierType) {
        super(CHANGE_SOLDIER_TYPE, activatorId);
        this.creatingSoldierType = creatingSoldierType;
    }
}

class GameObject {
    objectType // string
    id; // int
    owner; // int
    loc; // Loc

    constructor(id, owner, loc, objectType) {
        this.objectType = objectType;
        this.id = id;
        this.owner = owner;
        this.loc = loc;
    }
}

class Castle extends GameObject {
    creatingSoldierType; // string

    constructor(id, owner, loc, soldierType) {
        super(id, owner, loc, CASTLE_TYPE);
        this.id = id;
        this.creatingSoldierType = soldierType;
        this.owner = owner;
        this.loc = loc;
    }
}

module.exports = {
    Game,
    Castle,
    Loc,
    MapData,
    ChangeSoldierTypeAction,
};
