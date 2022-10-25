interface IStateManager {
    //    fun preSetState(newGameState: Game, action: List<Action>)
    fun setState(newAbstractGameState: AbstractGame, abstractAction: List<AbstractAction>): AbstractGame
    fun postSetState(newAbstractGameState: AbstractGame): AbstractGame
}