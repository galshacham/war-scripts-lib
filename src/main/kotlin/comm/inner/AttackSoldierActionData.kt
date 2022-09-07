package comm.inner

import enums.SoldierTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class AttackSoldierActionData(val attackedSoldierId: Int)

