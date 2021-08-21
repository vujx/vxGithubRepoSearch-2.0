package com.algebra.githubreposearch20.presentation.ui.dialog

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CustomDialog(private val listener: CustomDialogListener, private val title: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return MaterialAlertDialogBuilder(requireActivity(), R.style.MaterialAlertDialog_OK_color).setView(view)
            .setMessage(title)
            .setPositiveButton(App.getStringResource(R.string.ok)) { _, _ ->
                listener.okPress()
            }.setNegativeButton(App.getStringResource(R.string.cancel)) { _, _ ->
                dialog?.cancel()
            }.create()
    }
}
