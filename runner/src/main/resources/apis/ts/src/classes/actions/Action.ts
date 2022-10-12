export default  abstract class Action {
    activatorId: number
    owner: number
    type: string

    protected constructor(type: string, activatorId: number, owner: number) {
        this.type = type
        this.owner = owner
        this.activatorId = activatorId
    }
}