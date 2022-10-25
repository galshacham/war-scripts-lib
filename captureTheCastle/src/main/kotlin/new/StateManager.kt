package new

import AbstractAction
import AbstractGame
import IStateManager

class StateManager : IStateManager {
    override fun setState(newAbstractGameState: AbstractGame, abstractAction: List<AbstractAction>): AbstractGame {
        TODO("Not yet implemented")
    }

    override fun postSetState(newAbstractGameState: AbstractGame): AbstractGame {
        TODO("Not yet implemented")
    }
}