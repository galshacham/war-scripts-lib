package actionsData

import enums.ActionTypeEnum
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import objectsData.Loc

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class CaptureAction(
    override val activatorId: Int,
    val idToCapture: Int,
    override val owner: Int,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val type: ActionTypeEnum = ActionTypeEnum.CAPTURE,
) : Action()
