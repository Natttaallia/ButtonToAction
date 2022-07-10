package com.android.study.buttontoaction.domain.entities

import java.util.*

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
abstract class Action(
    private val enabled: Boolean,
    val priority: Int,
    private val valid_days: Array<Int>,
    private val cool_down: Long,
    var last_used: Long = 0L
) {

    open fun checkConditions(): Boolean {
        return enabled && checkValidDays(valid_days) && checkCoolDown(cool_down, last_used)
    }

    private fun checkValidDays(validDays: Array<Int>): Boolean {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
        return validDays.contains(day)
    }

    private fun checkCoolDown(coolDown: Long, lastUsed: Long): Boolean {
        return Calendar.getInstance().timeInMillis > lastUsed + coolDown
    }

    abstract fun invoke(data: ActionInvokeData)

}