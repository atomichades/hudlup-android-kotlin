package com.example.hudlup.onboarding

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.databinding.FragmentSignUpBinding
import com.example.hudlup.util.SharedPreferenceManager
import com.example.hudlup.util.TextValidator
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
        // Setup sign up button
        binding.signUpBtn.setOnClickListener {
            if (checkAndSetUIFieldsIfErrored()){
                Firebase.auth.createUserWithEmailAndPassword(binding.emailEditTxt.text.toString(), binding.password1EditTxt.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        animateSignInBtnSuccessful()
                        task.result.user?.sendEmailVerification()?.addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                Toast.makeText(context,"All done! We just need you to verify your email before you continue", Toast.LENGTH_LONG).show()
                                SharedPreferenceManager.StoreUserDetailsOnSignUp(binding.firstnameEditTxt.text.toString(),binding.lastnameEditTxt.text.toString(),binding.ageEditTxt.text.toString().toInt())
                                NavHostFragment.findNavController(this).popBackStack()
                            }
                        }

                    }else {
                        Toast.makeText(context,"There was an error with your sign up: ${task.exception}", Toast.LENGTH_LONG).show()
                        binding.signUpBtn.isEnabled = true
                    }
                 }
            }
        }
    }

    fun animateSignInBtnSuccessful(){
        binding.signUpBtn.isEnabled = false // Disable button
        binding.tickImageView.visibility = View.INVISIBLE
        binding.progressBar.progress = 0 // reset progress
        binding.progressBar.visibility = View.VISIBLE
        val anim = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100)
        anim.duration = 2000 // Set the desired animation duration
        binding.signUpBtn.text = "Signing up..."
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                binding.progressBar.visibility = View.GONE // Hide the progress bar
                binding.signUpBtn.text = "" // Clear the button text
                binding.tickImageView.visibility = View.VISIBLE // Make the tick visible
            }
        })
        val interpolator = LinearInterpolator()
        anim.interpolator = interpolator
        anim.start()
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