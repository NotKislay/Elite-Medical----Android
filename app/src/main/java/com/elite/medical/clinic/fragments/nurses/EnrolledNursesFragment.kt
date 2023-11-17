package com.elite.medical.clinic.fragments.nurses

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.clinic.adapter.EnrolledNursesAdapter
import com.elite.medical.databinding.ActivityEnrolledNursesBinding
import com.elite.medical.databinding.FragmentEnrolledNursesBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.apis.clinic.sidemenu.NursesClinicAPIs
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse

class EnrolledNursesFragment : Fragment() {

    private lateinit var binding: FragmentEnrolledNursesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEnrolledNursesBinding.inflate(inflater, container, false)

        populateList()


        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

    private fun populateList() {
        NursesClinicAPIs.getNurseEnrolled(object :
            NursesClinicAPIs.Companion.NursesEnrolledCallback {
            override fun onListReceived(nurses: List<Nurse>) {
                binding.loader.visibility = View.GONE
                if (nurses.isNotEmpty()) {
                    val adapter = EnrolledNursesAdapter(nurses, requireContext())
                    binding.rvEnrolledNurses.adapter = adapter
                } else {
                    val dialog = Dialog(requireContext())
                    val dialogBinding = ModalLayoutClinicDetailsMoreBinding.inflate(layoutInflater)
                    dialog.setContentView(dialogBinding.root)

                    dialogBinding.tvMoreDetails.text = "No Nurses Enrolled !"
                    dialogBinding.btnReviewsModal.visibility = View.GONE
                    dialogBinding.btnNurseAssocModal.visibility = View.GONE
                    dialogBinding.divider1.visibility = View.GONE
                    dialogBinding.divider2.visibility = View.GONE
                    dialogBinding.btnCancelModal.text = "Go Back"
                    dialogBinding.btnCancelModal.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

                    dialog.show()
                }
            }

        })
    }


}