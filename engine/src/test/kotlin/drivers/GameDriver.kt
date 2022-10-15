package drivers

import drivers.DriversTestConstants.CURRENT_TURN
import drivers.DriversTestConstants.MAX_TURNS
import objectsData.Game
import objectsData.GameData
import objectsData.GameObject

object GameDriver {
    private fun aBasicGameData() = GameData(CURRENT_TURN, MAX_TURNS)

    fun aGame(vararg objects: GameObject): Game {
        val gameObjects = mutableMapOf<Int, GameObject>()
        objects.forEach { gameObjects[it.id] = it }
        return Game(gameObjects, aBasicGameData())
    }
}