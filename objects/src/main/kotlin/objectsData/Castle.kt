package objectsData

import enums.ObjectTypeEnum
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@kotlinx.serialization.Serializable
data class Castle(
    override val id: Int,
    override var loc: Loc,
    val soldierType: ObjectTypeEnum,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val type: ObjectTypeEnum = ObjectTypeEnum.CASTLE
) : GameObject()