import Action from "./Action";
import Loc from "../Loc";

export default class MoveAction extends Action {
    newLoc: Loc

    constructor(activatorId: number, newLoc: Loc, owner: number) {
        super("MOVE", activatorId, owner);
        this.newLoc = newLoc;
    }
}