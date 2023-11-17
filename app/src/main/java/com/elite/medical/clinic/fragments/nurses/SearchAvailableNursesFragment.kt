package com.elite.medical.clinic.fragments.nurses

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.clinic.adapter.SearchAvailableNursesAdapter
import com.elite.medical.databinding.FragmentSearchAvailableNursesBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.apis.clinic.sidemenu.NursesClinicAPIs
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse


class SearchAvailableNursesFragment : Fragment() {

    private lateinit var binding: FragmentSearchAvailableNursesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: SearchAvailableNursesAdapter
    private var cityFilter = mutableListOf(
        "Select Location",
        "New York City",
        "New Zealand",
    )
    private var licenceTypeFilter = mutableListOf(

        "Select Licence Type", "C.M.T", "C.N.A", "L.P.N", "R.N", "P.A", "N.P", "cmt"
    )
    private lateinit var spinnerCity: Spinner
    private lateinit var spinnerLicenceType: Spinner
    private lateinit var mainList: MutableList<Nurse>
    private lateinit var globalFilteredList: MutableList<Nurse>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchAvailableNursesBinding.inflate(inflater, container, false)


        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

        spinnerCity = binding.cityFilter
        spinnerLicenceType = binding.licenceTypeFilter

        recyclerView = binding.rvAvlblNurses

        val spinnerAdapterCity = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, cityFilter
        )

        val spinnerAdapterLicenceType = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, licenceTypeFilter
        )

        spinnerCity.adapter = spinnerAdapterCity
        spinnerLicenceType.adapter = spinnerAdapterLicenceType



        populateList()


        return binding.root
    }


    private fun populateList() {

        NursesClinicAPIs.getAvailableSearchNurses(object :
            NursesClinicAPIs.Companion.NursesAvailableCallback {
            override fun onListReceived(nurses: List<Nurse>) {

                binding.loader.visibility = View.GONE
                if (nurses.isNotEmpty()) {
                    mainList = nurses as MutableList<Nurse>
                    globalFilteredList = nurses as MutableList<Nurse>
                    recyclerViewAdapter =
                        SearchAvailableNursesAdapter(nurses, requireContext())
                    recyclerView.adapter = recyclerViewAdapter


                    spinnerCity.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long
                            ) {
                                if (p2 != 0) {
                                    setFilteredList()
//                                    val filteredList = mainList.filter {
//                                        it.city.contains(cityFilter.elementAt(p2))
//                                    } as MutableList<Nurse>
//                                    binding.tvNoData.isVisible = filteredList.isEmpty()
//                                    recyclerViewAdapter.filterList(filteredList)
                                } else {
                                    setFilteredList()
                                    //binding.tvNoData.isVisible = false
//                                    recyclerViewAdapter.filterList(mainList)
                                }
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {}
                        }

                    spinnerLicenceType.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long
                            ) {
                                if (p2 != 0) {
                                    setFilteredList()
//                                    val filteredList = mainList.filter {
//                                        it.licenseType.contains(licenceTypeFilter.elementAt(p2))
//                                    } as MutableList<Nurse>
//                                    binding.tvNoData.isVisible = filteredList.isEmpty()
//                                    recyclerViewAdapter.filterList(filteredList)
                                } else {
                                    setFilteredList()
                                    //binding.tvNoData.isVisible = false
//                                    recyclerViewAdapter.filterList(mainList)
                                }

                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {}
                        }


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

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun setFilteredList() {
        val selectedCity = cityFilter.elementAt(spinnerCity.selectedItemPosition)
        val selectedLicenseType =
            licenceTypeFilter.elementAt(spinnerLicenceType.selectedItemPosition)
        var filteredList: List<Nurse>
        println("Selected data: $selectedCity and $selectedLicenseType")


        if (selectedCity == "Select Location" && selectedLicenseType != "Select Licence Type") {
            filteredList = mainList.filter { items ->
                (items.city.contains(selectedCity))
            }
        } else if (selectedCity != "Select Location" && selectedLicenseType == "Select Licence Type"){
            filteredList = mainList.filter { items ->
                (items.licenseType.contains(selectedLicenseType))
            }
        }
        else{
            filteredList =mainList.filter { items->
                (items.city.contains(selectedCity))
            }
            filteredList= filteredList.filter { items->
                (items.licenseType.contains(selectedLicenseType))
            }
        }
//            filteredList = mainList.filter { items ->
//                (items.city.contains(
//                    selectedCity,
//                    ignoreCase = true
//                )) && (items.licenseType.contains(
//                    selectedLicenseType, ignoreCase = true
//                ))
//            }
        //todo currently not working
        /*if (!filteredList.isNullOrEmpty()) {
            binding.textabovervlistofavalblnurse.text = "List of All Available Nurses"
            binding.textabovervlistofavalblnurse.apply {
                textSize = 18f
            }


        } else {
            binding.textabovervlistofavalblnurse.text = "No Records !"
            binding.textabovervlistofavalblnurse.apply {
                textSize = 27f
            }
            binding.rvAvlblNurses.isVisible = false
        }
        */
        recyclerViewAdapter.filterList(filteredList)
        recyclerViewAdapter.notifyDataSetChanged()
    }


}