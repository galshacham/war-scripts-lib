import actionsData.Action
import objectsData.Game

interface IEngine {
    fun runTurn(game: Game, actions: List<Action>): Game
}