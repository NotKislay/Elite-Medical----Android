package com.elite.medical.clinic.ui.sidemenu.jobs.fragments.myjobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        binding.btnBack.setOnClickListener { activity?.onBackPressed() }



        viewModel.jobDetailsByID.observe(viewLifecycleOwner) {
            val nurseList = it?.nurses as MutableList
            if (nurseList.isNotEmpty()) {
                val adapter =
                    NurseListAdapter(nurseList,viewModel)
                binding.listview.layoutManager = LinearLayoutManager(requireContext())
                binding.listview.adapter = adapter

            } else {
                MaterialAlertDialogBuilder(
                    view?.context!!,
                    R.style.MyDialogTheme
                )
                    .setMessage("No Data Found")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        activity?.onBackPressed()
                    }
                    .show()
            }
            binding.loader.visibility = View.GONE
        }




//        return inflater.inflate(R.layout.fragment_nurse_list, container, false)
        return binding.root
    }

}