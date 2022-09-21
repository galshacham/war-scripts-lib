package bigComm.comm.outer

@kotlinx.serialization.Serializable
data class MapData(val rows: Int,
                   val cols: Int,
                   val turn: Int,
                   val maxTurns: Int,
                   val players: List<Int>)