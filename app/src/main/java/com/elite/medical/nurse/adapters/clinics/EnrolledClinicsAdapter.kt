package com.elite.medical.nurse.adapters.clinics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.databinding.CustomTableItemWArrowBinding
import com.elite.medical.nurse.NurseViewModel
import com.elite.medical.nurse.viewmodels.clinics.ClinicsViewModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.Clinics

class EnrolledClinicsAdapter(
    private val clinics: List<Clinics>,
    private val viewModel: ClinicsViewModel,
    private val context: Context
) : RecyclerView.Adapter<EnrolledClinicsAdapter.ViewHolder>() {

    var onItemClicked: ((Clinics) -> Unit)? = null


    inner class ViewHolder(val binding: CustomTableItemWArrowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var nurseName = binding.tv1CstmTable
        var clinicType = binding.tv2CstmTable
        var addressContent = binding.tv3CstmTable

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
        holder.label2.text = "Type"
        holder.label3.text = "Address"
        holder.label4.visibility = View.GONE
        holder.label5.visibility = View.GONE
        holder.label6.visibility = View.GONE


        holder.nurseName.text = item.name
        holder.clinicType.text = item.clinicType
        holder.addressContent.text = item.address
        holder.dobcontent.visibility = View.GONE
        holder.licexpirycontent.visibility = View.GONE
        holder.experiencecontent.visibility = View.GONE

        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(item)
            viewModel.currentClinicID.postValue(item.id.toString())
            it.findNavController()
                .navigate(R.id.action_EnrolledClinicsFragment_to_enrolledClinicsDetailsFragment)
        }

    }

    override fun getItemCount() = clinics.size
}