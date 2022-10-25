interface IStateManager {
//    fun preSetState(newGameState: Game, action: List<Action>)
    fun setState(newGameState: Game, action: List<Action>): Game
    fun postSetState(newGameState: Game): Game
}