package objectsData

import enums.SoldierTypeEnum
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@kotlinx.serialization.Serializable
data class Castle(
    override val id: Int,
    override var loc: Loc,
    val soldierType: SoldierTypeEnum,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val type: String = "CASTLE"
) : GameObject()