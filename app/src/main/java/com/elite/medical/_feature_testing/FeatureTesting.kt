package com.elite.medical._feature_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.elite.medical.databinding.ActivityFeatureTestingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeatureTesting : AppCompatActivity() {

    private lateinit var binding: ActivityFeatureTestingBinding

    private lateinit var dateTextView: TextView

    val planets_array =
        arrayOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeatureTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pickImg.setOnClickListener {
            pickMedia.launch(arrayOf("*/*"))
        }

    }


    // Registers a photo picker activity launcher in single-select mode.
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            binding.imageView2.setImageURI(it)

            CoroutineScope(Dispatchers.Main).launch {

//                binding.pickImg.text = Auth.createNurse().toString()

            }

        }


}