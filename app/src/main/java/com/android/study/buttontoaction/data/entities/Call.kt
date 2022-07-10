package com.android.study.buttontoaction.data.entities

import android.content.Intent
import android.provider.ContactsContract
import com.android.study.buttontoaction.domain.entities.Action
import com.android.study.buttontoaction.domain.entities.ActionInvokeData
import com.android.study.buttontoaction.domain.entities.CallInvokeData


/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class Call(
    enabled: Boolean,
    priority: Int,
    valid_days: Array<Int>,
    cool_down: Long
) : Action(enabled, priority, valid_days, cool_down) {
    override fun invoke(data: ActionInvokeData) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE
        (data as? CallInvokeData)?.activity?.startActivityForResult(intent, CALL_REQUEST_CODE)
    }

    companion object {
        const val CALL_REQUEST_CODE = 10011
    }
}