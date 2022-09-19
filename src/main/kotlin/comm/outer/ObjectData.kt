package comm.outer

import comm.outer.objectsData.ObjectDataInterface
import enums.GameObjectsTypesEnums
import kotlinx.serialization.serializer

@kotlinx.serialization.Serializable
data class ObjectData<ADDITIONAL_DATA : ObjectDataInterface>(
    val id: Int,
    val owner: Int,
    val loc: LocData,
    val objectType: GameObjectsTypesEnums,
    val objectData: ADDITIONAL_DATA
) {}