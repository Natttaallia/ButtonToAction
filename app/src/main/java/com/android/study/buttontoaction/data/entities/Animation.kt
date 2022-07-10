package com.android.study.buttontoaction.data.entities

import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.android.study.buttontoaction.domain.entities.Action
import com.android.study.buttontoaction.domain.entities.ActionInvokeData
import com.android.study.buttontoaction.domain.entities.AnimationInvokeData

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class Animation(
    enabled: Boolean,
    priority: Int,
    valid_days: Array<Int>,
    cool_down: Long
) : Action(enabled, priority, valid_days, cool_down) {

    override fun invoke(data: ActionInvokeData) {
        val rotate = RotateAnimation(
            0f,
            360f,
            RELATIVE_TO_SELF,
            0.5f,
            RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 2000
        rotate.interpolator = LinearInterpolator()
        (data as? AnimationInvokeData)?.view?.startAnimation(rotate)
    }
}