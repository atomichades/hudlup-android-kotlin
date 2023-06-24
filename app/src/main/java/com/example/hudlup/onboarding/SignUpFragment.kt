package com.example.hudlup.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.databinding.FragmentSignUpBinding
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
                Firebase.auth.createUserWithEmailAndPassword(binding.firstnameEditTxt.text.toString(), binding.password1EditTxt.text.toString()).addOnCompleteListener { task ->{
                    if (task.isSuccessful){

                    }else {

                    }
                } }
            }
        }
    }

    fun checkAndSetUIFieldsIfErrored() : Boolean {
        var result =true
        if (binding.firstnameEditTxt.text.isEmpty()||TextValidator.hasSpecialCharacters(binding.firstnameEditTxt.text.toString())){
            binding.firstnameEditTxt.error = "You must enter a valid firstname"
            result = false
        }
        if (binding.lastnameEditTxt.text.isEmpty()||TextValidator.hasSpecialCharacters(binding.lastnameEditTxt.text.toString())){
            binding.lastnameEditTxt.error = "You must enter a valid lastname"
            result = false
        }
        if (binding.ageEditTxt.text.isEmpty()){
            binding.ageEditTxt.error = "You must enter your age"
            result = false
        }else if (viewModel.checkAgeIsOver18(binding.ageEditTxt.toString())){
            binding.ageEditTxt.error = "You must be over 18"
        }
        if (binding.emailEditTxt.text.isEmpty()){
            binding.emailEditTxt.error = "You must enter your email address"
            result = false
        }
        if (TextValidator.isEmailAddress(binding.emailEditTxt.text.toString())){
            binding.emailEditTxt.error = "This does not look like a valid email"
            result = false
        }
        if (binding.password1EditTxt.text.isEmpty()){
            binding.password1EditTxt.error = "You must enter a password"
        } else if (binding.password2EditTxt.text.isEmpty()){
            binding.password2EditTxt.error = "You must enter a password"
        }else if (!viewModel.checkPasswordMatches(binding.password1EditTxt.text.toString(),binding.password2EditTxt.text.toString())){
            binding.password1EditTxt.error = "Your passwords do not match"
            binding.password2EditTxt.error = "Your passwords do not match"
        }

        return result

    }

    companion object TAG {
        const val TAG: String = "SignUpFragment"
    }
}