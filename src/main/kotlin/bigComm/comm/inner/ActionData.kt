package bigComm.comm.inner

import bigComm.enums.ActionTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class ActionData<ADDITIONAL_DATA>(
    val activatorId: Int,
    val actionType: ActionTypeEnum,
    val actionData: ADDITIONAL_DATA
)