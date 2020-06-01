package com.example.domain.models

class DomainHabit(
    var uid: String = "",
    var title: String = "",
    var date: Int = 0,
    var description: String = "",
    var frequency: Frequecy = Frequecy.Day,
    var priority: HabitPriority = HabitPriority.Low,
    var type: HabitType = HabitType.Good,
    var count: Int = 0,
    var color: Int = 0
)


