package com.example.hearthstoneapp.util.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Context.showAlert(message: String, onOkPressed: () -> Unit) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            onOkPressed()
            dialog.dismiss()
        }
    val alert = builder.create()
    alert.show()
}