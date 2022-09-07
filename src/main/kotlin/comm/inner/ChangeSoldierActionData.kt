package comm.inner

import enums.SoldierTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class ChangeSoldierActionData(val creatingSoldierType: SoldierTypeEnum)
