import Loc from "../Loc";

export default abstract class GameObject {
    type: string
    owner: number
    id: number
    loc: Loc

    protected constructor(type: string, id: number, loc: Loc, owner: number) {
        this.owner = owner;
        this.type = type;
        this.id = id;
        this.loc = loc;
    }
}
