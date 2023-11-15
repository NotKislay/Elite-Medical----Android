package com.elite.medical.clinic.ui.sidemenu.jobs

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.ActivityCreateJobBinding
import com.elite.medical.retrofit.requestmodels.clinic.PostJobRequestModel
import com.elite.medical.utils.HelperMethods
import com.google.android.material.textfield.TextInputEditText

class CreateJob : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCreateJobBinding
    private lateinit var viewModel: MyJobsViewModel

    private lateinit var title: TextInputEditText
    private lateinit var vacancy: TextInputEditText
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var description: TextInputEditText
    private lateinit var location: Spinner
    private lateinit var jobType: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_job)
        viewModel = ViewModelProvider(this)[MyJobsViewModel::class.java]
        binding.btnBack.setOnClickListener { finish() }
        binding.btnSubmit.setOnClickListener { createJob() }

        initBindings()


        viewModel.getJobLocations()

        viewModel.jobLocation.observe(this) {
            val spinnerAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                it!!.clinicLocations
            )
            binding.spinner1.adapter = spinnerAdapter
        }

        viewModel.isJobCreatedSuccessfully.observe(this) {
            if (it) {
                Toast.makeText(this, "Job Created Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }

        }

        val jobTypeSpinnerItems = arrayOf("Part Time", "Full Time")
        val jobTypeSpinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, jobTypeSpinnerItems)
        binding.spinner2.adapter = jobTypeSpinnerAdapter


        binding.btnStartDate.setOnClickListener(this)
        binding.btnEndDate.setOnClickListener(this)

        binding.datePicker1.setOnDateChangedListener { datePicker, _, _, _ ->
            val start = "${datePicker.year}/${datePicker.month + 1}/${datePicker.dayOfMonth}"
            binding.tvStartDate.text = start
            binding.tvStartDate.error = null
            binding.btnStartDate.setTextColor(Color.parseColor("#FFFFFF"))

            toggleVisibility(datePicker)
        }

        binding.datePicker2.setOnDateChangedListener { datePicker, _, _, _ ->
            datePicker.dayOfMonth
            datePicker.month
            datePicker.year
            val end = "${datePicker.year}/${datePicker.month + 1}/${datePicker.dayOfMonth}"
            binding.tvEndDate.text = end
            binding.tvEndDate.error = null
            binding.btnEndDate.setTextColor(Color.parseColor("#FFFFFF"))

            toggleVisibility(datePicker)
        }


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
        val inputFields = arrayOf(
            title,
            vacancy,
            startDate,
            endDate,
            description
        )

        inputFields.map {
            if (it.id == binding.tvStartDate.id && it.text.isEmpty()) {
                binding.btnStartDate.setTextColor(Color.parseColor("#FF0000"))
                binding.btnStartDate.requestFocus()
            } else if (it.id == binding.tvEndDate.id && it.text.isEmpty()) {
                binding.btnEndDate.setTextColor(Color.parseColor("#FF0000"))
                binding.btnEndDate.requestFocus()
            }
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
            Toast.makeText(this, "all fields are required", Toast.LENGTH_SHORT).show()
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

    private fun toggleVisibility(view: View) {
        if (view.visibility == View.VISIBLE) view.visibility = View.GONE
        else view.visibility = View.VISIBLE

    }

    override fun onClick(view: View?) {
        when (view?.id) {

            binding.btnStartDate.id -> {
                HelperMethods.hideKeyboard(this@CreateJob)
                toggleVisibility(binding.datePicker1)
                binding.datePicker2.visibility = View.GONE
            }

            binding.btnEndDate.id -> {
                HelperMethods.hideKeyboard(this@CreateJob)
                binding.datePicker1.visibility = View.GONE
                toggleVisibility(binding.datePicker2)
            }

            binding.datePicker1.id -> {
                binding.datePicker1.setOnDateChangedListener { datePicker, i, i2, i3 ->
                    toggleVisibility(datePicker)
                }
            }

            binding.datePicker2.id -> {


            }
        }
    }

}