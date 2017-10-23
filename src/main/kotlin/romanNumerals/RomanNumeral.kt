package romanNumerals

enum class RomanNumeral {
    I, IV, V, IX, X, XL, L, XC, C, CD, D, CM, M
}

sealed class RomanNumeralsError(msg: String) : Exception(msg) {
    class NegativeNumbersNotAllowed : RomanNumeralsError("Negative numbers are not allowed")
    class NumbersGreaterThan3000NotAllowed : RomanNumeralsError("Numbers greater than 3000 not allowed")
    class NonValidRomanNumeral : RomanNumeralsError("Not valid format for Roman Numeral")
}

fun String.isRomanNumeral(): Boolean = stringRomanNumerals().contains(this)

fun Int.toRoman() : List<RomanNumeral> {
    if (this < 0) throw RomanNumeralsError.NegativeNumbersNotAllowed()
    if (this > 3000) throw RomanNumeralsError.NumbersGreaterThan3000NotAllowed()
    return toRomanInner(this, emptyList())
}

fun toRomanInner(n: Int, acc: List<RomanNumeral>) : List<RomanNumeral> {
    if (n == 0) return acc
    val closerRoman = extractCloserRoman(n)
    val closerRomanValue = closerRoman.value()

    return toRomanInner(n - closerRomanValue, acc + closerRoman)
}

fun extractCloserRoman(n: Int): RomanNumeral =
    romanNumerals().takeWhile { canExtract(it, n) }.last()

fun canExtract(roman: RomanNumeral, n: Int): Boolean = n - roman.value() >= 0

fun romanNumerals() = listOf(
        RomanNumeral.I,
        RomanNumeral.IV,
        RomanNumeral.V,
        RomanNumeral.IX,
        RomanNumeral.X,
        RomanNumeral.XL,
        RomanNumeral.L,
        RomanNumeral.XC,
        RomanNumeral.C,
        RomanNumeral.CD,
        RomanNumeral.D,
        RomanNumeral.CM,
        RomanNumeral.M
)

fun romanFromString(s: String): RomanNumeral = when (s.toUpperCase()) {
    "I" -> RomanNumeral.I
    "IV" -> RomanNumeral.IV
    "V" -> RomanNumeral.V
    "IX" -> RomanNumeral.IX
    "X" -> RomanNumeral.X
    "XL" -> RomanNumeral.XL
    "L" -> RomanNumeral.L
    "XC" -> RomanNumeral.XC
    "C" -> RomanNumeral.C
    "CD" -> RomanNumeral.CD
    "D" -> RomanNumeral.D
    "CM" -> RomanNumeral.CM
    "M" -> RomanNumeral.M
    else -> throw RomanNumeralsError.NonValidRomanNumeral()
}

fun stringRomanNumerals() : List<String> =
        romanNumerals().map { it.name }

fun List<RomanNumeral>.represent() = this.joinToString(separator = "") { it.name }

fun RomanNumeral.value(): Int = when (this) {
    RomanNumeral.I  -> 1
    RomanNumeral.IV -> 4
    RomanNumeral.V  -> 5
    RomanNumeral.IX -> 9
    RomanNumeral.X  -> 10
    RomanNumeral.XL -> 40
    RomanNumeral.L  -> 50
    RomanNumeral.XC -> 90
    RomanNumeral.C  -> 100
    RomanNumeral.CD -> 400
    RomanNumeral.D  -> 500
    RomanNumeral.CM -> 900
    RomanNumeral.M  -> 1000
}

