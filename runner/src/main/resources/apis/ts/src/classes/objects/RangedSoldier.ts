import Loc from "../Loc";
import Soldier from "./Soldier";

export default class RangedSoldier extends Soldier {
    constructor(id: number, loc: Loc, speed: number, owner: number) {
        super("RANGED", id, loc, speed, owner);
    }
}