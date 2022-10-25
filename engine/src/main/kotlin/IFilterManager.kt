interface IFilterManager {
    fun filterActions(newAbstractGameState: AbstractGame, abstractActions: List<AbstractAction>): List<AbstractAction>
}