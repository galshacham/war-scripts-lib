import actionsData.Action
import objectsData.Game

interface IEngine {
    fun runTurn(gameState: Game, actions: List<Action>): Game
    fun isOver(gameState: Game): Boolean
}