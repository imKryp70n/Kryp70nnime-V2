package com.imkryp70n.kryp70nnime.di

import android.app.Activity
import android.content.Context
import cn.pedant.SweetAlert.SweetAlertDialog
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType

object AesteticDialog {
    fun toasterSuccess(
        context: Context,
        isDarkMode: Boolean,
        duration: Int,
        title: String,
        message: String
    ): AestheticDialog {
        return AestheticDialog.Builder(context as Activity, DialogStyle.TOASTER, DialogType.SUCCESS)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setDarkMode(isDarkMode)
            .setDuration(duration)
            .show()
    }

    fun toasterError(
        context: Context,
        isDarkMode: Boolean,
        duration: Int,
        title: String,
        message: String
    ): AestheticDialog {
        return AestheticDialog.Builder(context as Activity, DialogStyle.TOASTER, DialogType.ERROR)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setDarkMode(isDarkMode)
            .setDuration(duration)
            .show()
    }

    // Warning Toaster
    fun toasterWarning(
        context: Context,
        isDarkMode: Boolean,
        duration: Int,
        title: String,
        message: String
    ): AestheticDialog {
        return AestheticDialog.Builder(context as Activity, DialogStyle.TOASTER, DialogType.WARNING)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setDarkMode(isDarkMode)
            .setDuration(duration)
            .show()
    }

    // Info Toaster
    fun toasterInfo(
        context: Context,
        isDarkMode: Boolean,
        duration: Int,
        title: String,
        message: String
    ): AestheticDialog {
        return AestheticDialog.Builder(context as Activity, DialogStyle.TOASTER, DialogType.INFO)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setDarkMode(isDarkMode)
            .setDuration(duration)
            .show()
    }

    fun connectifityError(
        context: Context,
        isDarkMode: Boolean,
        duration: Int,
        title: String,
        message: String
    ): AestheticDialog {

        return AestheticDialog.Builder(
            context as Activity,
            DialogStyle.CONNECTIFY,
            DialogType.ERROR
        )
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setDarkMode(isDarkMode)
            .setDuration(duration)
            .show()
    }

    fun sweetProgress(
        context: Context,
        title: String,
        message: String
    ) {
        return SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            .setTitleText(title)
            .setContentText(message)
            .show()
    }

    fun sweetSuccess(
        context: Context,
        title: String,
        message: String
    ) {
        return SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText(title)
            .setContentText(message)
            .show()
    }

    fun sweetWarning(
        context: Context,
        title: String,
        message: String,
        confirmText: String,
        cancelText: String,
        confirmListener: SweetAlertDialog.OnSweetClickListener,
        cancelListener: SweetAlertDialog.OnSweetClickListener

    ) {
        return SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
            .setTitleText(title)
            .setContentText(message)
            .setConfirmButton(confirmText, confirmListener)
            .setCancelButton(cancelText, cancelListener)
            .show()
    }

    fun sweetError(
        context: Context,
        title: String,
        message: String
    ) {
        return SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(title)
            .setContentText(message)
            .show()
    }


    fun sweetNormal(
        context: Context,
        title: String,
        message: String
    ) {
        return SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
            .setTitleText(title)
            .setContentText(message)
            .show()
    }


}