const {forEach} = require("lodash");

class Game {
    // owner; // int
    gameData; // MapData
    objects; // GameObject[]
    // logs; // String[]
    actions; // AbstractAction[]

    constructor(jsonGameState/*, owner*/) {
        const gameState = JSON.parse(jsonGameState);
        // this.logs = []
        this.actions = []
        this.gameData = new GameData(
            // gameState.mapData.rows,
            // gameState.mapData.cols,
            // gameState.mapData.players
            gameState.gameData.maxTurns,
            gameState.gameData.currentTurn
        );
        this.objects = {}

        forEach(gameState.objects, object => {
            const {id} = object;
            this.objects[id] = this.createObject(object)
        });
        // this.owner = owner;
    }

    createObject(object) {
        const {id, type, loc} = object
        switch (type) {
            case CASTLE_TYPE:
                return new Castle(
                    id,
                    // owner,
                    new Loc(loc.row, loc.col),
                    object.soldierType,
                );
            case SOLDIER_TYPE:
                return new Soldier(
                    id,
                    // owner,
                    new Loc(row, col),
                    4,
                );
        }
    }

    // getAllMyCastles() {
    //     return this.objects.filter((gameObject) => gameObject instanceof Castle && gameObject.owner === this.owner);
    // }

    // changeSoldierType(castleId, soldierType) {
    //     this.actions.push(new ChangeSoldierTypeAction(castleId, soldierType));
    // }

    // log(message) {
    //     this.logs.push(message);
    // }
}

const CASTLE_TYPE = "CASTLE";
const SOLDIER_TYPE = "SOLDIER";

class GameData {
    maxTurns // int
    currentTurn // int
    // rows; // int
    // cols; // int
    // players; // int[]

    constructor(/*rows, cols, players*/maxTurns, currentTurn) {
        // this.rows = rows;
        // this.cols = cols;
        // this.players = players;
        this.currentTurn = currentTurn
        this.maxTurns = maxTurns
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

// const CHANGE_SOLDIER_TYPE = "CHANGE_SOLDIER_TYPE";

class AbstractAction {
    activatorId // int
    type // string
    constructor(actionType, activatorId) {
        this.type = actionType
        this.activatorId = activatorId
    }
}

// class ChangeSoldierTypeAction extends AbstractAction {
//     creatingSoldierType; // string
//
//     constructor(activatorId, creatingSoldierType) {
//         super(CHANGE_SOLDIER_TYPE, activatorId);
//         this.creatingSoldierType = creatingSoldierType;
//     }
// }

class MoveAction extends AbstractAction {
    newLoc; // Loc

    constructor(activatorId, newLoc) {
        super("MOVE", activatorId);
        this.newLoc = newLoc;
    }
}

class GameObject {
    type // string
    id; // int
    // owner; // int
    loc; // Loc

    constructor(type, id/*, owner*/, loc) {
        this.type = type;
        this.id = id;
        // this.owner = owner;
        this.loc = loc;
    }
}

class Castle extends GameObject {
    soldierType; // string

    constructor(id/*, owner*/, loc, soldierType) {
        super(CASTLE_TYPE, id/*, owner*/, loc);
        this.soldierType = soldierType;
        // this.owner = owner;
    }
}

class Soldier extends GameObject {
    constructor(id/*, owner*/, loc, speed) {
        super(SOLDIER_TYPE, id/*, owner*/, loc, speed);
        // this.owner = owner;
        this.speed = speed;
    }
}


module.exports = {
    Game,
    Loc,
    GameData,
    GameObject,
    Castle,
    Soldier,
    AbstractAction,
    MoveAction,
    // ChangeSoldierTypeAction,
};
