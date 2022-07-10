package com.android.study.buttontoaction.domain.entities

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
sealed class ActionInvokeData

class CallInvokeData(val activity: Activity) : ActionInvokeData()
class ToastInvokeData(val context: Context) : ActionInvokeData()
class AnimationInvokeData(val view: View) : ActionInvokeData()
class NotificationInvokeData(val appContext: Application,
                             val context: Context) : ActionInvokeData()