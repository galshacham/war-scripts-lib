interface IEngine {
    fun runTurn(abstractGameState: AbstractGame, abstractActions: List<AbstractAction>): AbstractGame
    fun isOver(abstractGameState: AbstractGame): Boolean
}