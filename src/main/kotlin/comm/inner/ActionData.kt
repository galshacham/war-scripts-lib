package comm.inner

import enums.ActionTypeEnum
import enums.SoldierTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class ActionData<ADDITIONAL_DATA>(
    val activatorId: Int,
    val actionType: ActionTypeEnum,
    val actionData: ADDITIONAL_DATA
)