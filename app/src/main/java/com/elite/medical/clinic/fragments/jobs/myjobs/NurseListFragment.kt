package com.elite.medical.clinic.fragments.jobs.myjobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentNurseListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class NurseListFragment : Fragment() {
    private lateinit var binding: FragmentNurseListBinding
    private lateinit var viewModel: MyJobsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]




        viewModel.nurseListFromCurrentJob.observe(viewLifecycleOwner) { it ->
            if (it.isNotEmpty()) {
                val adapter =
                    NurseListAdapter(
                        it,
                        viewModel,
                        viewModel.currentJobDetails.value!!.status
                    )
                binding.listview.layoutManager = LinearLayoutManager(requireContext())
                binding.listview.adapter = adapter

                adapter.onItemClicked = { nurseDetails ->
                    viewModel.currentNurseDetails.postValue(nurseDetails)
                    findNavController().navigate(R.id.action_nurseListFragment_to_nurseDetailsFragment22)
                }

            } else {
                MaterialAlertDialogBuilder(
                    view?.context!!,
                    R.style.MyDialogTheme
                )
                    .setMessage("No Data Found")
                    .setPositiveButton("Okay") { dialog, _ ->
                        dialog.dismiss()
                        activity?.onBackPressedDispatcher?.onBackPressed()
                    }
                    .show()
            }
            binding.loader.visibility = View.GONE
        }




        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

}