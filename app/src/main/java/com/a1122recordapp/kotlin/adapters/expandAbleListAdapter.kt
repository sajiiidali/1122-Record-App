package com.a1122recordapp.kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import com.a1122recordapp.R
import com.a1122recordapp.kotlin.fragments.ShowSavedData
import java.util.HashMap

class expandAbleListAdapter(
    var childListMap: HashMap<String, List<myRow_data>>,
    var parentList: List<String>) : BaseExpandableListAdapter() {


    override fun getGroupCount(): Int {
        return parentList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childListMap[parentList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return parentList.get(groupPosition)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): myRow_data {
        return childListMap[parentList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {

        val titleOfList = getGroup(groupPosition).toString()
        var varView = convertView
        if (convertView == null){
            varView = LayoutInflater.from(parent?.context).inflate(R.layout.get_list_title, null as ViewGroup?)
        }
        val setListName = varView?.findViewById<TextView>(R.id.group_title)
        setListName?.setText(titleOfList)
        return varView!!

    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
        isLastChild: Boolean,convertView: View?,
        parent: ViewGroup?
    ): View {
        val EcNumber = getChild(groupPosition,childPosition).EC_NUMBER
        val ecType = getChild(groupPosition,childPosition).EC_TYPE
        var varView = convertView
        if (convertView == null){
            varView = LayoutInflater.from(parent?.context).inflate(R.layout.data_row_layout_listview, null as ViewGroup?)
        }
        val EC_NUMBER = varView?.findViewById<TextView>(R.id.edit_ec_number)
        val EC_TYPE = varView?.findViewById<TextView>(R.id.edit_ec_Type)
        val deleteList = varView?.findViewById<AppCompatImageButton>(R.id.deleteList)
        val editList = varView?.findViewById<AppCompatImageButton>(R.id.edit_listName)
        EC_NUMBER?.setText(EcNumber)
        EC_TYPE?.setText(ecType)
        deleteList?.setOnClickListener {
            ShowSavedData.getDeleteData(getChild(groupPosition,childPosition).EC_NUMBER,getChild(groupPosition,childPosition).EC_TYPE)
        }
        editList?.setOnClickListener {
            ShowSavedData.getEditLayout(getChild(groupPosition,childPosition).EC_NUMBER,getChild(groupPosition,childPosition).EC_TYPE)
        }
        return varView!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }


}