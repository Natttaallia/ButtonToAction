package com.android.study.buttontoaction.data.repositories

import com.android.study.buttontoaction.data.api.ActionsApi
import com.android.study.buttontoaction.data.mappers.ActionsLastUsedMapper
import com.android.study.buttontoaction.domain.entities.Action
import com.android.study.buttontoaction.domain.repositories.ActionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class ActionsRepositoryImpl(private val service: ActionsApi, private val mapper: ActionsLastUsedMapper) : ActionsRepository {

    override suspend fun getActions(): List<Action> =
        withContext(Dispatchers.IO) {
            return@withContext mapper.addLastUsed(service.getActions())
        }

}