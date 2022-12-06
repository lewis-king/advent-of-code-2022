package day6

fun main() {
    val result = TuningTrouble.charsBeforeStartOfPacket(4)
    println(result)
    val result2 = TuningTrouble.charsBeforeStartOfPacket(14)
    println(result2)
}

class TuningTrouble {
    companion object {
        private val input = this::class.java.getResource("/day6/input.txt")!!.readText()

        fun charsBeforeStartOfPacket(distinctWindow: Int) = (distinctWindow..input.toCharArray().size).find {
            input.substring(it - distinctWindow, it).toSet().size == distinctWindow
        }
    }
}
