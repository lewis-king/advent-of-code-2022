package day4

fun main() {
    val result = CampCleanup.completeOverlappingCleanups()
    println(result)
    val result2 = CampCleanup.partialOverlappingCleanups()
    println(result2)
}

class CampCleanup {
    companion object {
        private val input = this::class.java.getResource("/day4/input.txt")!!
            .readText()
            .split("\n")
            .map { it.split(",")[0] to it.split(",")[1] }

        fun completeOverlappingCleanups(): Int {
            return input.map {
                (it.first.split("-")[0].toInt() to it.first.split("-")[1].toInt()) to
                    (it.second.split("-")[0].toInt() to it.second.split("-")[1].toInt())
            }.count { pairs ->
                (pairs.first.first <= pairs.second.first && pairs.first.second >= pairs.second.second) ||
                    (pairs.second.first <= pairs.first.first && pairs.second.second >= pairs.first.second)
            }
        }

        fun partialOverlappingCleanups(): Int {
            return input.map {
                (it.first.split("-")[0].toInt() to it.first.split("-")[1].toInt()) to
                    (it.second.split("-")[0].toInt() to it.second.split("-")[1].toInt())
            }
                .count { pairs ->
                    (pairs.first.first >= pairs.second.first && pairs.first.first <= pairs.second.second) ||
                        (pairs.second.first >= pairs.first.first && pairs.second.first <= pairs.first.second)
                }
        }
    }
}
