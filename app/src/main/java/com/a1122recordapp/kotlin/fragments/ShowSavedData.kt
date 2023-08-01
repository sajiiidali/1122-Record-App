package com.a1122recordapp.kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.a1122recordapp.Database
import com.a1122recordapp.R
import com.a1122recordapp.kotlin.adapters.expandAbleListAdapter
import com.a1122recordapp.kotlin.adapters.myRow_data
import java.util.ArrayList
import java.util.HashMap

class ShowSavedData : Fragment(R.layout.show_saved_data) {
    lateinit var spinner: Spinner
    lateinit var spinnerAdapter: ArrayAdapter<String>
    var spinnerArray = ArrayList<String>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        showSavedView = view
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val args = ShowSavedDataArgs.fromBundle(requireArguments())
        spinner = view.findViewById(R.id.spinnerToolbar)
        val expandAbleListview = view.findViewById<ExpandableListView>(R.id.expandAbleListView)
        db = Database(requireContext())
        month = args.getMonth
        alwaysMaintainYearValue = args.getCurrentYear
        var isYearExist = args.getCurrentYear
        spinnerArray.add(args.getCurrentYear.toString())
        try {
            var byDate = 0
            while (byDate <= 2){
                    isYearExist -= 1
                    val getCursorOfYear = db.checkByYear(isYearExist)
                    if (getCursorOfYear?.count != 0){
                        spinnerArray.add(isYearExist.toString())
                        byDate = 0
                    }
                byDate++
            }
            spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray)
            spinner.adapter = spinnerAdapter
        } catch (e: Exception) {
            Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
        }
        var index = 0
        var byDate = 0
        while (byDate <= 32){
            val getCursorByMonth = db.checkDataByMonthAndYear(byDate,args.getMonth,args.getCurrentYear.toInt())
            if (getCursorByMonth?.count != 0){
                if (parentName.isEmpty())
                {
                    index = 0
                }else
                {
                    index++
                }
                parentName.add(index,"A")
                val childList = ArrayList<myRow_data>()
                while (getCursorByMonth?.moveToNext()!!)
                {
                    if (parentName[index] == "A") {
                        parentName[index] = getCursorByMonth.getString(2)
                    }
                    if (getCursorByMonth.getString(1) == "")
                        childList.add(myRow_data(getCursorByMonth.getString(2),getCursorByMonth.getString(0)))
                    else
                        childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                }
                hashMap[parentName[index]] = childList
            }
            byDate++
        }

        myExpandableAdapter = expandAbleListAdapter(hashMap,parentName)
        expandAbleListview.setAdapter(myExpandableAdapter)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedYear: String = spinnerArray[position]

                if (selectedYear.toInt() != args.getCurrentYear){
                    alwaysMaintainYearValue = selectedYear.toInt()
                    parentName.clear()
                    myExpandableAdapter.notifyDataSetChanged()
                    var index = 0
                    var byDate = 0
                    while (byDate <= 32){
                        val getCursorByMonth = db.checkDataByMonthAndYear(byDate,args.getMonth,selectedYear.toInt())
                        if (getCursorByMonth?.count != 0){
                            if (parentName.isEmpty())
                            {
                                index = 0
                            }else
                            {
                                index++
                            }
                            parentName.add(index,"A")
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName[index] == "A") {
                                    parentName[index] = getCursorByMonth.getString(2)
                                }
                                if (getCursorByMonth.getString(1) == "")
                                    childList.add(myRow_data(getCursorByMonth.getString(2),getCursorByMonth.getString(0)))
                                else
                                    childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap[parentName[index]] = childList
                        }
                        byDate++
                    }
                    myExpandableAdapter = expandAbleListAdapter(hashMap,parentName)
                    expandAbleListview.setAdapter(myExpandableAdapter)
                }else{
                    alwaysMaintainYearValue = selectedYear.toInt()
                    parentName.clear()
                    var index = 0
                    var byDate = 0
                    while (byDate <= 32){
                        val getCursorByMonth = db.checkDataByMonthAndYear(byDate,args.getMonth,args.getCurrentYear.toInt())
                        if (getCursorByMonth?.count != 0){
                            if (parentName.isEmpty())
                            {
                                index = 0
                            }else
                            {
                                index++
                            }
                            parentName.add(index,"A")
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName[index] == "A") {
                                    parentName[index] = getCursorByMonth.getString(2)
                                }
                                if (getCursorByMonth.getString(1) == "")
                                    childList.add(myRow_data(getCursorByMonth.getString(2),getCursorByMonth.getString(0)))
                                else
                                    childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap[parentName[index]] = childList
                        }
                        byDate++
                    }
                    myExpandableAdapter = expandAbleListAdapter(hashMap,parentName)
                    expandAbleListview.setAdapter(myExpandableAdapter)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    companion object
    {
        @SuppressLint("StaticFieldLeak")
        lateinit var showSavedView:View
        lateinit var myExpandableAdapter :expandAbleListAdapter
        lateinit var db: Database
        var parentName = ArrayList<String>()
        var month:Int = 0
        var alwaysMaintainYearValue = 0
        val hashMap= HashMap<String, List<myRow_data>>()


        fun getEditLayout(ecNumber: String, ecType: String) {
            val directions = ShowSavedDataDirections.actionShowSavedDataToEditECNumber2(ecNumber,ecType)
            Navigation.findNavController(showSavedView).navigate(directions)
        }
        fun getDeleteData(ecNumber: String, ecType: String){
            val directions = ShowSavedDataDirections.actionShowSavedDataToDeleteEcNumber(ecNumber,ecType)
            Navigation.findNavController(showSavedView).navigate(directions)
        }
        fun refreshList(){
            parentName.clear()
            myExpandableAdapter.notifyDataSetChanged()
            alwaysMaintainYearValue
            val expandAbleListview = showSavedView.findViewById<ExpandableListView>(R.id.expandAbleListView)
            try {
                var index = 0
                var byDate = 0
                while (byDate <= 32){
                    val getCursorByMonth = db.checkDataByMonthAndYear(byDate, month,alwaysMaintainYearValue)
                    if (getCursorByMonth?.count != 0){
                        if (parentName.isEmpty())
                        {
                            index = 0
                        }else
                        {
                            index++
                        }
                        parentName.add(index,"A")
                        val childList = ArrayList<myRow_data>()
                        while (getCursorByMonth?.moveToNext()!!)
                        {
                            if (parentName[index] == "A") {
                                parentName[index] = getCursorByMonth.getString(2)
                            }
                            if (getCursorByMonth.getString(1) == "")
                                childList.add(myRow_data(getCursorByMonth.getString(2),getCursorByMonth.getString(0)))
                            else
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                        }
                        hashMap[parentName[index]] = childList
                    }
                    byDate++
                }
                myExpandableAdapter = expandAbleListAdapter(hashMap,parentName)
                expandAbleListview.setAdapter(myExpandableAdapter)
            } catch (e: Exception) {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        parentName.clear()
        myExpandableAdapter.notifyDataSetChanged()
    }
}