import GameObject from "./GameObject";
import Loc from "../Loc";

export default  class Castle extends GameObject {
    soldierType: string

    constructor(id: number, loc: Loc, soldierType: string) {
        super("CASTLE", id, loc);
        this.soldierType = soldierType;
    }
}