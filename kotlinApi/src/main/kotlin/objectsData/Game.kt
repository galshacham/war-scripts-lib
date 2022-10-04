package objectsData

@kotlinx.serialization.Serializable
data class Game(val objects: MutableMap<Int, GameObject>, val gameData: GameData)