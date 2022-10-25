package rules.reducers

import actionsData.Action
import enums.ReducerEnum
import objectsData.Soldier
import rules.interfaces.IReducer
import rules.states.SoldiersState

class SoldiersReducer : IReducer<SoldiersState> {
    override fun setState(state: SoldiersState, action: Action): SoldiersState {
        TODO("Not yet implemented")
    }

    override fun validateAction(state: SoldiersState, action: Action): Boolean {
        TODO("Not yet implemented")
    }

    override fun ignoreAction(action: Action) {
        TODO("Not yet implemented")
    }


    override fun getReducerType(): ReducerEnum = ReducerEnum.SOLDIERS
}