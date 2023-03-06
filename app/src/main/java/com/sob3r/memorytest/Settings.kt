package com.sob3r.memorytest

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class Settings: DialogFragment() {

    private lateinit var listener: SettingsDialogListener

    interface SettingsDialogListener {
        fun btnSize1x()
        fun btnSize15x()
        fun defDifficulty()
        fun medDifficulty()
        fun hardDifficulty()
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as SettingsDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString()) +
                    " must implement NoticeDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater;
        val myView: View = inflater.inflate(R.layout.settings_dialog, null)

        val setBtnSize15x: Button = myView.findViewById(R.id.settingsBtn15x)
        val setBtnSize1x: Button = myView.findViewById(R.id.settingsBtn1x)
        setBtnSize1x.setOnClickListener { listener.btnSize1x() }
        setBtnSize15x.setOnClickListener { listener.btnSize15x() }

        val setDifficultyDefBtn: Button = myView.findViewById(R.id.settingsDifficultEasy)
        val setDifficultyMedBtn: Button = myView.findViewById(R.id.settingsDifficultMed)
        val setDifficultyHardBtn: Button = myView.findViewById(R.id.settingsDifficultHard)
        setDifficultyDefBtn.setOnClickListener { listener.defDifficulty() }
        setDifficultyMedBtn.setOnClickListener { listener.medDifficulty() }
        setDifficultyHardBtn.setOnClickListener { listener.hardDifficulty() }

        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialogTheme)

            builder
                .setPositiveButton(R.string.settings_set,
                    DialogInterface.OnClickListener{ dialog, id ->
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton(R.string.settings_cancel,
                    DialogInterface.OnClickListener{ dialog, id ->
                        listener.onDialogNegativeClick(this)
                    })
                .setView(myView)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}