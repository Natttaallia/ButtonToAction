package com.android.study.buttontoaction.domain.usecases

import android.content.SharedPreferences
import com.android.study.buttontoaction.data.mappers.ActionsLastUsedMapper
import com.android.study.buttontoaction.domain.entities.Action
import java.util.*

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class GetAppropriateActionUseCase(
    private val sharedPreferences: SharedPreferences.Editor,
    private val mappers: ActionsLastUsedMapper
) {

    fun getAction(actions: List<Action>): Action? {
        val action = mappers.addLastUsed(actions.sortedBy { it.priority }).firstOrNull { it.checkConditions() }
        action?.let {
            saveActionLastUsed(action)
        }
        return action
    }

    private fun saveActionLastUsed(action: Action) {
        with(sharedPreferences) {
            putLong("${action.javaClass.name}_last_used", Calendar.getInstance().timeInMillis)
            apply()
        }
    }

}