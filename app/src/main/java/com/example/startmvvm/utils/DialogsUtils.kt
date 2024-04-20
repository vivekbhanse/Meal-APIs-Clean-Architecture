package com.example.startmvvm.utils

import android.app.ProgressDialog

import android.content.Context




class DialogsUtils {
    fun showProgressDialog(context: Context?, message: String?): ProgressDialog? {
        val m_Dialog = ProgressDialog(context)
        m_Dialog.setMessage(message)
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        m_Dialog.setCancelable(false)
        m_Dialog.show()
        return m_Dialog
    }
}