package com.android.study.buttontoaction.domain.repositories

import com.android.study.buttontoaction.domain.entities.Action

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
interface ActionsRepository {
    suspend fun getActions(): List<Action>
}