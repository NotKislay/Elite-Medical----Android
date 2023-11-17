package com.elite.medical.clinic.fragments.jobs.create

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentCreateJobBinding
import com.elite.medical.databinding.FragmentProfileClinicBinding
import com.elite.medical.retrofit.requestmodels.clinic.PostJobRequestModel
import com.elite.medical.utils.HelperMethods
import com.google.android.material.textfield.TextInputEditText
import java.sql.Date
import java.util.Calendar

class CreateJobFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCreateJobBinding
    private lateinit var viewModel: MyJobsViewModel
    private lateinit var title: TextInputEditText
    private lateinit var vacancy: TextInputEditText
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var description: TextInputEditText
    private lateinit var location: Spinner
    private lateinit var jobType: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateJobBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MyJobsViewModel::class.java]

        initBindings()

        viewModel.getJobLocations()

        viewModel.jobLocation.observe(viewLifecycleOwner) {
            val spinnerAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                it!!.clinicLocations
            )
            binding.spinner1.adapter = spinnerAdapter
        }

        viewModel.isJobCreatedSuccessfully.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Job Created Successfully", Toast.LENGTH_SHORT)
                    .show()
                activity?.onBackPressedDispatcher?.onBackPressed()
            }

        }

        val jobTypeSpinnerItems = arrayOf("Part Time", "Full Time")
        val jobTypeSpinnerAdapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                jobTypeSpinnerItems
            )
        binding.spinner2.adapter = jobTypeSpinnerAdapter


        binding.btnStartDate.setOnClickListener(this)
        binding.btnEndDate.setOnClickListener(this)

        binding.btnStartDate.setOnClickListener { openDatePicker(startDate) }
        binding.btnEndDate.setOnClickListener { openDatePicker(endDate) }


        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        binding.btnSubmit.setOnClickListener { createJob() }







        return binding.root
    }

    private fun initBindings() {
        title = binding.title
        vacancy = binding.Vacancy
        description = binding.Description
        location = binding.spinner1
        jobType = binding.spinner2
        startDate = binding.tvStartDate
        endDate = binding.tvEndDate
    }

    private fun createJob() {
        HelperMethods.hideKeyboard(requireActivity())
        val inputFields = arrayOf(
            title,
            vacancy,
            startDate,
            endDate,
            description
        )

        inputFields.map {
            if (it.text.toString().isEmpty()) {
                it.error = "Shouldn't be empty"
                it.requestFocus()
            }
        }

        val titleText = title.text.toString()
        val vacancyText = vacancy.text.toString()
        val startDateText = binding.tvStartDate.text.toString()
        val endDateText = binding.tvEndDate.text.toString()
        val descriptionText = description.text.toString()
        val locationText = location.selectedItem.toString()
        val jobTypeText = jobType.selectedItem.toString()

        if (
            titleText.isEmpty() ||
            vacancyText.isEmpty() ||
            endDateText.isEmpty() ||
            startDateText.isEmpty() ||
            descriptionText.isEmpty()
        ) {
            Toast.makeText(requireContext(), "all fields are required", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.postJob(
                PostJobRequestModel(
                    title = titleText,
                    jobType = jobTypeText,
                    engage_from = binding.tvStartDate.text.toString(),
                    engage_to = binding.tvEndDate.text.toString(),
                    vacancy = vacancyText,
                    locations = locationText,
                    description = descriptionText
                )
            )
        }


    }

    override fun onClick(view: View?) {}

    private fun openDatePicker(button: TextView) {
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.THEME_DEVICE_DEFAULT_DARK,
            { _, year, month, dayOfMonth ->
                val date = Date(year - 1900, month, dayOfMonth)
                val str = "$date"
                button.text = str
                button.setTextColor(Color.BLACK)
            },
            y,
            m,
            d
        )
        dialog.show()
        dialog.setOnCancelListener {
            button.text = ""
        }
        button.error = null
    }

}