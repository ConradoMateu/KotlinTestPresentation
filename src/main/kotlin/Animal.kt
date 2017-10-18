
data class Animal(val animal: String, val canFly: Boolean){


    fun cook(): Animal{
        return this.copy(animal = "🍗")
    }

    fun sleep(): Animal{
        return this.copy(animal = "💤")
    }
}