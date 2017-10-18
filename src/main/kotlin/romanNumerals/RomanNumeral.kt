package romanNumerals

enum class RomanNumeral {
    I, IV, V, IX, X, XL, L, XC, C, CD, D, CM, M
}

sealed class RomanNumeralsError(msg: String) : RuntimeException(msg) {
    class NegativeNumbersNotAllowed : RomanNumeralsError("Negative numbers are not allowrd")
}

fun Int.toRoman() : List<RomanNumeral> {
    if (this < 0) throw RomanNumeralsError.NegativeNumbersNotAllowed()
}
