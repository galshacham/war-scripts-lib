export default  class GameData {
    maxTurns: number
    currentTurn: number

    constructor(maxTurns: number, currentTurn: number) {
        this.currentTurn = currentTurn
        this.maxTurns = maxTurns
    }
}