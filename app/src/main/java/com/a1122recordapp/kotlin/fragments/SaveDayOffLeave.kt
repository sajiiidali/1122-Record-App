package com.a1122recordapp.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.a1122recordapp.Database
import com.a1122recordapp.R
import java.util.ArrayList


class SaveDayOffLeave : DialogFragment(R.layout.save_dayoff_leave) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = SaveDayOffLeaveArgs.fromBundle(requireArguments())
        val byDay    = args.day
        val byMonth  = args.month
        val byYear   = args.year
        val textViewDate = view.findViewById<TextView>(R.id.tvdateforDayoff)
        val saveButton   = view.findViewById<Button>(R.id.btnsaveOfDayoff)
        val spin = view.findViewById<Spinner>(R.id.dayoffSpinner)
        val database = Database(requireActivity())
        val getOldDate = "$byDay-$byMonth-$byYear"
        textViewDate.text = getOldDate
        val arrayList = ArrayList<String>()
        arrayList.add("<Select EC Type>")
        arrayList.add("DAY OFF")
        arrayList.add("CASUAL LEAVE")
        arrayList.add("MEDICAL LEAVE")
        arrayList.add("POWER LEAVE")
        arrayList.add("MARRIAGE LEAVE")
        arrayList.add("PATERNITY LEAVE")
        arrayList.add("NATIONAL DAY")
        arrayList.add("DUTY CHANGE")

        val array = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayList)
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = array

        saveButton.setOnClickListener {

            try {
                val cursor = database.checkDataByMonthAndYear(byDay,byMonth,byYear)
                if (cursor?.count != 0){
                    Toast.makeText(activity, "Can't Save Because On this day you were on duty", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    if (spin.selectedItem.toString() == "<Select EC Type>"){
                        Toast.makeText(activity, "please Select your Type of Leave ", Toast.LENGTH_SHORT).show()
                    }else {
                        val typeOfHoliday   = spin.selectedItem.toString()
                        val dayOfMonth      = byDay.toString()
                        val monthOfYear     = byMonth.toString()
                        val yYear           = byYear.toString()

                        val isInsert = database.insertData(typeOfHoliday,"",getOldDate,monthOfYear,dayOfMonth,yYear,"")
                        dismiss()
                        if (isInsert)
                            Toast.makeText(activity, "$typeOfHoliday Saved", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(activity, "not Saved", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
            }
        if(spin.selectedItem.toString() == "NATIONAL DAY" || spin.selectedItem.toString() == "CASUAL LEAVE"){
        }

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