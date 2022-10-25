package rules.interfaces

import kotlin.reflect.KClass

interface IReducerOld {
    fun getActionType(): KClass<*>
}