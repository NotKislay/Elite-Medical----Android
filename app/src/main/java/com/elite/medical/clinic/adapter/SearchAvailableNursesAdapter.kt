package com.elite.medical.clinic.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.clinic.ui.sidemenu.nurses.ActivityEnrolledNurseDetails
import com.elite.medical.clinic.ui.sidemenu.nurses.ActivitySearchNurseDetails
import com.elite.medical.databinding.CustomTableItemWArrowBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse

class SearchAvailableNursesAdapter(private var nurses: List<Nurse>, private val context: Context) :
    RecyclerView.Adapter<SearchAvailableNursesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CustomTableItemWArrowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var nurseName = binding.tv1CstmTable
        var citycontent = binding.tv2CstmTable
        var lictypecontent = binding.tv3CstmTable
        var dobcontent = binding.tv4CstmTable
        var licexpirycontent = binding.tv5CstmTable
        var experiencecontent = binding.tv6CstmTable

        var label1 = binding.label1CstmTable
        var label2 = binding.label2CstmTable
        var label3 = binding.label3CstmTable
        var label4 = binding.label4CstmTable
        var label5 = binding.label5CstmTable
        var label6 = binding.label6CstmTable

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomTableItemWArrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.label1.text = "Name"
        holder.label2.text = "City"
        holder.label3.text = "License Type"
        holder.label4.text = "D.O.B"
        holder.label5.text = "License Expiry"
        holder.label6.text = "Experience (in yrs)"

        holder.nurseName.text = nurses[position].name
        holder.citycontent.text = nurses[position].city
        holder.lictypecontent.text = nurses[position].licenseType
        holder.dobcontent.text = nurses[position].dob
        holder.licexpirycontent.text = nurses[position].licenseExpiry
        holder.experiencecontent.text = nurses[position].experience

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ActivitySearchNurseDetails::class.java)
            intent.putExtra("Nurse_id", nurses[position].id.toString())
            context.startActivity(intent)
        }

    }

    override fun getItemCount() = nurses.size

    fun filterList(filteredList: List<Nurse>) {
        nurses = filteredList
        notifyDataSetChanged()
    }

}