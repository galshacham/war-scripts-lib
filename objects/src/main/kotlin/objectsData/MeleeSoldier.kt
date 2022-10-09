package objectsData

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@kotlinx.serialization.Serializable
data class MeleeSoldier(
    override val id: Int,
    override var loc: Loc,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val speed: Int = 4,
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) override val type: String = "MELEE"
) : Soldier()