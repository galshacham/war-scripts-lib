package reducers.applyingReducers

import GameConstants.Companion.LOYAL_AFFECTION_RANGE
import GameConstants.Companion.LOYAL_COUP_VALUE
import GameConstants.Companion.MAX_LOYALTY
import objectsData.Castle
import objectsData.Game
import objectsData.Soldier

class CaptureApplyReducer : IApplyReducer {
    override fun applyState(game: Game) {
        game.objects.forEach() {
            if (it.value is Castle) {
                val castle = it.value as Castle
                val ownersDivision = mutableMapOf<Int, Int>()

                if (castle.loyalty <= LOYAL_COUP_VALUE) {
                    game.objects.forEach { obj ->
                        if (obj.value is Soldier) if (castle.loc.inRange(
                                obj.value.loc,
                                LOYAL_AFFECTION_RANGE
                            )
                        ) if (ownersDivision.contains(obj.value.owner)) {
                            ownersDivision[obj.value.owner] = ownersDivision[obj.value.owner]!!.plus(1)
                        } else {
                            ownersDivision[obj.value.owner] = 1
                        }
                    }
                }
                val maxSoldiers = ownersDivision.values.maxOrNull()
                val mostSoldiersOwner =
                    ownersDivision.filterValues { numOfSoldiers -> numOfSoldiers == maxSoldiers }.keys

                if (mostSoldiersOwner.size == 1) {
                    castle.owner = mostSoldiersOwner.first()
                    castle.loyalty = MAX_LOYALTY
                }
            }

        }
    }

}