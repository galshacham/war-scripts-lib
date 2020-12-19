package main.objects.actions

data class ChangeSoldierTypeAction(val actionId: Int, val side: String, val affectedId: Int, val soldierType: String) :
        Action(actionId, side)