interface ITranslator {

    fun gameToBuffer(abstractGame: AbstractGame): ByteArray

    fun bufferToGame(buffer: ByteArray): AbstractGame

    fun bufferToActionList(buffer: ByteArray): List<AbstractAction>
}