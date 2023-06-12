package com.example.hudlup.onboarding

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hudlup.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding ? = null
    private val binding get() = _binding!!
    val view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtons(){
        binding.forgotMyPasswordBtn.setOnClickListener {
        //TODO NAV TO FORGOTTEN PASSWORD
            findNavController().navigate(R.id.action_loginFragment_to_forgottenPasswordFragment)
        }

        binding.signUpBtn.setOnClickListener {
            //TODO NAV TO SIGNUP
        }
    }

    companion object TAG {
        const val TAG: String = "LoginFragment"
    }



}