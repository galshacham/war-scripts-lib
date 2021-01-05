package main.objects.actions

import main.Engine

data class ChangeSoldierTypeAction(val actionId: Int, val side: Int, val affectedId: Int, val soldierType: String) :
        Action(actionId, side) {

    override fun apply(engine: Engine) {
        val selectedCastle = engine.castles.find { it.side == side && it.id == affectedId }

        if (selectedCastle == null) {
            print("castle of id $affectedId on the side of $side does not exist, action did not occur!")
        } else
            selectedCastle.changeSoldierType(soldierType)
    }

    override fun validate(engine: Engine) {
        TODO("Not yet implemented")
    }
}