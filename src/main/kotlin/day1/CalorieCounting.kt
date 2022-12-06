package day1

fun main() {
    val result = CalorieCounting.maxCaloriesCarried()
    println(result)
    val result2 = CalorieCounting.maxCaloriesCarriedTop3()
    println(result2)
}

class CalorieCounting {
    companion object {
        private val input = this::class.java.getResource("/day1/input.txt")!!
            .readText()
            .split("\n\n")
            .map { it.split("\n") }

        fun maxCaloriesCarried(): Int = input.maxOf { it.sumOf { it.toInt() } }

        fun maxCaloriesCarriedTop3(): Int = input.map { it.sumOf { it.toInt() } }.sortedDescending().take(3).sum()
    }
}
