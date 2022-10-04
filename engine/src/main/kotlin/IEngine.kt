import actionsData.Action
import objectsData.Game

interface IEngine {
    fun runTurn(game: String, actions: String): String
}