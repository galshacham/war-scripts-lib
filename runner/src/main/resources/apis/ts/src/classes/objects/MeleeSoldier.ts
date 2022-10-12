import Loc from "../Loc";
import Soldier from "./Soldier";

export default class MeleeSoldier extends Soldier {
    constructor(id: number, loc: Loc, speed: number, owner: number) {
        super("MELEE", id, loc, speed, owner);
    }
}