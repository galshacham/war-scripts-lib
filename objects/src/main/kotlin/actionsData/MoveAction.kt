package actionsData

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import objectsData.Loc

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class MoveAction(
    override val activatorId: Int,
    val newLoc: Loc,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val type: String = "MOVE"
) : Action()
