class Engine(
    private val filterManager: IFilterManager,
    private val stateManager: IStateManager,
    private val statusManager: IStatusManager,
) : IEngine {
    override fun runTurn(abstractGameState: AbstractGame, abstractActions: List<AbstractAction>): AbstractGame {
        val validActions = filterManager.filterActions(abstractGameState, abstractActions)

        val newGameState = stateManager.setState(abstractGameState, validActions)

        return stateManager.postSetState(newGameState)
    }

    override fun isOver(abstractGameState: AbstractGame): Boolean {
        return statusManager.validateGameOver(abstractGameState)
    }
}
