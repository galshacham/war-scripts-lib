package main.objects

import main.enums.SoldierTypeEnum

data class Castle(val id: Int, val side: String, val loc: Location) {
    var soldierType = SoldierTypeEnum.MELEE

    fun changeSoldierType(newType: SoldierTypeEnum) {
        soldierType = newType
    }

    fun changeSoldierType(newType: String) {
        soldierType = SoldierTypeEnum.valueOf(newType)
    }
}
