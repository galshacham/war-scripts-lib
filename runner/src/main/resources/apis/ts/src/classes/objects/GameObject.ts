import Loc from "../Loc";

export default  abstract class GameObject {
    type: string
    id: number
    loc: Loc

    protected constructor(type: string, id: number, loc: Loc) {
        this.type = type;
        this.id = id;
        this.loc = loc;
    }
}
