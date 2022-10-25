class Engine(
    private val filterManager: IFilterManager,
    private val stateManager: IStateManager,
    private val statusManager: IStatusManager,
) : IEngine {
    override fun runTurn(gameState: Game, actions: List<Action>): Game {
        val validActions = filterManager.filterActions(gameState, actions)

        val newGameState = stateManager.setState(gameState, validActions)

        return stateManager.postSetState(newGameState)
    }

    override fun isOver(gameState: Game): Boolean {
        return statusManager.validateGameOver(gameState)
    }
}
