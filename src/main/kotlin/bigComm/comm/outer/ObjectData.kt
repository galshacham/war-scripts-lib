package bigComm.comm.outer

import bigComm.comm.outer.objectsData.ObjectDataInterface
import bigComm.enums.GameObjectsTypesEnums

@kotlinx.serialization.Serializable
data class ObjectData<ADDITIONAL_DATA : ObjectDataInterface>(
    val id: Int,
    val owner: Int,
    val loc: LocData,
    val objectType: GameObjectsTypesEnums,
    val objectData: ADDITIONAL_DATA
) {}