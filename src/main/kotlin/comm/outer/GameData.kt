package comm.outer

import comm.outer.objectsData.ObjectDataInterface

const val NATURAL_SIDE = -1

@kotlinx.serialization.Serializable
data class GameData(
    var mapData: MapData,
    var gameObjects: List<ObjectData<*>>
)