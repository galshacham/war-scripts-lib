import exceptions.UnsupportedSoldierTypeException
import main.enums.SoldierTypeEnum
import objects.Location
import objects.Soldier

class SoldierFactory(val mockIdGen: IdGenerator) {
    fun createSoldier(side: Int, loc: Location, type: SoldierTypeEnum): Soldier =
            when (type) {
                SoldierTypeEnum.MELEE -> Soldier(mockIdGen.next(), side, loc, type, 4, 2, 2, 1)
                SoldierTypeEnum.RANGED -> Soldier(mockIdGen.next(), side, loc, type, 1, 2, 2, 4)
                else -> throw UnsupportedSoldierTypeException("Soldier of type $type is not supported in the factory!")
            }
}