package com.elite.medical.clinic.ui.sidemenu.nurses

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.nurses.more.ActivityReviewsByClinicToNurses
import com.elite.medical.databinding.ActivityEnrolledNurseDetailsBinding
import com.elite.medical.retrofit.apis.clinic.sidemenu.NursesClinicAPIs
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.EmploymentFromEnrNursByid
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.NurseFromEnrNurseDet
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.ReviewFromEnrNurseDet
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.properties.Delegates

class ActivityEnrolledNurseDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEnrolledNurseDetailsBinding
    private lateinit var reviewslist: List<ReviewFromEnrNurseDet>
    private lateinit var empenddate: String
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var selectedDate: Date
    private lateinit var formattedDate: String
    private var getid by Delegates.notNull<Int>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enrolled_nurse_details)
        binding.btnBack.setOnClickListener { finish() }
        binding.listViewEnrNursesDet.visibility = View.GONE


        getid = intent.getIntExtra("Nurse_id", 0)

        fetchEnrolledNurseByID(getid.toString())
        binding.btnTimesheet.setOnClickListener {
            val intent = Intent(this, TimesheetNurseFromClinic::class.java)
            intent.putExtra("ID", getid)
            startActivity(intent)
        }

        binding.moreBtnEnrNurseDetails.setOnClickListener {
            // Creating a custom dialog
            val customDialog = Dialog(this)
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

            // Customize the dialog components
            val cancelBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
            val terminateBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
            terminateBtn.text = "Terminate"
            terminateBtn.setTextColor(ContextCompat.getColor(this, R.color.md_red_A700))
            val reviewsBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)

            terminateBtn.setOnClickListener {
                customDialog.dismiss()
                terminateNurse(empenddate, getid.toString())
            }

            reviewsBtn.setOnClickListener {
                customDialog.dismiss()
                val intent = Intent(this, ActivityReviewsByClinicToNurses::class.java)

                intent.putExtra("NurseID", getid)
                intent.putParcelableArrayListExtra("ClinicReviewforNurse", ArrayList(reviewslist))
                startActivity(intent)//
            }

            cancelBtn.setOnClickListener {
                customDialog.dismiss()
            }
            customDialog.show()
        }
    }

    private fun fetchEnrolledNurseByID(id: String) {
        var jobtitle = ""
        var hiringstatus = ""
        empenddate = ""
        NursesClinicAPIs.getNurseEnrByid(id,
            object : NursesClinicAPIs.Companion.EnrolledNursesByIdCallback {
                override fun onEmploymentDetailsReceived(
                    empdetails: EmploymentFromEnrNursByid,
                    statusCode: Int?
                ) {

                    empenddate = empdetails.empEnd
                    jobtitle = empdetails.jobtitle
                    hiringstatus = if (empdetails.status == "hiring_approved") {
                        "Hired"
                    } else{
                        "Hiring Approval Pending"
                    }
                }

                override fun onNurseDetailsReceived(
                    nurses: NurseFromEnrNurseDet,
                    statusCode: Int?
                ) {

                    binding.listViewEnrNursesDet.visibility = View.VISIBLE
                    val arrayData = listOf(
                        "Name: ${nurses?.name}",
                        "Job Title: $jobtitle",
                        "Contact No.: ${nurses?.mobile}",
                        "Email: ${nurses?.email}",
                        "D.O.B.: ${nurses?.dob}",
                        "Address: ${nurses?.address}",
                        "License Type: ${nurses?.licenseType}",
                        "License Expiry: ${nurses?.licenseExpiry}",
                        "Experience: ${nurses?.experience}",
                        "Speciality: ${nurses?.speciality}",
                        "US Immigration Status: ${nurses?.usImmigrationStatus}",
                        "NCLEX Status: ${nurses?.nclexStatus}",
                        "CGFNS Status: ${nurses?.cgfnsStatus}",
                        "Count Shift: ${nurses?.shift}",
                        "Hiring  Status: $hiringstatus",
                        "Employment End Date: $empenddate"
                    )
                    binding.loader.visibility = View.GONE
                    val adapter = ArrayAdapter(
                        this@ActivityEnrolledNurseDetails,
                        R.layout.custom_single_item_textview,
                        arrayData
                    )
                    binding.listViewEnrNursesDet.adapter = adapter
                }

                override fun onReviewReceived(
                    reviews: List<ReviewFromEnrNurseDet>,
                    statusCode: Int?
                ) {
                    reviewslist = reviews
                }

            })
    }

    private fun terminateNurse(empenddate: String, id: String) {
        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)
        val cancelBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
        val title = customDialog.findViewById<TextView>(R.id.tv_more_details)
        title.text = "Set Notice Period"
        val datepickerBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
        datepickerBtn.text = "Date"
        val terminatebtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)
        terminatebtn.text = "Terminate"
        terminatebtn.setTextColor(ContextCompat.getColor(this, R.color.md_red_700))

        datepickerBtn.setOnClickListener {
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.time


                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                formattedDate = dateFormat.format(selectedDate)
                datepickerBtn.text = formattedDate
                datepickerBtn.setTextColor(ContextCompat.getColor(this, R.color.black))
                datepickerBtn.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
            }

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val style = AlertDialog.THEME_HOLO_LIGHT

            datePickerDialog = DatePickerDialog(this, style, dateSetListener, year, month, day)
            datePickerDialog.show()
        }


        terminatebtn.setOnClickListener {
            if (datepickerBtn.text == "Date") {
                Toast.makeText(this, "Please select a date...", Toast.LENGTH_SHORT).show()

            } else {
                //API CALL
                NursesClinicAPIs.terminatenursebyid(formattedDate,getid,object : NursesClinicAPIs.Companion.TerminateNurseCallback {
                    override fun onMsgReceived(message: String, code: Int) {
                        customDialog.dismiss()
                        Toast.makeText(this@ActivityEnrolledNurseDetails,message,Toast.LENGTH_SHORT).show()
                    }

                })

            }
        }

        cancelBtn.setOnClickListener { customDialog.dismiss() }



        customDialog.show()
    }


}