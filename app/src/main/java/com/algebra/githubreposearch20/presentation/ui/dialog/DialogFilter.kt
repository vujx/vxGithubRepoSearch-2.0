package com.algebra.githubreposearch20.presentation.ui.dialog

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R

class DialogFilter(private val listener: DialogFilterListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val mBuilder = AlertDialog.Builder(requireContext())
        mBuilder.setTitle(App.getStringResource(R.string.filter_title))
        val listOfValues = mutableListOf(
            App.getStringResource(R.string.stars),
            App.getStringResource(R.string.forks),
            App.getStringResource(R.string.update)
        )

        mBuilder.setNegativeButton(App.getStringResource(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
        mBuilder.setSingleChoiceItems(listOfValues.toTypedArray(), 0) { _, _ ->
        }
        mBuilder.setView(view)
        val alertDialog = mBuilder.create()
        alertDialog.show()
        mBuilder.setSingleChoiceItems(listOfValues.toTypedArray(), 0) { _, which ->
            listener.pressFilter(listOfValues[which])
            alertDialog.dismiss()
        }
        return alertDialog
    }
}
