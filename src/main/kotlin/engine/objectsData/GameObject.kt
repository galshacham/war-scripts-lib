package engine.objectsData

@kotlinx.serialization.Serializable
abstract class GameObject() {
    abstract val id: Int
    abstract var loc: Loc
//    override fun toJson(): String {
//
//        val clazz = this::class
//        clazz.members.map {
//            if (true)
//                "${it.name} --[${it.returnType}]---> ${it.call(this)}")
//
//                5
//        }
//
//        return ""
//    }

//    @Suppress("UNCHECKED_CAST")
//    fun <R> readInstanceProperty(instance: GameObject, propertyName: String): R {
//        val property = instance::class.members
//            .first { it.name == propertyName }
//        return property.get(instance)
//    }
}


