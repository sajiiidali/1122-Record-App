package com.a1122recordapp.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import com.a1122recordapp.Database
import com.a1122recordapp.R

class DeleteEcNumber :DialogFragment(R.layout.delete_ec_number_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val yes = view.findViewById<AppCompatButton>(R.id.btnYes)
        val no  = view.findViewById<AppCompatButton>(R.id.btnNo)
        val message = view.findViewById<AppCompatTextView>(R.id.deleteMessage)
        val dialogTitle = "Do You Want To Delete This Record"
        val db = Database(requireContext())
        val args = DeleteEcNumberArgs.fromBundle(requireArguments())
        message.text = dialogTitle

        yes.setOnClickListener {
            val getCursorOffLeave = db.checkDayOffLeave(args.getEcNumber,args.getEcType)
            val getCursorOfEcNumber = db.checkByEcNumber(args.getEcNumber,args.getEcType)

            if (getCursorOffLeave.count != 0){
                val isDeleted = db.deleteDayOffLeave(args.getEcNumber,args.getEcType)
                if (isDeleted)
                    Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(activity, "Please Try Again", Toast.LENGTH_SHORT).show()
                ShowSavedData.refreshList()
                dialog?.dismiss()
            }
            if (getCursorOfEcNumber.count != 0){
                val isDeleted = db.deleteData(args.getEcNumber,args.getEcType)
                if (isDeleted)
                    Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(activity, "Please Try Again", Toast.LENGTH_SHORT).show()
                ShowSavedData.refreshList()
                dialog?.dismiss()
            }


        }
        no.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}