package time

import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.WordSpec
import time.Time
import time.TimeRange

class TimeTest : WordSpec({

    "time.Time" should {

        "throw an exception if Hour is greater than 23" {
            shouldThrow<TimeError.HourInvalidFormat> {
                Time.TimeBuilder {
                    hour { 24 }
                    minute { 30 }
                }.build()
            }
        }

        "throw an exception if Minute is greater than 59" {
            shouldThrow<TimeError.MinuteInvalidFormat> {
                Time.TimeBuilder {
                    hour { 12 }
                    minute { 60 }
                }.build()
            }
        }

    }

    "time.TimeRange" should {

        "throw an exception if Ending hour is before Staring hour" {
            shouldThrow<TimeError.EndTimeIsEqualOrPreviousToStartingTime> {
                TimeRange.TimeRangeBuilder {
                    from {
                        Time.TimeBuilder {
                            hour { 23 }
                            minute { 59 }
                        }.build()
                    }

                    until {
                        Time.TimeBuilder {
                            hour { 10 }
                            minute { 0 }
                        }.build()
                    }.build()
                }
            }
        }

        "throw an exception if Ending hour is equal to Staring hour" {
            shouldThrow<TimeError.EndTimeIsEqualOrPreviousToStartingTime> {
                TimeRange.TimeRangeBuilder {
                    from {
                        Time.TimeBuilder {
                            hour { 23 }
                            minute { 59 }
                        }.build()
                    }

                    until {
                        Time.TimeBuilder {
                            hour { 23 }
                            minute { 59 }
                        }.build()
                    }.build()
                }
            }
        }

    }


})