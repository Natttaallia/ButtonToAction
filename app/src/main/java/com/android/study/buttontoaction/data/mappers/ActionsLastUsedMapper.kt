package com.android.study.buttontoaction.data.mappers

import android.content.SharedPreferences
import com.android.study.buttontoaction.domain.entities.Action

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class ActionsLastUsedMapper(private val sharedPreferences: SharedPreferences) {
    fun addLastUsed(actions: List<Action>) : List<Action> {
        return actions.map {
            it.last_used = getLastUsed(it)
            it
        }
    }

    private fun getLastUsed(action: Action): Long {
        return sharedPreferences.getLong("${action.javaClass.name}_last_used", 0L)
    }
}