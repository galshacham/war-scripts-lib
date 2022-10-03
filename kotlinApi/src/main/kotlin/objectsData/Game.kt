package objectsData

// TODO: Later change this to real shit
@kotlinx.serialization.Serializable
data class Game(val objects: MutableMap<Int, GameObject>, val gameData: GameData)