package com.elite.medical.nurse.fragments.searchjobs

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.FragmentSearchJobsBinding
import com.elite.medical.nurse.NurseViewModel
import com.elite.medical.nurse.adapters.jobs.SearchJobsAdapter
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job

class SearchJobs : Fragment() {
    private lateinit var binding: FragmentSearchJobsBinding
    private lateinit var viewModel: NurseViewModel
    private lateinit var adapter: SearchJobsAdapter
    private lateinit var listView: RecyclerView
    private lateinit var listJobs: List<Job>

    private val jobTypeItems = arrayOf("Select Job Type", "Part Time", "Full Time")
    private val locationItems = arrayOf("Select Location", "New York", "Atlanta", "Albama")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchJobsBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        initialize()

        viewModel.jobList.observe(viewLifecycleOwner) { populateJobs(it) }

        filterListByJobTitle()



        return binding.root
    }

    private fun filterListByJobTitle() {
        binding.filterJobTitle.doOnTextChanged { text, _, _, _ ->
            val filterList = listJobs.filter { it.title.contains(text!!, true) }
            binding.tvNoData.isVisible = filterList.isEmpty()
            adapter.filterList(filterList)
        }
    }

    private fun initialize() {
        viewModel = ViewModelProvider(requireActivity())[NurseViewModel::class.java]
        listView = binding.listview
        viewModel.getJobsList()
        setupSpinner()
    }

    private fun populateJobs(it: List<Job>?) {
        listJobs = it!!
        adapter = SearchJobsAdapter(it, viewModel, context)
        binding.listview.adapter = adapter
        binding.loader.isVisible = false

        adapter.onItemClikced = {
            viewModel.updateCurrentJobID(it.id.toString())
            findNavController().navigate(com.elite.medical.R.id.action_searchJobsTab_to_searchJobDetail2)
        }


        binding.spinnerJobType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != 0) {
                        val item = jobTypeItems.elementAt(p2)
                        val filterList = listJobs.filter { it.type.contains(item) }
                        binding.tvNoData.isVisible = filterList.isEmpty()
                        adapter.filterList(filterList)
                    } else {
                        adapter.filterList(listJobs)
                        binding.tvNoData.isVisible = false
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

        binding.spinnerLocation.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != 0) {
                        val item = locationItems.elementAt(p2)
                        val filterList = listJobs.filter {
                            it.locations.joinToString(",").contains(item)
                        }
                        binding.tvNoData.isVisible = filterList.isEmpty()
                        adapter.filterList(filterList)
                    } else {
                        adapter.filterList(listJobs)
                        binding.tvNoData.isVisible = false
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }


    }

    private fun setupSpinner() {
        val jobTypeAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                jobTypeItems
            )
        val locationAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                locationItems
            )
        binding.spinnerJobType.adapter = jobTypeAdapter
        binding.spinnerLocation.adapter = locationAdapter
    }


}