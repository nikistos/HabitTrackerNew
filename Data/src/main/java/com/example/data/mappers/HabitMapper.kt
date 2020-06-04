package com.example.data.mappers

import com.example.data.models.*
import com.example.domain.models.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitMapper @Inject constructor() {

    fun entityToNetworkModel(habit: HabitEntity): NetworkHabit {
        with(habit) {
            return NetworkHabit(
                uid = if (this.uid.isEmpty()) null else this.uid,
                title = this.title,
                date = this.date,
                description = this.description,
                priority = PriorityConverter().fromEnum(this.priority),
                type = TypeHabitConverter().fromEnum(this.type),
                color = this.color,
                count = this.count,
                frequency = when (this.frequency) {
                    Frequecy.Day -> 1
                    Frequecy.Week -> 7
                    Frequecy.Month -> 30
                },
                done_dates = this.done_dates.toTypedArray()
            )
        }
    }

    fun networkToEntityModel(habit: NetworkHabit): HabitEntity {
        with(habit) {
            return HabitEntity(
                uid = this.uid ?: "",
                title = this.title,
                date = this.date,
                description = this.description,
                priority = PriorityConverter().toEnum(this.priority),
                type = TypeHabitConverter().toEnum(this.type),
                count = this.count,
                color = this.color,
                frequency = when (this.frequency) {
                    30 -> Frequecy.Month
                    7 -> Frequecy.Week
                    else -> Frequecy.Day
                },
                done_dates = this.done_dates.toMutableList()
            )
        }
    }

    fun networkToDomainModel(habit: NetworkHabit): DomainHabit {
        with(habit) {
            return DomainHabit(
                uid = this.uid ?: "",
                title = this.title,
                date = this.date,
                description = this.description,
                priority = PriorityConverter().toEnum(this.priority),
                type = TypeHabitConverter().toEnum(this.type),
                count = this.count,
                color = this.color,
                frequency = when (this.frequency) {
                    30 -> Frequecy.Month
                    7 -> Frequecy.Week
                    else -> Frequecy.Day
                },
                done_dates = this.done_dates.toMutableList()
            )
        }
    }

    fun domainToNetworkModel(habit: DomainHabit): NetworkHabit {
        with(habit) {
            return NetworkHabit(
                uid = if (this.uid.isEmpty()) null else this.uid,
                title = this.title,
                date = this.date,
                description = this.description,
                priority = PriorityConverter().fromEnum(this.priority),
                type = TypeHabitConverter().fromEnum(this.type),
                color = this.color,
                count = this.count,
                frequency = when (this.frequency) {
                    Frequecy.Day -> 1
                    Frequecy.Week -> 7
                    Frequecy.Month -> 30
                },
                done_dates = this.done_dates.toTypedArray()
            )
        }
    }

    fun entityToDomainModel(habit: HabitEntity): DomainHabit {
        with(habit) {
            return DomainHabit(
                uid = this.uid,
                title = this.title,
                date = this.date,
                description = this.description,
                priority = this.priority,
                type = this.type,
                count = this.count,
                color = this.color,
                frequency = this.frequency,
                done_dates = this.done_dates
            )
        }
    }

    fun domainToEntity(habit: DomainHabit): HabitEntity {
        with(habit) {
            return HabitEntity(
                uid = this.uid,
                title = this.title,
                date = this.date,
                description = this.description,
                priority = this.priority,
                type = this.type,
                count = this.count,
                color = this.color,
                frequency = this.frequency,
                done_dates = this.done_dates
            )
        }
    }

}