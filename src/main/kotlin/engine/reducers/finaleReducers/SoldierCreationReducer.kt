package engine.reducers.finaleReducers

import engine.IdGenerator
import engine.objectsData.Castle
import engine.objectsData.Game
import engine.objectsData.RangedSoldier
import engine.objectsData.Soldier

class SoldierCreationReducer : IFinaleReducer {
    override fun finaleState(game: Game): Boolean {
        val addedSoldiers = mutableMapOf<Int, Soldier>()

        game.objects
            .filter { it.value is Castle }
            .forEach {
                val newId = IdGenerator.getId()

                // TODO change according to castle production type
                addedSoldiers[newId] = RangedSoldier(newId, it.value.loc)
            }

        game.objects.putAll(addedSoldiers)

        return true
    }
}