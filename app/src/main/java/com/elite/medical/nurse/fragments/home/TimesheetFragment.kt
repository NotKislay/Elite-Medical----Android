package com.elite.medical.nurse.fragments.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.databinding.FragmentTimesheetBinding
import com.elite.medical.databinding.ModalLayoutBinding
import com.elite.medical.nurse.adapters.home.TimeSheetAdapter
import com.elite.medical.nurse.viewmodels.UserNurseMainViewModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.nurse.home.NurseTimeSheetModel

class TimesheetFragment : Fragment() {

    private lateinit var binding: FragmentTimesheetBinding
    private lateinit var dialogBinding: ModalLayoutBinding
    private lateinit var viewmodel: UserNurseMainViewModel
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTimesheetBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(this)[UserNurseMainViewModel::class.java]
        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

        viewmodel.getTimeSheets()
        setTimeSheetData()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setTimeSheetData() {
        viewmodel.timeSheetCallback =
            { timesheets: List<NurseTimeSheetModel.Timesheet>?, genericSuccessErrorModel: GenericSuccessErrorModel? ->

                if (timesheets == null) {
                    Toast.makeText(
                        requireContext(),
                        genericSuccessErrorModel?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    activity?.onBackPressedDispatcher?.onBackPressed()
                } else {

                    if (timesheets.isEmpty()) {
                        binding.loader.visibility = View.GONE
                        dialogBinding = ModalLayoutBinding.inflate(layoutInflater)
                        dialog = Dialog(requireContext())
                        dialog.setContentView(dialogBinding.root)

                        val heading = dialogBinding.heading
                        val btn1 = dialogBinding.btn1
                        val btn2 = dialogBinding.btn2
                        val btn3 = dialogBinding.btn3
                        val btn4 = dialogBinding.btn4
                        val btn5 = dialogBinding.btn5


                        btn1.text = "No Timesheet"
                        btn2.isVisible = false
                        btn3.isVisible = false
                        btn4.isVisible = false
                        btn5.text = "Go back"
                        btn5.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

                        dialog.show()
                    } else {
                        val rvAdapter = TimeSheetAdapter(timesheets)
                        binding.rvTimedata.adapter = rvAdapter
                        binding.rvTimedata.layoutManager = LinearLayoutManager(requireContext())
                        binding.loader.isVisible = false
                    }
                }

            }
    }
}