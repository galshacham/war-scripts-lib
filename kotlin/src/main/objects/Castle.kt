package main.objects

import com.google.gson.Gson
import main.enums.SoldierType

data class Castle(val id: Int, val side: String, val loc: Location) {
    fun changeSoldierType(newType: SoldierType) {
        soldierType = newType
    }

    fun parseToJson(): String = Gson().toJson(this)

    var soldierType = SoldierType.MELEE
}
