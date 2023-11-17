package com.elite.medical

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.elite.medical.databinding.FragmentClockInOutBinding
import javax.xml.transform.URIResolver

class ClockInOutFragment : Fragment() {

    private lateinit var binding :  FragmentClockInOutBinding
    private val CAMERA_PERMISSION_CODE = 100
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentClockInOutBinding.inflate(inflater,container,false)
        binding.cameraBtn.setOnClickListener { launchCamera() }


        return binding.root
    }

    private fun launchCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)
        //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri) // Save captured image to a file
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
        startActivityForResult(cameraIntent, CAMERA_PERMISSION_CODE)
//        resultLauncher.launch(cameraIntent)
    }

    //new implementation
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {


            val result: Intent? = result.data
            val photo = result!!.extras!!["data"] as Bitmap?
            binding.imageDisplayArea.setImageBitmap(photo)
        }
    }

    private var contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imageDisplayArea.setImageURI(it)
    }

    //deprecated
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == CAMERA_PERMISSION_CODE) {

            val photo = data!!.extras!!["data"] as Bitmap?

            binding.imageDisplayArea.setImageBitmap(photo)
        }

    }

}