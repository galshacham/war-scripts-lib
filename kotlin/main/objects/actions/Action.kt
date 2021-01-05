package main.objects.actions

import exceptions.ActionValidationException
import main.Engine

abstract class Action(open val actionId: Int, open val side: Int) {

    abstract fun apply(engine: Engine)

    open fun validate(engine: Engine) {
        if (!engine.mapData.players.contains(side))
            throw ActionValidationException("Invalid action! player of side $side does not exist!")
    }
}