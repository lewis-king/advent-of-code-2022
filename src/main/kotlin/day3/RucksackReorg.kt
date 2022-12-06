package day3

fun main() {
    val result = RucksackReorg.sumPrioritiesOfItemsInBothCompartments()
    println(result)
    val result2 = RucksackReorg.sumPrioritiesOfBadges()
    println(result2)
}

class RucksackReorg {
    companion object {
        private val input = this::class.java.getResource("/day3/input.txt")!!
            .readText()
            .split("\n")

        fun sumPrioritiesOfItemsInBothCompartments(): Int {
            return input.map { it.substring(0, it.length / 2) to it.substring(it.length / 2, it.length) }
                .map { it.first.toCharArray().intersect(it.second.toCharArray().asIterable().toSet()) }
                .sumOf { it.toCharArray().sumOf { determineScore(it) } }
        }

        private fun determineScore(it: Char) = if (it.code < 91) it.code - 38 else it.code - 96

        fun sumPrioritiesOfBadges(): Int {
            val badgeGroups = mutableListOf<List<CharArray>>()
            for (i in 3..input.size step 3) {
                badgeGroups.add(input.map { it.toCharArray() }.slice(i - 3 until i))
            }
            return badgeGroups.sumOf {
                it.asSequence().map { it.distinct() }
                    .reduce { acc, string -> acc + string }
                    .groupBy { it }
                    .filter { it.value.size == 3 }
                    .map { determineScore(it.key) }.first()
            }
        }
    }
}
