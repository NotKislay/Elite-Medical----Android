package com.elite.medical

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.edit
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.RetrofitInterfaceNurse
import com.elite.medical.utils.Constants
import com.elite.medical.utils.ConstantsClinic
import com.elite.medical.utils.ConstantsNurse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EliteMedical : Application() {

    enum class ShardPrefsEnum {
        LOGIN_TOKEN_CLINIC, LOGIN_TOKEN_ADMIN, LOGIN_TOKEN_NURSE
    }


    override fun onCreate() {
        super.onCreate()


        shardPrefsAdmin =
            getSharedPreferences(ShardPrefsEnum.LOGIN_TOKEN_ADMIN.toString(), Context.MODE_PRIVATE)
        shardPrefsClinic =
            getSharedPreferences(ShardPrefsEnum.LOGIN_TOKEN_CLINIC.toString(), Context.MODE_PRIVATE)
        shardPrefsNurse =
            getSharedPreferences(ShardPrefsEnum.LOGIN_TOKEN_NURSE.toString(), Context.MODE_PRIVATE)


        val clientClinic = OkHttpClient.Builder().addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("Authorization", "Bearer $AuthTokenClinic")
                    .build()
            chain.proceed(request)
        }.addInterceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.code == 401) {
                updateClinicToken(null)
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("token", "null")
                startActivity(intent)
            }
            response
        }.build()

        val clientAdmin = OkHttpClient.Builder().addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("Authorization", "Bearer $AuthTokenAdmin")
                    .build()
            chain.proceed(request)
        }.addInterceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.code == 401) {
                updateAdminToken(null)

                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("token", "null")
                startActivity(intent)

            }
            response
        }.build()

        val clientNurse = OkHttpClient.Builder().addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("Authorization", "Bearer $AuthTokenNurse")
                    .build()
            chain.proceed(request)
        }.addInterceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.code == 401) {
                updateNurseToken(null)
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("token", "null")
                startActivity(intent)
            }


            response
        }.build()


        retrofitClinic = Retrofit.Builder().baseUrl(ConstantsClinic.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(clientClinic).build()
            .create(RetrofitInterfaceClinic::class.java)

        retrofitAdmin = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(clientAdmin).build()

        retrofitNurse = Retrofit.Builder().baseUrl(ConstantsNurse.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(clientNurse).build()
            .create(RetrofitInterfaceNurse::class.java)


    }

    companion object {
        lateinit var retrofitClinic: RetrofitInterfaceClinic
        lateinit var retrofitAdmin: Retrofit
        lateinit var retrofitNurse: RetrofitInterfaceNurse
        private lateinit var shardPrefsAdmin: SharedPreferences
        private lateinit var shardPrefsClinic: SharedPreferences
        private lateinit var shardPrefsNurse: SharedPreferences

        fun updateAdminToken(authToken: String?) {
            shardPrefsAdmin.edit {
                putString(ShardPrefsEnum.LOGIN_TOKEN_ADMIN.toString(), authToken)
                apply()
            }
        }

        fun updateClinicToken(authToken: String?) {
            shardPrefsClinic.edit {
                putString(ShardPrefsEnum.LOGIN_TOKEN_CLINIC.toString(), authToken)
                apply()
            }
        }

        fun updateNurseToken(authToken: String?) {
            shardPrefsNurse.edit {
                putString(ShardPrefsEnum.LOGIN_TOKEN_NURSE.toString(), authToken)
                apply()
            }
        }

        val AuthTokenAdmin: String?
            get() {
                return shardPrefsAdmin.getString(ShardPrefsEnum.LOGIN_TOKEN_ADMIN.toString(), null)
            }

        val AuthTokenClinic: String?
            get() {
                return shardPrefsClinic.getString(
                    ShardPrefsEnum.LOGIN_TOKEN_CLINIC.toString(), null
                )
            }

        val AuthTokenNurse: String?
            get() {
                return shardPrefsNurse.getString(
                    ShardPrefsEnum.LOGIN_TOKEN_NURSE.toString(), null
                )
            }
    }

}