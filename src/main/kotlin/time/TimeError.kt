package time

sealed class TimeError(val msg: String) : RuntimeException(msg) {
    class HourInvalidFormat : TimeError("Hours cannot be greater than 23")
    class MinuteInvalidFormat : TimeError("Minutes cannot be greater than 60")
    class EndTimeIsEqualOrPreviousToStartingTime : TimeError("Ending time cannot be previous or equal to starting time")
}