package main.objects

import main.enums.SoldierTypeEnum

data class Castle(val id: Int, val side: Int, val loc: Location) {
    var soldierType = SoldierTypeEnum.MELEE

    fun changeSoldierType(newType: SoldierTypeEnum) {
        soldierType = newType
    }

    fun changeSoldierType(newType: String) {
        soldierType = SoldierTypeEnum.valueOf(newType)
    }
}
