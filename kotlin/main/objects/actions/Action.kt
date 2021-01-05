package main.objects.actions

import main.Engine

abstract class Action(actionId: Int, side: String) {
    abstract fun apply(engine: Engine)
    abstract fun validate(engine: Engine)
}