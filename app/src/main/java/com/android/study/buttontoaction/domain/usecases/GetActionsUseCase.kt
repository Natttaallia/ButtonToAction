package com.android.study.buttontoaction.domain.usecases

import com.android.study.buttontoaction.domain.repositories.ActionsRepository

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class GetActionsUseCase(private val actionsRepository: ActionsRepository) {
    suspend fun invoke() = actionsRepository.getActions()
}