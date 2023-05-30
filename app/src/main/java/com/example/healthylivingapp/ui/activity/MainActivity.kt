package com.example.healthylivingapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.healthylivingapp.R
import com.example.healthylivingapp.databinding.ActivityMainBinding
import com.example.healthylivingapp.ui.fragment.JelajahiFragment
import com.example.healthylivingapp.ui.fragment.ProfileFragment
import com.example.healthylivingapp.ui.fragment.ResepFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.navigation_jelajahi -> replaceFragment(JelajahiFragment())
                R.id.navigation_resep -> replaceFragment(ResepFragment())
                R.id.navigation_profile -> replaceFragment(ProfileFragment())

                else ->{}
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        fragmentTransaction.commit()
    }
}