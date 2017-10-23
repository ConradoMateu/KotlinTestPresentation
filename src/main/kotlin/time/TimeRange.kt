package time

data class TimeRange private constructor(val from: Time, val until: Time) {

    companion object {
        fun create(init: TimeRangeBuilder.() -> Unit) = TimeRangeBuilder(init).build()
    }

    private constructor(builder: TimeRangeBuilder) : this(builder.from, builder.until)

    class TimeRangeBuilder private constructor() {
        constructor(init: TimeRangeBuilder.() -> Unit) : this() {
            init()
        }

        lateinit var from: Time
        lateinit var until: Time

        fun from(init: TimeRangeBuilder.() -> Time) = apply {
            this.from = init()
        }

        fun until(init: TimeRangeBuilder.() -> Time) = apply {
            val time = init()
            if (time.isAfter(this.from)) this.until = time
            else throw TimeError.EndTimeIsEqualOrPreviousToStartingTime()
        }

        fun build() = TimeRange(this)
    }
}

