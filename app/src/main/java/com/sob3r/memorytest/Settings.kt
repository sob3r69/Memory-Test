package com.sob3r.memorytest

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class Settings: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it, R.style.AlertDialogTheme)
            val inflater = requireActivity().layoutInflater;

            builder
                .setPositiveButton(R.string.settings_set,
                    DialogInterface.OnClickListener{
                            dialog, id ->
                    })
                .setNegativeButton(R.string.settings_cancel,
                    DialogInterface.OnClickListener{
                            dialog, id ->
                    })
                .setView(inflater.inflate(R.layout.settings_dialog, null))
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}