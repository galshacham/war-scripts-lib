const {forEach, isEqual} = require("lodash");

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
                    new Loc(loc.col, loc.row),
                    object.soldierType,
                );
            case SOLDIER_TYPE:
                return new Soldier(
                    id,
                    // owner,
                    new Loc(loc.col, loc.row),
                    object.speed,
                );
        }
    }

    moveSoldier(soldierId, newLoc) {
        this.actions.push(new MoveAction(soldierId, newLoc))
    }

    getDirections(soldierId, desiredLocation) {
        const options = []
        const {loc, speed} = this.objects[soldierId]

        const movingRowDiff = loc.row - desiredLocation.row
        const movingColDiff = loc.col - desiredLocation.col

        const movingUpMultiplier = movingRowDiff < 0 ? 1 : -1
        const movingRightMultiplier = movingColDiff < 0 ? 1 : -1

        for (let row = 0; row <= Math.abs(movingRowDiff); row++) {
            for (let col = 0; col <= Math.abs(movingColDiff); col++) {
                const optionalLoc = new Loc(loc.col + movingRightMultiplier * col, loc.row + movingUpMultiplier * row)
                if (loc.inRange(optionalLoc, speed) === 0 && !isEqual(loc, optionalLoc))
                    options.push(optionalLoc)
            }
        }

        return options
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
    col; // int
    row; // int

    constructor(col, row) {
        this.col = col;
        this.row = row;
    }

    inRange(loc, range) {
        const diff = Math.abs(this.col - loc.col) + Math.abs(this.row - loc.row)
        if (diff === range) return 0
        return diff < range ? 1 : -1
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
