package day5

import java.util.*

fun main() {
    val result = SupplyStacks.getTopCratesFromEachStack(false)
    println(result)
    val result2 = SupplyStacks.getTopCratesFromEachStack(true)
    println(result2)
}

// TODO: Refactor
class SupplyStacks {
    companion object {
        private val input = this::class.java.getResource("/day5/input.txt")!!
            .readText()
            .split("\n")

        // TODO: parsing crate info fun - refactor
        private fun initialisedCrates(): Map<Int, Stack<Char>> {
            val crates = mutableMapOf<Int, Stack<Char>>()
            (1..9).forEach {
                crates[it] = Stack()
            }
            input.take(8).reversed().forEach { str ->
                sequenceOf(1 to 1, 2 to 5, 3 to 9, 4 to 13, 5 to 17, 6 to 21, 7 to 25, 8 to 29, 9 to 33).forEach {
                    try {
                        if (str[it.second] != ' ') crates[it.first]!!.add(str[it.second])
                    } catch (_: Exception) {}
                }
            }
            return crates
        }

        // TODO: Please refactor me :(
        fun getTopCratesFromEachStack(crateMover9001: Boolean): String {
            val crates = initialisedCrates()

            input.drop(10).forEach {
                var nOfCrates = getValue(it, 1)
                val from = getValue(it, 3)
                val to = getValue(it, 5)
                val temp = mutableListOf<Char>()

                while (nOfCrates > 0) {
                    if (crates[from]!!.isNotEmpty()) {
                        if (crateMover9001) {
                            temp.add(crates[from]!!.pop())
                        } else {
                            crates[to]!!.push(crates[from]!!.pop())
                        }
                    }
                    nOfCrates--
                }
                temp.reversed().forEach { crates[to]!!.push(it) }
            }
            return (1..9).joinToString("") {
                crates[it]!!.peek().toString()
            }
        }

        private fun getValue(str: String, index: Int) = str.split(" ")[index].toInt()
    }
}
