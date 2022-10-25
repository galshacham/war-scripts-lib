package objectsData

import AbstractGame
import kotlinx.serialization.Serializable

@Serializable
data class Game(val objects: MutableMap<Int, GameObject>, val gameData: GameData) : AbstractGame()