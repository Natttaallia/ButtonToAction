package com.android.study.buttontoaction.data.entities

import android.widget.Toast
import com.android.study.buttontoaction.R
import com.android.study.buttontoaction.domain.entities.Action
import com.android.study.buttontoaction.domain.entities.ActionInvokeData
import com.android.study.buttontoaction.domain.entities.ToastInvokeData

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class Toast(
    enabled: Boolean,
    priority: Int,
    valid_days: Array<Int>,
    cool_down: Long
) : Action(enabled, priority, valid_days, cool_down) {

    override fun invoke(data: ActionInvokeData) {
        val context = (data as? ToastInvokeData)?.context
        context?.let {
            Toast.makeText(it, it.getString(R.string.action_is_toast), Toast.LENGTH_SHORT).show()
        }
    }
}