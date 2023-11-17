package com.elite.medical.nurse.adapters.home

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.elite.medical.R

class MenuAdapter(
    private val context: Context,
    private val sideMenuItems: List<String>,
    private val sideMenuSubItems: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {


    private val groupIcons: Map<String, Int> = mapOf(
        "Dashboard" to R.drawable.sm_1,
        "Jobs" to R.drawable.sm_1_6,
        "Clinics" to R.drawable.sm_1_3,
        "Home" to R.drawable.sm_1_1,
        "Notifications" to R.drawable.sm_1_2,
        "Profile" to R.drawable.sm_1_5,
        "Search Jobs" to R.drawable.search_icon,
        "Applied Jobs" to R.drawable.sm_2_5,
        "Enrolled" to R.drawable.sm_2_2,
    )

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return sideMenuSubItems[sideMenuItems[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        var convertViewVar = convertView

        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertViewVar == null) {

            convertViewVar = layoutInflater.inflate(R.layout.list_item_navign, null)

        }
        val expandedListTextView = convertViewVar!!.findViewById<TextView>(R.id.expandedListItem)
        expandedListTextView.text = expandedListText

        val groupIcon = convertViewVar.findViewById<ImageView>(R.id.itemicon)
        val iconResourceId = groupIcons[expandedListText]
        if (iconResourceId != null) {
            groupIcon.setImageResource(iconResourceId)
        }

        return convertViewVar
    }


    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val listTitle = getGroup(listPosition) as String
        var convertViewVar = convertView
        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                    as LayoutInflater
        if (convertViewVar == null) {

            convertViewVar = layoutInflater.inflate(R.layout.list_group, null)

        }
        val listTitleTextView = convertViewVar!!.findViewById<TextView>(R.id.listTitle)
        listTitleTextView.setTypeface(null, Typeface.NORMAL)
        listTitleTextView.text = listTitle // group text


        //setting group icons

        val groupIcon = convertViewVar.findViewById<ImageView>(R.id.groupicon)
        val iconResourceId = groupIcons[listTitle]
        if (iconResourceId != null)
            groupIcon.setImageResource(iconResourceId)



        val indicatorArrow =
            convertViewVar.findViewById<ImageView>(R.id.indicatorArrow)//indicator custom icno


        if (isExpanded) {
            indicatorArrow.setImageResource(R.drawable.uparrow)
        } else {
            indicatorArrow.setImageResource(R.drawable.downarrow)
        }

        return convertViewVar
    }


    override fun getChildrenCount(listPosition: Int): Int {
        return sideMenuSubItems[sideMenuItems[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return sideMenuItems[listPosition]
    }

    override fun getGroupCount(): Int {
        return sideMenuItems.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}

