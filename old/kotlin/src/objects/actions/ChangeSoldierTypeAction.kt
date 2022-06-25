package objects.actions

import Game
import exceptions.ActionValidationException
import main.enums.SoldierTypeEnum
import main.objects.actions.Action
import objects.Castle

data class ChangeSoldierTypeAction(override val actionId: String, override val side: Int, val affectedId: String, val soldierType: SoldierTypeEnum) : Action {

    override fun apply(game: Game) {
        val selectedCastle = game.gameObjects.find { it.id == affectedId }

        if (selectedCastle == null) {
            print("castle of id $affectedId on the side of $side does not exist, action did not occur!")
        } else {
            selectedCastle as Castle
            selectedCastle.changeSoldierType(soldierType)
        }
    }

    override fun validate(game: Game) {
        super.validate(game)
        val affectedCastle = game.gameObjects.find { it.id == affectedId }!!

        if (affectedCastle.side != side) {
            throw ActionValidationException("Invalid action! Trying to change soldier type of castle which does not belong to user (castleId: ${affectedCastle.id})")
        }
    }
}