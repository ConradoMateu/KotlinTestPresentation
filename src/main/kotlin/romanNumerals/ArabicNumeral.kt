package romanNumerals

fun String.toArabic() : Int {
    if (this.containsNonRomanNumeralChar()) throw RomanNumeralsError.NonValidRomanNumeral()
    return toArabicInner(this, 0)
}

fun toArabicInner(roman: String, n: Int): Int {
    if (roman.isBlank()) return n
    return if (roman.length == 1) n + romanFromString(roman).value()
    else {
        val combinedRomans = roman.take(2)
        val leftRomans = roman.drop(2)
        if (combinedRomans.isRomanNumeral()) {
            toArabicInner(leftRomans, n + romanFromString(combinedRomans).value())
        } else {
            toArabicInner(roman.drop(1), n + romanFromString(roman.take(1)).value())
        }
    }

}

fun String.containsNonRomanNumeralChar(): Boolean =
        !this.toCharArray()
                .map(Char::toString)
                .all { it.isRomanNumeral() }