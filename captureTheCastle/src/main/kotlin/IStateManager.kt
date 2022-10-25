import actionsData.Action
import objectsData.Game

interface IStateManager {
    fun setState(newGameState: Game, oldGameState: Game, it: Action)
}