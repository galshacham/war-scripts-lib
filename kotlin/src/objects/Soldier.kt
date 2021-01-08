package objects

import main.enums.SoldierTypeEnum

data class Soldier(val id: Int,
                   val side: Int,
                   val loc: Location,
                   val soldierType: SoldierTypeEnum,
                   val health: Int,
                   val speed: Int,
                   val power: Int,
                   val range: Int) {

}


