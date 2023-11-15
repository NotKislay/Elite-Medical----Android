package com.elite.medical.clinic.ui.sidemenu.nurses

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.clinic.adapter.SearchAvailableNursesAdapter
import com.elite.medical.databinding.ActivityEnrolledNursesBinding
import com.elite.medical.databinding.ActivitySearchNursesBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.apis.clinic.sidemenu.NursesClinicAPIs
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse

class ActivitySearchNurses : AppCompatActivity() {

    private lateinit var binding: ActivitySearchNursesBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_nurses)
        binding.btnBack.setOnClickListener { finish() }

        spinnerCity = binding.cityFilter
        spinnerLicenceType = binding.licenceTypeFilter

        recyclerView = binding.rvAvlblNurses

        val spinnerAdapterCity = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, cityFilter
        )

        val spinnerAdapterLicenceType = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, licenceTypeFilter
        )

        spinnerCity.adapter = spinnerAdapterCity
        spinnerLicenceType.adapter = spinnerAdapterLicenceType



        populateList()

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
                        SearchAvailableNursesAdapter(nurses, this@ActivitySearchNurses)
                    recyclerView.adapter = recyclerViewAdapter


                    spinnerCity.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long
                            ) {
                                if (p2 != 0) {
                                    val filteredList = mainList.filter {
                                        it.city.contains(cityFilter.elementAt(p2))
                                    } as MutableList<Nurse>
                                    binding.tvNoData.isVisible = filteredList.isEmpty()
                                    recyclerViewAdapter.filterList(filteredList)
                                } else {
                                    binding.tvNoData.isVisible = false
                                    recyclerViewAdapter.filterList(mainList)
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
                                    val filteredList = mainList.filter {
                                        it.licenseType.contains(licenceTypeFilter.elementAt(p2))
                                    } as MutableList<Nurse>
                                    binding.tvNoData.isVisible = filteredList.isEmpty()
                                    recyclerViewAdapter.filterList(filteredList)
                                } else {
                                    binding.tvNoData.isVisible = false
                                    recyclerViewAdapter.filterList(mainList)
                                }

                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {}
                        }


                } else {
                    val dialog = Dialog(this@ActivitySearchNurses)
                    val dialogBinding = ModalLayoutClinicDetailsMoreBinding.inflate(layoutInflater)
                    dialog.setContentView(dialogBinding.root)

                    dialogBinding.tvMoreDetails.text = "No Nurses Enrolled !"
                    dialogBinding.btnReviewsModal.visibility = View.GONE
                    dialogBinding.btnNurseAssocModal.visibility = View.GONE
                    dialogBinding.divider1.visibility = View.GONE
                    dialogBinding.divider2.visibility = View.GONE
                    dialogBinding.btnCancelModal.text = "Go Back"
                    dialogBinding.btnCancelModal.setOnClickListener { finish() }

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

        val filteredList = mainList.filter { items ->
            (items.city.contains(selectedCity, ignoreCase = true)) && (items.licenseType.contains(
                selectedLicenseType, ignoreCase = true
            ))
        }
        //todo currently not working
        /*if (!filteredList.isNullOrEmpty()) {
            binding.textabovervlistofavalblnurse.text = "List of All Available Nurses"
            binding.textabovervlistofavalblnurse.apply {
                textSize = 18f
            }
            println("lis is $filteredList")


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