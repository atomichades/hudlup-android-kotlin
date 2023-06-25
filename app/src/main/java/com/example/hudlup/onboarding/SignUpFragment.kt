package com.example.hudlup.onboarding

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.databinding.FragmentSignUpBinding
import com.example.hudlup.util.SharedPreferenceManager
import com.example.hudlup.util.TextValidator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClicks()
        _binding?.toolbar?.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupOnClicks(){
        binding.signUpBtn.setOnClickListener {
            if (checkAndSetUIFieldsIfErrored()){
                Firebase.auth.createUserWithEmailAndPassword(binding.firstnameEditTxt.text.toString(), binding.password1EditTxt.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        SharedPreferenceManager.StoreUserDetailsOnSignUp(binding.firstnameEditTxt.text.toString(),binding.lastnameEditTxt.text.toString(),binding.ageEditTxt.text.toString().toInt())
                        NavHostFragment.findNavController(this).popBackStack()
                    }else {

                    }
                 }
            }
        }
    }

    fun checkAndSetUIFieldsIfErrored() : Boolean {
        var result = true
        with(binding) {
            if (firstnameEditTxt.text.isEmpty() || TextValidator.hasSpecialCharacters(firstnameEditTxt.text.toString())) {
                firstnameEditTxt.error = "You must enter a valid firstname"
                result = false
            }
            if (lastnameEditTxt.text.isEmpty() || TextValidator.hasSpecialCharacters(lastnameEditTxt.text.toString())) {
                lastnameEditTxt.error = "You must enter a valid lastname"
                result = false
            }
            if (ageEditTxt.text.isEmpty()) {
                ageEditTxt.error = "You must enter your age"
                result = false
            } else if (viewModel.checkAgeIsOver18(ageEditTxt.toString())) {
                ageEditTxt.error = "You must be over 18"
            }

            if (emailEditTxt.text.isEmpty()) {
                emailEditTxt.error = "You must enter your email address"
                result = false
            } else if (!TextValidator.isEmailAddress(emailEditTxt.text.toString())) {
                emailEditTxt.error = "This does not look like a valid email"
                result = false
            }

            if (password1EditTxt.text.isEmpty()) {
                password1EditTxt.error = "You must enter a password"
            } else if (password2EditTxt.text.isEmpty()) {
                password2EditTxt.error = "You must enter a password"
            } else if (!viewModel.checkPasswordMatches(password1EditTxt.text.toString(), password2EditTxt.text.toString())) {
                password1EditTxt.error = "Your passwords do not match"
                password2EditTxt.error = "Your passwords do not match"
            }
        }

        return result

    }

    companion object TAG {
        const val TAG: String = "SignUpFragment"
    }
}