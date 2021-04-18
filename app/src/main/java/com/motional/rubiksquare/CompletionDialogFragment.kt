package com.motional.rubiksquare

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

internal const val FRAGMENT_TAG = "CompletionDialogFragment"

class CompletionDialogFragment : DialogFragment() {

    internal lateinit var listener: DialogListener

    interface DialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = activity as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement DialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val title = resources.getString(R.string.completion_dialog_title)
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("${String(Character.toChars(0x1F64C))} $title")
                .setMessage(R.string.completion_dialog_message)
                .setPositiveButton(R.string.completion_dialog_positive_text) { _, _ ->
                    listener.onDialogPositiveClick(this)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}