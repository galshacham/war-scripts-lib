package objects

import Game
import main.enums.SoldierTypeEnum
import java.util.*

const val TURNS_TO_CREATE_SOLDIER = 5

data class Castle(override val id: String, override val side: Int, override val loc: Location) : GameObject {
    var soldierType = SoldierTypeEnum.MELEE

    fun changeSoldierType(newType: SoldierTypeEnum) {
        soldierType = newType
    }

    override fun updateState(game: Game) {
        if (isTimeToCreateSoldier(game)) {
            val newSoldier = Soldier(
                    UUID.randomUUID().toString(),
                    this.side,
                    this.loc,
                    this.soldierType
            )

            game.gameObjects.add(newSoldier)
        }
    }

    private fun isTimeToCreateSoldier(game: Game) = game.mapData.turn % TURNS_TO_CREATE_SOLDIER == 0
}
