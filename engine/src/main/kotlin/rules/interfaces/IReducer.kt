package rules.interfaces

import kotlin.reflect.KClass

interface IReducer {
    fun getActionType(): KClass<*>
}