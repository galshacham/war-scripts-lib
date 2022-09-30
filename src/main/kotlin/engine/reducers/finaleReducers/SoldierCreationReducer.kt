package engine.reducers.finaleReducers

import engine.enums.SoldierTypeEnum
import engine.IdGenerator
import engine.objectsData.*

class SoldierCreationReducer : IFinaleReducer {
    override fun finaleState(game: Game): Boolean {
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

        return true
    }
}