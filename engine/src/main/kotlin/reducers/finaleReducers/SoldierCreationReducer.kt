package reducers.finaleReducers

import IdGenerator
import enums.SoldierTypeEnum
import objectsData.*

class SoldierCreationReducer : IFinaleReducer {
    override fun finaleState(game: Game) {
        val addedSoldiers = mutableMapOf<Int, Soldier>()

        game.objects.forEach() {
            if (it.value is Castle) {
                val newId = IdGenerator.getId()

                val newSoldier = when ((it.value as Castle).soldierType) {
                    SoldierTypeEnum.MELEE -> MeleeSoldier(newId, it.value.loc)
                    SoldierTypeEnum.RANGED -> RangedSoldier(newId, it.value.loc)
                }

                addedSoldiers[newId] = newSoldier
            }
        }

        game.objects.putAll(addedSoldiers)
    }
}