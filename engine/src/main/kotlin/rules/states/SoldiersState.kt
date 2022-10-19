package rules.states

import objectsData.Soldier

data class SoldiersState(val state: Map<Int, Soldier>): IState {
    override fun getState(): Any {
        return state
    }

}