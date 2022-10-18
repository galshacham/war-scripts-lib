package rules.reducers

import GameConstants.Companion.LOYAL_AFFECTION_RANGE
import GameConstants.Companion.LOYAL_AFFECTION_VALUE
import GameConstants.Companion.LOYAL_COUP_VALUE
import GameConstants.Companion.MAX_LOYALTY
import actionsData.Action
import actionsData.CaptureAction
import actionsData.MoveAction
import objectsData.Castle
import objectsData.Game
import objectsData.Soldier
import rules.interfaces.IApplyReducer
import rules.interfaces.IUpdateReducer
import rules.interfaces.IValidateReducer
import kotlin.reflect.KClass

class CaptureReducer : IValidateReducer<CaptureAction>, IUpdateReducer<CaptureAction>, IApplyReducer {
    override fun validate(game: Game, actions: List<CaptureAction>): List<Action> {
        return actions.filter {
            game.objects[it.idToCapture]!!.loc.inRange(game.objects[it.activatorId]!!.loc, LOYAL_AFFECTION_RANGE)
        }
    }

    override fun update(game: Game, actions: List<CaptureAction>) {
        actions.forEach {
            val castle = game.objects[it.idToCapture] as Castle

            castle.loyalty -= LOYAL_AFFECTION_VALUE
        }
    }

    // This needs a massive refactor
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

    override fun getActionType(): KClass<*> {
        return CaptureAction::class
    }
}