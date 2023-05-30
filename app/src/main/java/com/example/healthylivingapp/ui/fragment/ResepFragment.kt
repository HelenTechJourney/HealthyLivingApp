package com.example.healthylivingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.healthylivingapp.databinding.FragmentResepBinding
import com.example.healthylivingapp.ui.viewmodel.ResepViewModel

class ResepFragment : Fragment() {
    private var _binding: FragmentResepBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[ResepViewModel::class.java]

        _binding = FragmentResepBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textResep
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}