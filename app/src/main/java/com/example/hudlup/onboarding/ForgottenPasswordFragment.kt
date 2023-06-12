package com.example.hudlup.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hudlup.R
import com.example.hudlup.databinding.FragmentForgottenPasswordBinding
import com.example.hudlup.databinding.FragmentLoginBinding

class ForgottenPasswordFragment : Fragment() {
    private var _binding: FragmentForgottenPasswordBinding ? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgottenPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object TAG {
        const val TAG: String = "ForgottenPasswordFragmenet"
    }
}