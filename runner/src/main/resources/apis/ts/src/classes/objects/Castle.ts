import GameObject from "./GameObject";
import Loc from "../Loc";

export default  class Castle extends GameObject {
    soldierType: string

    constructor(id: number, loc: Loc, soldierType: string, owner: number) {
        super("CASTLE", id, loc, owner);
        this.soldierType = soldierType;
    }
}