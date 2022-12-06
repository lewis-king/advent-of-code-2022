package day2

fun main() {
    val result = RockPaperScissors.scoreStrategyGuide()
    println(result)
    val result2 = RockPaperScissors.scoreStrategyGuide2()
    println(result2)
}

// TODO: Refactor
class RockPaperScissors {
    companion object {
        private val likeForLike = mapOf("A" to "X", "B" to "Y", "C" to "Z")
        private val scores = mapOf("A" to 1, "X" to 1, "B" to 2, "Y" to 2, "C" to 3, "Z" to 3)
        private val beats = mapOf("A" to "Y", "B" to "Z", "C" to "X")
        private val loses = mapOf("A" to "Z", "B" to "X", "C" to "Y")

        private val input = this::class.java.getResource("/day2/input.txt")!!
            .readText()
            .split("\n")
            .map { Pair(it.split(" ")[0], it.split(" ")[1]) }

        fun scoreStrategyGuide(): Int = input.sumOf { determineRoundPoints(it) }

        fun scoreStrategyGuide2(): Int = input.map { determineMove(it) }.sumOf { determineRoundPoints(it) }

        private fun determineMove(play: Pair<String, String>): Pair<String, String> {
            return when (play.second) {
                "Y" -> return play.first to likeForLike[play.first]!!
                "X" -> play.first to loses[play.first]!!
                "Z" -> play.first to beats[play.first]!!
                else -> play.first to play.first
            }
        }

        // TODO: Refactor
        private fun determineRoundPoints(play: Pair<String, String>): Int {
            if ((play.first == "A" && play.second == "X") || (play.first == "B" && play.second == "Y") || (play.first == "C" && play.second == "Z")) {
                return 3 + scores[play.second]!!
            }
            when (play.first) {
                "A" -> {
                    if (play.second == "Y") return 6 + scores[play.second]!!
                    if (play.second == "Z") return 0 + scores[play.second]!!
                }
                "B" -> {
                    if (play.second == "X") return 0 + scores[play.second]!!
                    if (play.second == "Z") return 6 + scores[play.second]!!
                }
                "C" -> {
                    if (play.second == "X") return 6 + scores[play.second]!!
                    if (play.second == "Y") return 0 + scores[play.second]!!
                }
            }
            return 0
        }
    }
}
