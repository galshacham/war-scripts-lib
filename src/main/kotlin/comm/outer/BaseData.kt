package comm.outer

import enums.GameObjectsTypesEnums

@kotlinx.serialization.Serializable
data class BaseData(
    val id: Int,
    val owner: Int,
    val loc: LocData,
    val objectType: GameObjectsTypesEnums,
    val objectData: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseData

        if (id != other.id) return false
        if (owner != other.owner) return false
        if (loc != other.loc) return false
        if (objectType != other.objectType) return false
        if (!objectData.contentEquals(other.objectData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + owner
        result = 31 * result + loc.hashCode()
        result = 31 * result + objectType.hashCode()
        result = 31 * result + objectData.contentHashCode()
        return result
    }
}
