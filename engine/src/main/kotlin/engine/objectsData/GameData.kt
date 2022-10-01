package engine.objectsData

@kotlinx.serialization.Serializable
data class GameData(val maxTurns: Int, var currentTurn: Int)