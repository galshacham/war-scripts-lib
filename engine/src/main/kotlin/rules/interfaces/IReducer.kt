package rules.interfaces

import actionsData.Action
import enums.ReducerEnum
import rules.states.IState

interface IReducer<STATE: IState> {
    fun setState(state: STATE, action: Action): STATE
    fun validateAction(state: STATE, action: Action): Boolean
    fun ignoreAction(action: Action)

    fun getReducerType(): ReducerEnum
}