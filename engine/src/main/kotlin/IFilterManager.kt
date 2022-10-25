interface IFilterManager {
    fun filterActions(newGameState: Game, actions: List<Action>): List<Action>
}