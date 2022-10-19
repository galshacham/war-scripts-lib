package rules.states

import objectsData.Game
import rules.interfaces.IReducer


// Maybe caching will be important
class StateManager {
    fun getState(game: Game, reducer: IReducer<IState>): IState {
//        reducer.javaClass.genericSuperclass
        TODO("Not yet implemented")
    }

    fun mergeState(stateList: List<IState>): Game {
        TODO("Not yet implemented")
    }

}
