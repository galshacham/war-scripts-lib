package reducers.applyingReducers

import IdGenerator
import enums.ObjectTypeEnum
import objectsData.*

class SoldierCreationReducer : IApplyReducer {
    override fun applyState(game: Game) {
        val addedSoldiers = mutableMapOf<Int, Soldier>()

        game.objects.forEach() {
            if (it.value is Castle) {
                val newId = IdGenerator.getId(game.objects)

                val newSoldier = when ((it.value as Castle).soldierType) {
                    ObjectTypeEnum.MELEE -> MeleeSoldier(newId, it.value.loc, it.value.owner)
                    ObjectTypeEnum.RANGED -> RangedSoldier(newId, it.value.loc, it.value.owner)
                    else -> throw Exception("Error: ignored soldier creation since the type given was $it is not a soldier!")
                }

                addedSoldiers[newId] = newSoldier
            }
        }

        game.objects.putAll(addedSoldiers)
    }
}