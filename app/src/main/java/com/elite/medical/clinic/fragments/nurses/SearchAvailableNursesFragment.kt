package com.elite.medical.clinic.fragments.nurses

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.clinic.adapter.SearchAvailableNursesAdapter
import com.elite.medical.clinic.viewmodels.sidemenu.SearchJobsViewModel
import com.elite.medical.databinding.FragmentSearchAvailableNursesBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.SearchNurseModel


class SearchAvailableNursesFragment : Fragment() {

    private lateinit var binding: FragmentSearchAvailableNursesBinding
    private val viewModel by viewModels<SearchJobsViewModel>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: SearchAvailableNursesAdapter

    private var cities = mutableListOf<String>()
    private var licenceTypeFilter =
        mutableListOf("Select Licence Type", "C.M.T", "C.N.A", "L.P.N", "R.N", "P.A", "N.P")
    private lateinit var spinnerCity: Spinner
    private lateinit var spinnerLicenceType: Spinner
    private lateinit var mainList: MutableList<Nurse>
    private lateinit var tempList: MutableList<Nurse>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchAvailableNursesBinding.inflate(inflater, container, false)
        viewModel.getAvailableSearchNurses()



        spinnerCity = binding.cityFilter
        spinnerLicenceType = binding.licenceTypeFilter
        recyclerView = binding.listview


        val spinnerAdapterLicenceType = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, licenceTypeFilter
        )
        spinnerLicenceType.adapter = spinnerAdapterLicenceType



        viewModel.searchNurseCallback =
            { data: SearchNurseModel?, error: GenericSuccessErrorModel? ->

                if (data != null) {
                    binding.loader.isVisible = false
                    cities = data.cities.toMutableList()
                    cities.add(0, "Select City")

                    populateCityFilter(cities)
                    populateNursesList(data.nurses)

                    callFilterListener()
                } else {
                    Toast.makeText(requireContext(), error?.message, Toast.LENGTH_SHORT).show()
                    activity?.onBackPressedDispatcher?.onBackPressed()
                }
            }


        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

    private fun callFilterListener() {

        spinnerCity.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long
                ) {
                    applyFilters()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

        spinnerLicenceType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long
                ) {
                    applyFilters()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }


    }

    private fun applyFilters() {
        val cityFilterIndex = spinnerCity.selectedItemPosition
        val licenceTypeFilterIndex = spinnerLicenceType.selectedItemPosition

        tempList = mainList

        if (cityFilterIndex != 0) {
            val filteredByCity =
                tempList.filter { it.city.contains(cities.elementAt(cityFilterIndex)) }
            tempList = filteredByCity as MutableList<Nurse>
        }


        if (licenceTypeFilterIndex != 0) {
            var licenceTypeKey = licenceTypeFilter.elementAt(
                licenceTypeFilterIndex
            )
            if (licenceTypeKey == "C.M.T") {
                var filteredByLicenseType =
                    tempList.filter { it.licenseType.contains("C.M.T") || it.licenseType.contains("cmt") }

                tempList = filteredByLicenseType as MutableList<Nurse>

            } else {
                val filteredByLicenseType =
                    tempList.filter {
                        it.licenseType.contains(
                            licenceTypeKey
                        )
                    }
                tempList = filteredByLicenseType as MutableList<Nurse>
            }

        }


        binding.tvNoData.isVisible = tempList.isEmpty()
        recyclerViewAdapter.filterList(tempList)
    }

    private fun populateCityFilter(cities: MutableList<String>) {
        if (cities.isEmpty()) {
            Toast.makeText(requireContext(), "City Filter Not Available", Toast.LENGTH_SHORT).show()
        } else {
            val spinnerAdapterCity = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_dropdown_item, cities
            )
            spinnerCity.adapter = spinnerAdapterCity
        }
    }

    private fun populateNursesList(nurses: List<Nurse>) {

        mainList = nurses as MutableList<Nurse>

        if (nurses.isNotEmpty()) {

            recyclerViewAdapter = SearchAvailableNursesAdapter(nurses, requireContext())
            recyclerView.adapter = recyclerViewAdapter

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


}