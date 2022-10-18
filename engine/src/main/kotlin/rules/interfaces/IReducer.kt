package rules.interfaces

import actionsData.Action
import rules.states.IState

interface IReducer {
    fun setState(state: IState, action: Action): IState
    fun validateAction(state: IState, action: Action): Boolean
    fun ignoreAction(action: Action)
}