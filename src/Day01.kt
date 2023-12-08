import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var cumsum = 0

        for (line in input) {
            var lineSum = 0
            var firstCharFound = false
            var lastCharValue = 0
            for (char in line) {
                if (char.isDigit()) {
                    if (!firstCharFound) {
                        lineSum += 10 * char.digitToInt()
                        firstCharFound = true
                    }
                    lastCharValue = char.digitToInt()
                }
            }
            lineSum += lastCharValue
            cumsum += lineSum
        }
        return cumsum
    }

    fun part2(input: List<String>): Int {
        var cumsum = 0
        val numberTextToValue: Map<String, Int> = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )


        for (line in input) {
            var lineSum = 0
            var firstCharFound = false
            var lastCharValue = 0
            for ((idx, char) in line.withIndex()) {
                if (char.isDigit()) {
                    if (!firstCharFound) {
                        lineSum += 10 * char.digitToInt()
                        firstCharFound = true
                    }
                    lastCharValue = char.digitToInt()
                } else {
                    val relevantSlice: String = line.substring(
                        idx,
                        min(idx + 5, line.length)
                    )
                    for (entry in numberTextToValue.entries) {
                        val indexOfKey = relevantSlice.indexOf(entry.key)
                        if (indexOfKey == 0) {
                            if (!firstCharFound) {
                                lineSum += 10 * entry.value
                                firstCharFound = true
                            }
                            lastCharValue = entry.value
                            break
                        }
                    }
                }
            }
            lineSum += lastCharValue
            cumsum += lineSum
        }
        return cumsum
    }

    val testInput = readInput("Day01_test")
    part1(testInput).println()
    val input = readInput("Day01")
    part1(input).println()

    val testInput2 = readInput("Day01_test2")
    part2(testInput2).println()
    part2(input).println()
}
