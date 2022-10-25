interface IEngine {
    fun runTurn(gameState: Game, actions: List<Action>): Game
    fun isOver(gameState: Game): Boolean
}