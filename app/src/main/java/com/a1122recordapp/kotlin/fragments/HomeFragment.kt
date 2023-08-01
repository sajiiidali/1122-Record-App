package com.a1122recordapp.kotlin.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.a1122recordapp.R
import com.a1122recordapp.databinding.HomeFragmentBinding

class HomeFragment : Fragment(R.layout.home_fragment) {

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = HomeFragmentBinding.bind(view)
        var homeView = view
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.app_name)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        with(binding){
            ToDayLayout.setOnClickListener {
                val navigation = HomeFragmentDirections.actionHomeFragmentToEcSaveIntoCurrentDate2()
                Navigation.findNavController(view).navigate(navigation)
            }
            saveDayOff.setOnClickListener {
                val layoutInflater = layoutInflater
                homeView = layoutInflater.inflate(R.layout.datepicker_layout,null)
                val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogButtonColor)
                builder.setView(homeView)

                builder.setPositiveButton(R.string.Yes) { dialog, btn->
                    val datePickerDialog = homeView.findViewById<DatePicker>(R.id.datepicker)
                    val selectedDate = datePickerDialog.dayOfMonth
                    val selectedMonth = datePickerDialog.month + 1
                    val selectedYear = datePickerDialog.year

                    try {
                        val directions = HomeFragmentDirections.actionHomeFragmentToSaveDayOffLeave2(selectedDate,selectedMonth,selectedYear)
                        Navigation.findNavController(view).navigate(directions)
                        dialog.dismiss()
                    } catch (e: Exception) {
                        Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
                    }
                }.show()
            }
            previousDayLayout.setOnClickListener {
                val layoutInflater = layoutInflater
                homeView = layoutInflater.inflate(R.layout.datepicker_layout,null)
                val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogButtonColor)
                builder.setView(homeView)

                builder.setPositiveButton(R.string.Yes) { dialog, btn->
                    val datePickerDialog = homeView.findViewById<DatePicker>(R.id.datepicker)
                    val selectedDate = datePickerDialog.dayOfMonth
                    val selectedMonth = datePickerDialog.month + 1
                    val selectedYear = datePickerDialog.year

                    try {
                        val directions = HomeFragmentDirections.actionHomeFragmentToEcSaveIntoPreviousDate(selectedDate,selectedMonth,selectedYear)
                        Navigation.findNavController(view).navigate(directions)
                        dialog.dismiss()
                    } catch (e: Exception) {
                        Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
                    }
                }.show()
            }
            showSavedDataLayout.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToGetMonthByUser()
                Navigation.findNavController(view).navigate(directions)
            }
        }
    }
}