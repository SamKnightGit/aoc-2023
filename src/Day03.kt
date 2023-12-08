fun main() {
    fun part1(input: List<String>): Int {
        var cumsum = 0
        val specialCharacterIndices = HashSet<Pair<Int, Int>>()

        // First pass gathers indices of special characters
        for ((rowIdx, line) in input.withIndex()) {
            for ((colIdx, char) in line.withIndex()) {
                if (!char.isDigit() && char != '.') {
                    specialCharacterIndices.add(Pair(rowIdx, colIdx))
                }
            }
        }

        // Second pass calculates sum of numbers near special
        var currentParsedNumber = ""
        var nearSpecialCharacter = false
        for ((rowIdx, line) in input.withIndex()) {
            for ((colIdx, char) in line.withIndex()) {
                if (char.isDigit()) {
                    currentParsedNumber += char
                    for (i in rowIdx-1..rowIdx+1) {
                        for (j in colIdx-1..colIdx+1) {
                            val indexPair: Pair<Int, Int> = Pair(i, j)
                            if (specialCharacterIndices.contains(indexPair)) {
                                nearSpecialCharacter = true
                            }
                        }
                    }
                }
                else {
                    if (currentParsedNumber != "") {
                        if (nearSpecialCharacter) {
                            cumsum += currentParsedNumber.toInt()
                            currentParsedNumber = ""
                            nearSpecialCharacter = false
                        } else {
                            currentParsedNumber = ""
                        }
                    }
                }
            }
        }

        return cumsum
    }

    fun part2(input: List<String>): Int {
        var cumsum = 0
        val potentialGearLocations: MutableMap<Pair<Int, Int>, MutableList<Int>> = mutableMapOf()

        // First pass gathers potential gear locations
        for ((rowIdx, line) in input.withIndex()) {
            for ((colIdx, char) in line.withIndex()) {
                if (char == '*') {
                    potentialGearLocations[Pair(rowIdx, colIdx)] = mutableListOf()
                }
            }
        }

        // Second pass calculates gear associations
        var currentParsedNumber = ""
        var associatedGearLocations: MutableSet<Pair<Int, Int>> = mutableSetOf()
        for ((rowIdx, line) in input.withIndex()) {
            for ((colIdx, char) in line.withIndex()) {
                if (char.isDigit()) {
                    currentParsedNumber += char
                    for (i in rowIdx - 1..rowIdx + 1) {
                        for (j in colIdx - 1..colIdx + 1) {
                            val indexPair: Pair<Int, Int> = Pair(i, j)
                            if (potentialGearLocations.contains(indexPair)) {
                                associatedGearLocations.add(indexPair)
                            }
                        }
                    }
                } else {
                    if (currentParsedNumber != "") {
                        if (associatedGearLocations.size > 0) {
                            for (gearLocation in associatedGearLocations) {
                                potentialGearLocations[gearLocation]!!.add(currentParsedNumber.toInt())
                            }
                            associatedGearLocations = mutableSetOf()
                            currentParsedNumber = ""
                        } else {
                            currentParsedNumber = ""
                        }
                    }
                }
            }
        }

        // Find true gears (size 2) and get product
        for (gearNumber in potentialGearLocations.values) {
            if (gearNumber.size == 2) {
                cumsum += gearNumber.reduce { acc, num -> acc * num}
            }
        }
        return cumsum
    }

    val testInput = readInput("Day03_test")
    part1(testInput).println()
    val input = readInput("Day03")
    part1(input).println()

    val input2 = readInput("Day03")
    part2(testInput).println()
    part2(input2).println()
}
