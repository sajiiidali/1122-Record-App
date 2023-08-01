package com.a1122recordapp.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.a1122recordapp.R
import java.util.*

class GetMonthByUser : DialogFragment(R.layout.get_monthby_user),View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val january      = view.findViewById<Button>(R.id.btnjanuary)
        val february     = view.findViewById<Button>(R.id.btnfebruary)
        val march        = view.findViewById<Button>(R.id.btnmarch)
        val april        = view.findViewById<Button>(R.id.btnapril)
        val may          = view.findViewById<Button>(R.id.btnmay)
        val june         = view.findViewById<Button>(R.id.btnjune)
        val july         = view.findViewById<Button>(R.id.btnjuly)
        val august       = view.findViewById<Button>(R.id.btnaugust)
        val september    = view.findViewById<Button>(R.id.btnseptember)
        val october      = view.findViewById<Button>(R.id.btnoctober)
        val november     = view.findViewById<Button>(R.id.btnnovember)
        val december     = view.findViewById<Button>(R.id.btndecember)

        january.setOnClickListener(this)
        february.setOnClickListener(this)
        march.setOnClickListener(this)
        april.setOnClickListener(this)
        may.setOnClickListener(this)
        june.setOnClickListener(this)
        july.setOnClickListener(this)
        august.setOnClickListener(this)
        september.setOnClickListener(this)
        october.setOnClickListener(this)
        november.setOnClickListener(this)
        december.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnjanuary->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(1,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnfebruary->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(2,getCurrentYear)
                findNavController().navigate(directions)            }
            R.id.btnmarch->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(3,getCurrentYear)
                findNavController().navigate(directions)            }
            R.id.btnapril->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(4,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnmay->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(5,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnjune->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(6,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnjuly->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(7,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnaugust->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(8,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnseptember->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(9,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnoctober->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(10,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btnnovember->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(11,getCurrentYear)
                findNavController().navigate(directions)
            }
            R.id.btndecember->{
                val calendar    = Calendar.getInstance(TimeZone.getDefault())
                val getCurrentYear     = calendar.get(Calendar.YEAR)
                val directions = GetMonthByUserDirections.actionGetMonthByUserToShowSavedData(12,getCurrentYear)
                findNavController().navigate(directions)
            }
        }
            }
    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}