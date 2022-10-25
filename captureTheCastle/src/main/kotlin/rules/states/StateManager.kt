package rules.states

import enums.ReducerEnum
import objectsData.Game
import objectsData.Soldier
import rules.interfaces.IReducer


// Maybe caching will be important
class StateManager {
    fun getState(game: Game, reducer: IReducer<*>): IState {
        when (reducer.getReducerType()) {
            ReducerEnum.SOLDIERS -> {
                return SoldiersState(game.objects.filterValues { it is Soldier } as Map<Int, Soldier>)
            }
        }
    }

    fun mergeState(stateList: List<IState>): Game {
        TODO("Not yet implemented")
    }

}
