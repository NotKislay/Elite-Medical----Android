package com.elite.medical.nurse.adapters.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.CustomTableItemWArrowBinding
import com.elite.medical.retrofit.responsemodel.nurse.home.DashboardDataNurseModel

class TopRatedClinicsAdapter(
    private val clinics: List<DashboardDataNurseModel.TopClinic>
) : RecyclerView.Adapter<TopRatedClinicsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: CustomTableItemWArrowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var clinicName = binding.tv1CstmTable
        var location = binding.tv2CstmTable
        var rating = binding.tv3CstmTable

        //NR below
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

        val item = clinics.elementAt(position)

        holder.label1.text = "Clinic Name"
        holder.label2.text = "Location"
        holder.label3.text = "Ratings"
        holder.label4.visibility = View.GONE
        holder.label5.visibility = View.GONE
        holder.label6.visibility = View.GONE



        holder.clinicName.text = item.name
        holder.location.text = item.city
        holder.rating.text = item.clinicReview[0].rating
        holder.dobcontent.visibility = View.GONE
        holder.licexpirycontent.visibility = View.GONE
        holder.experiencecontent.visibility = View.GONE


    }

    override fun getItemCount() = clinics.size
}