package romanNumerals

fun String.toArabic() : Int {
    if (this.containsNonRomanNumeralChar()) throw RomanNumeralsError.NonValidRomanNumeral()
    return toArabicInner(this, 0)
}

fun toArabicInner(roman: String, n: Int): Int {
    if (roman.isBlank()) return n
    val characters = roman.split("")
    if(characters.size == 1) return n + characters[0].to

}

fun String.containsNonRomanNumeralChar(): Boolean =
        !this.split("").all { it.isRomanNumeral() }