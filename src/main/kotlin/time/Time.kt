package time

import java.lang.Math.abs

data class Time private constructor(val hour: Int, val minute: Int) {

    private constructor(builder: TimeBuilder) : this(builder.hour, builder.minute)

    class TimeBuilder private constructor() {
        constructor(init: TimeBuilder.() -> Unit) : this() {
            init()
        }

        var hour: Int = 0
        var minute: Int = 0

        fun hour(init: TimeBuilder.() -> Int) = apply {
            val hour = abs(init())
            if (hour > 23) throw TimeError.HourInvalidFormat()
            else this.hour = hour
        }

        fun minute(init: TimeBuilder.() -> Int) = apply {
            val minute = abs(init())
            if (minute > 59) throw TimeError.MinuteInvalidFormat()
            else this.minute = minute
        }

        fun build() = Time(this)
    }

    fun isAfter(time: Time): Boolean {
        if (this.hour > time.hour) {
            return true
        }
        if (this.hour == time.hour && this.minute > time.minute) {
            return true
        }
        return false
    }

}