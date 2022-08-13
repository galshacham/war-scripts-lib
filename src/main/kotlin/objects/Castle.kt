package objects

import Game
import enums.SoldierTypeEnum
import objects.actions.Action
import objects.actions.ChangeSoldierTypeAction

const val TURNS_TO_CREATE_SOLDIER = 5

@kotlinx.serialization.Serializable
data class Castle(
    override val id: Int,
    override val owner: Int,
    override val loc: Location,
    override val action: ChangeSoldierTypeAction?
) : GameObject<ChangeSoldierTypeAction> {
    var soldierType = SoldierTypeEnum.MELEE

    fun changeSoldierType(newType: SoldierTypeEnum) {
        soldierType = newType
    }

    override fun updateState(game: Game) {
//        if (isTimeToCreateSoldier(game)) {
//            val newSoldier = Soldier(
//                    UUID.randomUUID().toString(),
//                    this.side,
//                    this.loc,
//                    this.soldierType
//            )
//
//            game.gameObjects.add(newSoldier)
//        }
    }

//    private fun isTimeToCreateSoldier(game: Game) = game.mapData.turn % TURNS_TO_CREATE_SOLDIER == 0
}
