package enums

import comm.inner.AttackSoldierActionData
import comm.inner.ChangeSoldierActionData
import kotlin.reflect.KClass

enum class ActionTypeEnum(val soldierType: String) {
    CHANGE_SOLDIER_TYPE("CHANGE_SOLDIER_TYPE"),
    ATTACK_SOLDIER_TYPE("ATTACK_SOLDIER_TYPE"),
}
