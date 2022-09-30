package bigComm.comm.inner

import engine.enums.SoldierTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class ChangeSoldierActionData(val creatingSoldierType: SoldierTypeEnum)
