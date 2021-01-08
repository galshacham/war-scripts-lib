package main.objects.actions

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import main.enums.ActionTypeEnum
import main.enums.SoldierTypeEnum
import main.exceptions.NoSuchActionException
import java.lang.reflect.Type

val ACTION_ID = "actionId"
val SIDE = "side"
val AFFECTED_ID = "affectedId"
val SOLDIER_TYPE = "soldierType"

class ActionDeserializer : JsonDeserializer<Action> {
    override fun deserialize(element: JsonElement, action: Type, gson: JsonDeserializationContext): Action {
        val jsonObject = (element as JsonObject)
        val actionType: ActionTypeEnum = getActionType(jsonObject)

        val actionId = jsonObject[ACTION_ID].asInt
        val side = jsonObject[SIDE].asInt

        when (actionType) {
            ActionTypeEnum.CHANGE_SOLDIER_TYPE -> {
                val affectedId = jsonObject[AFFECTED_ID].asInt
                val soldierType = jsonObject[SOLDIER_TYPE].asString

                return ChangeSoldierTypeAction(actionId, side, affectedId, SoldierTypeEnum.valueOf(soldierType))
            }
        }


    }

    private fun getActionType(jsonObject: JsonObject): ActionTypeEnum {
        var actionType: ActionTypeEnum
        try {
            actionType = ActionTypeEnum.valueOf(jsonObject["actionType"].asString)

        } catch (e: IllegalArgumentException) {
            throw NoSuchActionException("The action you are trying to create is of enum type ${jsonObject["actionType"]} which does not exist in ActionTypeEnum")
        }

        return actionType
    }
}