package com.a1122recordapp.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.a1122recordapp.Database
import com.a1122recordapp.R
import java.util.*

class EcSaveIntoCurrentDate: DialogFragment(R.layout.activity_save_data_current_day) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val currentDate = view.findViewById<TextView>(R.id.tvdate)
        val getEcNumber = view.findViewById<EditText>(R.id.edttext)
        val saveButton  = view.findViewById<Button>(R.id.btnsave)
        val clearButton = view.findViewById<Button>(R.id.btnclear)
        val database = Database(requireActivity())


        val spin = view.findViewById<Spinner>(R.id.spinner)
        val arrayList = ArrayList<String>()
        arrayList.add("<Select EC Type>")
        arrayList.add("RC")
        arrayList.add("ME")
        arrayList.add("FO")
        arrayList.add("WR")
        arrayList.add("RO")
        arrayList.add("OO")
        arrayList.add("REF")
        val array = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayList)
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = array
        val calendar    = Calendar.getInstance(TimeZone.getDefault())
        val month       = calendar.get(Calendar.MONTH)
        val byMonth    = month+1
        val byDay      = calendar.get(Calendar.DAY_OF_MONTH)
        val byYear     = calendar.get(Calendar.YEAR)
        val getCurrentDate = "$byDay-$byMonth-$byYear"
        currentDate.text = getCurrentDate

        saveButton.setOnClickListener {
            var isMatch = 0
            try {
                val checkEcNumber = getEcNumber.text.toString()
                val cursor = database.checkData(byMonth,byYear)
                if (cursor.count != 0){
                    while (cursor.moveToNext()){
                        if (cursor.getString(1).toString() == checkEcNumber){
                            isMatch++
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
            }

            if (spin.selectedItem.toString() == "<Select EC Type>"){
                Toast.makeText(activity, "Select EC Type ", Toast.LENGTH_SHORT).show()
            }else if (getEcNumber.text.toString().isEmpty()) {
                getEcNumber.error = "Type EC Number"
            }else if (isMatch == 0){
                val ecType = spin.selectedItem.toString()
                val ecNumber = getEcNumber.text.toString()
                val dayOfMonth = byDay.toString()
                val monthOfYear = byMonth.toString()
                val yYear       = byYear.toString()
                val isInsert = database.insertData(ecType,ecNumber,getCurrentDate,monthOfYear,dayOfMonth,yYear,"")
                if (isInsert){
                    Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity, "not Saved", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity, "This Record Already Saved", Toast.LENGTH_SHORT).show()
            }

        }
        clearButton.setOnClickListener {
            getEcNumber.setText("")
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