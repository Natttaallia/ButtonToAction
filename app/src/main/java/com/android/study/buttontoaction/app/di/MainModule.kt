package com.android.study.buttontoaction.app.di

import com.android.study.buttontoaction.app.ui.MainViewModel
import com.android.study.buttontoaction.data.repositories.ActionsRepositoryImpl
import com.android.study.buttontoaction.domain.usecases.GetActionsUseCase
import com.android.study.buttontoaction.domain.usecases.GetAppropriateActionUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
val mainModule = module {
    factory { GetActionsUseCase(ActionsRepositoryImpl(get(), get())) }
    factory { GetAppropriateActionUseCase(get(), get()) }
    viewModel { MainViewModel(get(), get(), androidContext(), androidApplication()) }
}