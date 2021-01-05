package main.objects.actions

import exceptions.ActionValidationException
import main.Engine

data class ChangeSoldierTypeAction(override val actionId: Int, override val side: Int, val affectedId: Int, val soldierType: String) :
        Action(actionId, side) {

    override fun apply(engine: Engine) {
        val selectedCastle = engine.castles.find { it.side == side && it.id == affectedId }

        if (selectedCastle == null) {
            print("castle of id $affectedId on the side of $side does not exist, action did not occur!")
        } else
            selectedCastle.changeSoldierType(soldierType)
    }

    override fun validate(engine: Engine) {
        super.validate(engine)
        val affectedCastle = engine.castles.find { it.id == affectedId }!!

        if (affectedCastle.side != side) {
            throw ActionValidationException("Invalid action! Trying to change soldier type of castle which does not belong to user (castleId: ${affectedCastle.id})")
        }
    }
}