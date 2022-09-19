package bigComm.comm.inner

import bigComm.enums.SoldierTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class ChangeSoldierActionData(val creatingSoldierType: SoldierTypeEnum)
