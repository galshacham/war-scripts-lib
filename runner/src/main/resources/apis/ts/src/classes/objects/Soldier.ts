import GameObject from "./GameObject";
import Loc from "../Loc";

export default abstract class Soldier extends GameObject {
    speed: number

    protected constructor(type: string, id: number, loc: Loc, speed: number, owner: number) {
        super(type, id, loc, owner);
        this.speed = speed;
    }
}