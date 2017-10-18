package romanNumerals

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class RomanNumeralsTest : StringSpec() {
    init {

        "Roman numerals V, D or L can never be repeated" {
            forAll(NumberBetween0And3000()) { n: Int ->
                val translation = n.toRoman()

                translation.count { it == RomanNumeral.V } <= 1
                translation.count { it == RomanNumeral.D } <= 1
                translation.count { it == RomanNumeral.L } <= 1
            }
        }

    }

    class NumberBetween0And3000 : Gen<Int> {
        override fun generate(): Int = Gen.choose(0, 3001).generate()
    }
}