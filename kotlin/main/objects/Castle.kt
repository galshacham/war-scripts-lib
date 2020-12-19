package main.objects

import main.enums.SoldierTypeEnum

data class Castle(val id: Int, val side: String, val loc: Location) {
    fun changeSoldierType(newType: SoldierTypeEnum) {
        soldierType = newType
    }

    var soldierType = SoldierTypeEnum.MELEE
}
