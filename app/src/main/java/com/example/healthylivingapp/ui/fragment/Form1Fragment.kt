package com.example.healthylivingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.healthylivingapp.R
import com.example.healthylivingapp.databinding.FragmentForm1Binding

class Form1Fragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentForm1Binding
    private var gender: String? = null
    private var daily: String? = null
    private var age: Int = 0
    private var height: Double = 0.0
    private var weight: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForm1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val genderOptions = listOf("Laki-laki", "Perempuan")
        val genderAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderOptions)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = genderAdapter

        val activityOptions = listOf("Tidak aktif", "Sedikit aktif", "Aktif", "Sangat aktif", "Sangat aktif sekali")
        val activityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, activityOptions)
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.dailySpinner.adapter = activityAdapter
    }

    override fun onClick(v:View){
        if (v.id == R.id.btn_lanjutkan) {
            if (isFormValid()) {
            gender = binding.genderSpinner.selectedItem.toString()
            age = binding.etAge.text.toString().toInt()
            height = binding.etHeight.text.toString().toDouble()
            weight = binding.etWeight.text.toString().toDouble()
            daily = binding.dailySpinner.selectedItem.toString()

                val form2Fragment = Form2Fragment()
                val bundle = Bundle().apply {
                    putString("gender", gender)
                    putInt("age", age)
                    putDouble("height", height)
                    putDouble("weight", weight)
                    putString("dailyActivity", daily)
                }
                form2Fragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(
                        R.id.frame_container,
                        form2Fragment,
                        Form2Fragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            } else {
                Toast.makeText(requireContext(), "Harap isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isFormValid(): Boolean {
        val age = binding.etAge.text.toString()
        val height = binding.etHeight.text.toString()
        val weight = binding.etWeight.text.toString()

        return age.isNotBlank() && height.isNotBlank() && weight.isNotBlank()
    }
}