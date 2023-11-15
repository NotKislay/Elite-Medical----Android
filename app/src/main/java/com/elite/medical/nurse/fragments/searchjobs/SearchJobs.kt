package com.elite.medical.nurse.fragments.searchjobs

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.FragmentSearchJobsBinding
import com.elite.medical.nurse.viewmodels.NurseViewModel
import com.elite.medical.nurse.adapters.jobs.SearchJobsAdapter
import com.elite.medical.nurse.viewmodels.jobs.JobsViewModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobList

class SearchJobs : Fragment() {
    private lateinit var binding: FragmentSearchJobsBinding
    private lateinit var viewModel: NurseViewModel
    private lateinit var viewModelJobs: JobsViewModel
    private lateinit var adapter: SearchJobsAdapter
    private lateinit var listView: RecyclerView
    private lateinit var listJobs: List<Job>

    private lateinit var jobTypeItems: MutableList<String>
    private lateinit var locationItems: MutableList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchJobsBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        initialize()

//        viewModel.jobList.observe(viewLifecycleOwner) { populateJobs(it) }

        viewModelJobs.getJobsList()

        viewModelJobs.jobListCallback = {
            populateJobs(it)
        }

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
        viewModelJobs = ViewModelProvider(requireActivity())[JobsViewModel::class.java]
        listView = binding.listview
    }

    private fun populateJobs(it: JobList) {

        if (it.status == "warning") {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            binding.btnJobSearchRequest.isVisible = true
            binding.loader.isVisible = false

            binding.btnJobSearchRequest.setOnClickListener {
                viewModelJobs.jobSearchRequest()
            }

            viewModelJobs.jobSearchRequestCallback = {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }

        } else {

            listJobs = it.jobs
            adapter = SearchJobsAdapter(it.jobs, viewModel, context)
            binding.listview.adapter = adapter
            binding.loader.isVisible = false

            locationItems = it.jobLocations.toMutableList()
            jobTypeItems = it.jobTypes.toMutableList()

            locationItems.add(0, "Select Location")
            jobTypeItems.add(0, "Select Job Type")

            setupSpinner()


            adapter.onItemClikced = {
                viewModelJobs.updateCurrentJobID(it.id.toString())
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