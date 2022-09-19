package bigComm.comm.outer.objectsData

import bigComm.enums.SoldierTypeEnum
import kotlinx.serialization.Serializable

@Serializable
class CastleData(val creatingSoldierType: SoldierTypeEnum) : ObjectDataInterface