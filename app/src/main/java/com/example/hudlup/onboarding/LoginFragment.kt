package com.example.hudlup.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hudlup.R
import com.example.hudlup.databinding.FragmentLoginBinding
import com.example.hudlup.util.TextValidator


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding ? = null
    private lateinit var viewModel: LoginViewModel
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtons(view : View){
        binding.forgotMyPasswordBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_forgottenPasswordFragment)
        }
        binding.signUpBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginBtn.setOnClickListener {
            //TODO: abstract into view model
            var result = false;
            if (binding.emailEditTxt.text.isEmpty()){
                binding.emailEditTxt.setError("Must not be empty")
            }else if (TextValidator.isEmailAddress(binding.emailEditTxt.text.toString())){
                binding.emailEditTxt.setError("Must be a valid email address")
            }
            if (binding.passwordEditTxt.text.isEmpty()){
                binding.passwordEditTxt.setError("Must not be empty")
            }


        }
    }

    companion object TAG {
        const val TAG: String = "LoginFragment"
    }



}