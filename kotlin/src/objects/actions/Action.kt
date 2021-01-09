package main.objects.actions

import Engine
import exceptions.ActionValidationException

interface Action {
    val actionId: String
    val side: Int

    fun apply(engine: Engine)

    fun validate(engine: Engine) {
        if (!engine.mapData.players.contains(side))
            throw ActionValidationException("Invalid action! player of side $side does not exist!")
    }
}