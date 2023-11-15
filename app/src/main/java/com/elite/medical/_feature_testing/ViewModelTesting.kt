package com.elite.medical._feature_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elite.medical.R
import com.elite.medical._feature_testing.ui.main.ViewModelTestingFragment

class ViewModelTesting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_testing)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewModelTestingFragment.newInstance())
                .commitNow()
        }
    }
}