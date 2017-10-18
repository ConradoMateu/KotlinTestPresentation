import io.kotlintest.forAll
import io.kotlintest.forAny
import io.kotlintest.forAtLeast
import io.kotlintest.matchers.*
import io.kotlintest.specs.StringSpec

/**
 * Created by ConradoMateu on 18/10/2017.
 */
class KotlinTest : StringSpec() {
    init {
        val animals = listOf(
                Animal("🐤",false),
                Animal("🐔", false),
                Animal("🦅",true),
                Animal("🐖",false),
                Animal("🐲",true)

        )
        val cookedAnimals: List<Animal> = animals.map { it.cook() }
        val flyingAnimalsSleeping: List<Animal> = animals.map{if (it.canFly){it.sleep()}else it}

        "At least one animal should cross the river"{
            forAny(animals){
                it.canFly shouldBe(true)
            }
        }

        "At least three animals should cross the river"{
            forAtLeast(3,animals){
                it.canFly shouldBe(true)
            }
        }

        "All animals should be cooked"{
            forAll(cookedAnimals){
                it.animal should match("🍗")}
        }

        //With Costum matcher

        "All animals should be cooked"{
            forAll(cookedAnimals){
                it.animal should beChicken()
            }
        }

        "All animals that can fly should be sleeping"{
            val animalsSleeping = flyingAnimalsSleeping.filter { it.canFly }
            val animalsAwake = flyingAnimalsSleeping.filter { !it.canFly }

            forAll(animalsSleeping) {
                it.canFly shouldBe(true)
                it.animal should contain("💤")

            }

            forAll(animalsAwake) {
                it.canFly shouldBe(false)
                it.animal shouldNot contain("💤")

            }
        }


    }

    fun contain(substr: String) = object : Matcher<String> {
        override fun test(value: String) = Result(value.contains(substr), "String $value should include substring $substr")
    }

    fun beChicken(): Matcher<String> = object : Matcher<String> {
        override fun test(value: String) = Result(value.matches("🍗".toRegex()), "String $value should be 🍗")
    }

}