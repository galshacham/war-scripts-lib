import {forEach, isEqual} from "lodash";
import GameData from "./classes/GameData";
import GameObject from "./classes/objects/GameObject";
import Action from "./classes/actions/Action";
import Castle from "./classes/objects/Castle";
import Soldier from "./classes/objects/Soldier";
import Loc from "./classes/Loc";
import MoveAction from "./classes/actions/MoveAction";
import MeleeSoldier from "./classes/objects/MeleeSoldier";
import RangedSoldier from "./classes/objects/RangedSoldier";

export default class Game {
    gameData: GameData
    objects: Map<number, GameObject>
    actions: Action[]

    constructor(jsonGameState: string) {
        const gameState = JSON.parse(jsonGameState);
        this.actions = []
        this.gameData = new GameData(
            gameState.gameData.maxTurns,
            gameState.gameData.currentTurn
        );
        this.objects = new Map<number, GameObject>()

        forEach(gameState.objects, object => {
            const {id} = object;
            this.objects.set(id, this.createObject(object))
        });
    }

    private createObject(object: { id: number, type: string, loc: Loc }): GameObject {
        const {id, type, loc} = object
        switch (type) {
            case CASTLE_TYPE:
                return new Castle(
                    id,
                    new Loc(loc.col, loc.row),
                    // @ts-ignore -- this is the initiation only
                    object.soldierType,
                );
            case MELEE_SOLDIER_TYPE:
                return new MeleeSoldier(
                    id,
                    new Loc(loc.col, loc.row),
                    // @ts-ignore -- this is the initiation only
                    object.speed,
                );
            case RANGED_SOLDIER_TYPE:
                return new RangedSoldier(
                    id,
                    new Loc(loc.col, loc.row),
                    // @ts-ignore -- this is the initiation only
                    object.speed,
                );
            default:
                throw new Error("Type didnt match any type! " + JSON.stringify(object))
        }
    }

    public moveSoldier(soldierId: number, newLoc: Loc) {
        this.actions.push(new MoveAction(soldierId, newLoc))
    }

    public getDirections(soldierId: number, desiredLocation: Loc) {
        const options = new Array<Loc>()
        const {loc, speed} = this.objects.get(soldierId) as Soldier

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
const MELEE_SOLDIER_TYPE = "MELEE";
const RANGED_SOLDIER_TYPE = "RANGED";
