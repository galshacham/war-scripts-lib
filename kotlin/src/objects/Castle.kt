package main.objects

import SoldierFactory
import main.enums.SoldierTypeEnum
import objects.Location
import objects.Soldier

data class Castle(val id: Int, val side: Int, val loc: Location) {
    var soldierType = SoldierTypeEnum.MELEE

    fun changeSoldierType(newType: SoldierTypeEnum) {
        soldierType = newType
    }

    fun createSoldier(factory: SoldierFactory): Soldier = factory.createSoldier(side, loc, soldierType)
}
