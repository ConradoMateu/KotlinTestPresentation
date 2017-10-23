package romanNumerals

import io.kotlintest.matchers.fail
import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.*
import io.kotlintest.specs.WordSpec

class RomanNumeralsTest : WordSpec() {
    init {

        "Roman numerals" should {

            "Get correct value from Arabic Number" {
                val table = table(
                        headers("Arabic Number", "Roman Numeral"),
                        row(49, "XLIX"),
                        row(1601, "MDCI"),
                        row(21, "XXI"),
                        row(1996, "MCMXCVI")
                )

                forAll(table) { num: Int, roman: String ->
                    num.toRoman().represent() shouldBe roman
                }
            }

            "V, D or L can never be repeated" {
                forAll(NumberBetween0And3000()) { n: Int ->
                    val translation = n.toRoman()

                    translation.count { it == RomanNumeral.V } <= 1
                    translation.count { it == RomanNumeral.D } <= 1
                    translation.count { it == RomanNumeral.L } <= 1
                }
            }

        }

        "Arabic numbers" should {

            "Be translated correctly to its Roman value" {
                val table = table(
                        headers("Roman Numeral", "Arabic Number"),
                        row("XXX", 30),
                        row("CD", 400),
                        row("VIII", 8),
                        row("MCXI", 1111),
                        row("MMXLVIII", 2048),
                        row("MMXVII", 2017)
                )

                forAll(table) { roman: String, arabic: Int ->
                    roman.toArabic() shouldBe arabic
                }
            }

            "Arabic to Roman and back should return the same value" {
                forAll(NumberBetween0And3000()) { n: Int ->
                    n.toRoman().represent().toArabic() == n
                }
            }

            "Arabic numbers greater than 0 always returns non empty strings" {
                forAll(Non0ArabicNumber()) { n: Int ->
                    n.toRoman().represent().isNotEmpty()
                }
            }

        }

        "Translations" should {

            "Translate from Arabic to Roman using negative should throw error" {
                forAll(NegativeNumber()) { n: Int ->
                    try {
                        n.toRoman()
                        fail("Exception should have been thrown")
                    } catch (err: Exception) {
                        err is RomanNumeralsError.NegativeNumbersNotAllowed
                    }
                }
            }

            "Translate from Arabic to Roman using values greater than 3000 should throw error" {
                forAll(NumberGreaterThan3000()) {
                    try {
                        it.toRoman()
                        fail("Exception should have been thrown")
                    } catch (err: Exception) {
                        err is RomanNumeralsError.NumbersGreaterThan3000NotAllowed
                    }
                }
            }

        }


    }

    class NumberBetween0And3000 : Gen<Int> {
        override fun generate(): Int = Gen.positiveIntegers().generate() % 3001
    }

    class Non0ArabicNumber : Gen<Int> {
        override fun generate(): Int = Gen.choose(1, 3001).generate()
    }

    class NegativeNumber : Gen<Int> {
        override fun generate() = Gen.negativeIntegers().generate()
    }

    class NumberGreaterThan3000 : Gen<Int> {
        override fun generate(): Int = Gen.positiveIntegers().generate() + 3001
    }
}