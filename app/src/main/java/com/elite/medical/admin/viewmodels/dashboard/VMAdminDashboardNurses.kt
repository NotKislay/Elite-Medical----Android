package com.elite.medical.admin.viewmodels.dashboard

import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.NurseByUserIdModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VMAdminDashboardNurses:ViewModel() {







fun getNurseDetailsByID(id:String) {
    EliteMedical.retrofitAdmin.getNurseByUserId(id).enqueue(object : Callback<NurseByUserIdModel?> {
        override fun onResponse(
            call: Call<NurseByUserIdModel?>,
            response: Response<NurseByUserIdModel?>
        ) {

        }

        override fun onFailure(call: Call<NurseByUserIdModel?>, t: Throwable) {}
    })
}


}