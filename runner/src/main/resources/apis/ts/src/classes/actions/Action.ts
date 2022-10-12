export default  abstract class Action {
    activatorId: number
    type: string

    protected constructor(actionType: string, activatorId: number) {
        this.type = actionType
        this.activatorId = activatorId
    }
}