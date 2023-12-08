fun main() {
    fun part1(input: List<String>): Int {
        var cumsum = 0

        val maxBalls: Map<String, Int> = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        fileLoop@ for (line in input) {
            val gameIndex = line.split(": ")[0].split(" ")[1].toInt()
            val formattedLine = line.split(": ")[1]
            for (round in formattedLine.split("; ")) {
                for (ballPull in round.split(", ")) {
                    val (balls, colour) = ballPull.split(" ")
                    if (balls.toInt() > maxBalls[colour]!!) {
                        continue@fileLoop
                    }
                }
            }
            cumsum += gameIndex
        }

        return cumsum
    }

    fun part2(input: List<String>): Int {
        var cumsum = 0

        for (line in input) {
            val maxBalls: MutableMap<String, Int> = mutableMapOf(
                    "red" to 0,
                    "green" to 0,
                    "blue" to 0
            )
            val formattedLine = line.split(": ")[1]
            for (round in formattedLine.split("; ")) {
                for (ballPull in round.split(", ")) {
                    val (balls, colour) = ballPull.split(" ")
                    if (balls.toInt() > maxBalls[colour]!!) {
                        maxBalls[colour] = balls.toInt()
                    }
                }
            }
            cumsum += maxBalls.values
                    .filter { x -> x > 0 }
                    .reduce { acc, nextVal -> acc * nextVal }
        }

        return cumsum
    }

    val testInput = readInput("Day02_test")
    part1(testInput).println()
    val input = readInput("Day02")
    part1(input).println()

    val testInput2 = readInput("Day02_test2")
    part2(testInput2).println()
    part2(input).println()
}
