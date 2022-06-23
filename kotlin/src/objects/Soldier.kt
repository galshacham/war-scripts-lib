package objects

import Engine
import main.enums.SoldierTypeEnum

data class Soldier(override val id: String,
                   override val side: Int,
                   override val loc: Location,
                   val soldierType: SoldierTypeEnum) : GameObject {

    private val health: Int
    private val speed: Int
    private val power: Int
    private val range: Int

    init {
        when (soldierType) {
            SoldierTypeEnum.MELEE -> {
                health = 4
                speed = 2
                power = 2
                range = 1
            }
            SoldierTypeEnum.RANGED -> {
                health = 1
                speed = 2
                power = 2
                range = 4
            }
        }
    }

    override fun updateState(game: Engine) {

    }
}

