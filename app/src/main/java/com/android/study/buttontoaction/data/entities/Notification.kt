package com.android.study.buttontoaction.data.entities

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.provider.ContactsContract
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.study.buttontoaction.R
import com.android.study.buttontoaction.domain.entities.Action
import com.android.study.buttontoaction.domain.entities.ActionInvokeData
import com.android.study.buttontoaction.domain.entities.NotificationInvokeData

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class Notification(
    enabled: Boolean,
    priority: Int,
    valid_days: Array<Int>,
    cool_down: Long
) : Action(enabled, priority, valid_days, cool_down) {
    val CHANNEL_ID = "button_to_action_channel"
    val NOTIFICATION_ID = 12345

    override fun invoke(data: ActionInvokeData) {
        (data as? NotificationInvokeData)?.let {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(it.appContext, Call.CALL_REQUEST_CODE, intent,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT }
                    else { PendingIntent.FLAG_UPDATE_CURRENT })
            val builder = NotificationCompat.Builder(it.appContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(it.context.getString(R.string.notification_text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
            with(NotificationManagerCompat.from(it.appContext)) {
                notify(NOTIFICATION_ID, builder.build())
            }
        }
    }
}