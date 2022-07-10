package com.android.study.buttontoaction.app.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.study.buttontoaction.R
import com.android.study.buttontoaction.data.entities.Animation
import com.android.study.buttontoaction.data.entities.Call
import com.android.study.buttontoaction.data.entities.Notification
import com.android.study.buttontoaction.domain.entities.*
import com.android.study.buttontoaction.domain.usecases.GetActionsUseCase
import com.android.study.buttontoaction.domain.usecases.GetAppropriateActionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class MainViewModel(
    private val getActionsUseCase: GetActionsUseCase,
    private val getActionUseCase: GetAppropriateActionUseCase,
    private val context: Context,
    private val application: Application
) : ViewModel() {

    private val actionsFlow: MutableStateFlow<List<Action>> = MutableStateFlow(listOf())

    private val _stateFlow: MutableStateFlow<State> = MutableStateFlow(State.LOADING)
    val stateFlow: StateFlow<State> = _stateFlow

    init {
        getActions()
    }

    private fun getActions() {
        viewModelScope.launch {
            _stateFlow.emit(State.LOADING)
            actionsFlow.emit(getActionsUseCase.invoke())
            _stateFlow.emit(State.LOADED)
        }
    }

    fun invokeAction(activity: Activity, view: View) {
        val action = getActionUseCase.getAction(actionsFlow.value)
        action?.invoke(getAppropriateAction(action, activity, view))
            ?: Toast.makeText(context, context.getString(R.string.action_error), Toast.LENGTH_SHORT)
                .show()
    }

    private fun getAppropriateAction(
        action: Action,
        activity: Activity,
        view: View
    ): ActionInvokeData {
        return when (action) {
            is Call -> CallInvokeData(activity)
            is Animation -> AnimationInvokeData(view)
            is Notification -> NotificationInvokeData(application, context)
            else -> ToastInvokeData(context)
        }
    }

    companion object {
        enum class State {
            LOADING, LOADED
        }
    }
}