package objectsData

import enums.ObjectTypeEnum
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@kotlinx.serialization.Serializable
data class RangedSoldier(
    override val id: Int,
    override var loc: Loc,
    override var owner: Int,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val type: ObjectTypeEnum = ObjectTypeEnum.RANGED,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val speed: Int = 4
) : Soldier() {
}