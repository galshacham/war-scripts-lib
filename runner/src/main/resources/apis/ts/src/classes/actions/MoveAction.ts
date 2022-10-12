import Action from "./Action";
import Loc from "../Loc";

export default class MoveAction extends Action {
    newLoc: Loc

    constructor(activatorId: number, newLoc: Loc) {
        super("MOVE", activatorId);
        this.newLoc = newLoc;
    }
}