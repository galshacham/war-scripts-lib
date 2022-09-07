package comm.outer

const val NATURAL_SIDE = -1

@kotlinx.serialization.Serializable
data class GameData(
    var mapData: MapData,
    var gameObjects: List<BaseData>
)