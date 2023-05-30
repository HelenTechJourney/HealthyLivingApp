package com.example.healthylivingapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.healthylivingapp.databinding.FragmentForm2Binding
import com.example.healthylivingapp.ui.activity.MainActivity

class Form2Fragment : Fragment() {
    private lateinit var binding: FragmentForm2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentForm2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gender = arguments?.getString("gender")
        val age = arguments?.getInt("age", 0)
        val height = arguments?.getDouble("height", 0.0)
        val weight = arguments?.getDouble("weight", 0.0)
        val daily = arguments?.getString("dailyActivity")

        val bmr = calculateBMR(gender, age, height, weight)
        val bmrAct= activityBMR(bmr,daily)
        binding.resultBmr.text = bmrAct.toString()

        val bmi = calculateBMI(height, weight)
        binding.resultBmi.text = bmi.toString()

        val weightRecommendation = calculateWeightRecommendation(height)
        binding.resultWeightRecomend.text = weightRecommendation

        binding.btnSelesai.setOnClickListener {
            val weightTarget = binding.etWeight.text.toString()

            if (weightTarget.isNotBlank()) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("weightTarget", weightTarget)
                startActivity(intent)
            } else {
                Toast.makeText(activity, "Harap isi target berat badan", Toast.LENGTH_SHORT).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPressed()
            }
        })
    }

    private fun handleBackPressed() {
        val fragmentManager = parentFragmentManager
        fragmentManager.popBackStack()
    }

    private fun calculateBMR(gender: String?, ageInYears: Int?, heightInCm: Double?, weightInKg: Double?): Double {
        val ageInMonths = ageInYears!! * 12

        val bmr: Double = if (gender == "Laki-laki") {
            66 + (13.75 * weightInKg!!) + (5 * heightInCm!!) - (6.75 * ageInMonths)
        } else {
            655 + (9.56 * weightInKg!!) + (1.85 * heightInCm!!) - (4.68 * ageInMonths)
        }
        return activityBMR(bmr, daily = String())
    }

    private fun activityBMR(bmr: Double, daily: String?): Double {
        return when (daily) {
            "Tidak aktif" -> bmr * 1.2
            "Sedikit aktif" -> bmr * 1.375
            "Aktif" -> bmr * 1.55
            "Sangat aktif" -> bmr * 1.725
            "Sangat aktif sekali" -> bmr * 1.9
            else -> bmr
        }
    }
    private fun calculateBMI(heightInCm: Double?, weightInKg: Double?): Double {
        val heightInMeter = heightInCm!! / 100
        return weightInKg!! / (heightInMeter * heightInMeter)
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    private fun calculateWeightRecommendation(heightInCm: Double?): String {
        val heightInMeter = heightInCm!! / 100
        val weightMin = (heightInMeter * heightInMeter) * 18.5
        val weightMax = (heightInMeter * heightInMeter) * 24.9
        return "${weightMin.format(1)} - ${weightMax.format(1)} kg"
    }
}