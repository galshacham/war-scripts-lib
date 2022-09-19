package comm.outer.objectsData

import enums.SoldierTypeEnum
import kotlinx.serialization.Serializable

@Serializable
class CastleData(val creatingSoldierType: SoldierTypeEnum) : ObjectDataInterface