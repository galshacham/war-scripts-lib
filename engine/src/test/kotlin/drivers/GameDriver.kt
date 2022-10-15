package drivers

import drivers.TestConstants.CURRENT_TURN
import drivers.TestConstants.MAX_TURNS
import drivers.TestConstants.SOLDIER_ID_1
import drivers.objects.Soldiers.aRangedSoldier
import objectsData.Game
import objectsData.GameData

object GameDriver {
    private fun aBasicGameData() = GameData(CURRENT_TURN, MAX_TURNS)

    fun aGameWithSoldier() = Game(mutableMapOf(Pair(SOLDIER_ID_1, aRangedSoldier())), aBasicGameData())
}