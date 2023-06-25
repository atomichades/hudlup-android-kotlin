package com.example.hudlup.onboarding

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.hudlup.R
import com.example.hudlup.databinding.FragmentLoginBinding
import com.example.hudlup.util.TextValidator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.CrashlyticsRegistrar
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport


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
            if (checkTextFieldsAreValid()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailEditTxt.text.toString(),binding.passwordEditTxt.text.toString()).addOnCompleteListener {
                    task ->
                    if (task.isSuccessful){
                         animateSignInBtnSuccessful{
                             //TODO: Grab user data from DB
                             view.findNavController().navigate(R.id.action_loginFragment_to_hub)
                         }
                    }else{
                        binding.error.setText("Error: "+task.exception)
                        FirebaseCrashlytics.getInstance().log(task.exception.toString())
                        binding.error.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    fun animateSignInBtnSuccessful(callback: () -> Unit){
        binding.loginBtn.isEnabled = false // Disable button
        binding.tickImageView.visibility = View.INVISIBLE
        binding.progressBar.progress = 0 // reset progress
        binding.progressBar.visibility = View.VISIBLE
        val anim = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100)
        anim.duration = 2000 // Set the desired animation duration
        binding.loginBtn.text = "Logging in..."
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                binding.progressBar.visibility = View.GONE // Hide the progress bar
                binding.loginBtn.text = "" // Clear the button text
                binding.tickImageView.visibility = View.VISIBLE // Make the tick visible
            }
        })
        val interpolator = LinearInterpolator()
        anim.interpolator = interpolator
        anim.start()
        // Simulating a delay of 1 second
        Handler(Looper.getMainLooper()).postDelayed({
            callback.invoke()
        }, 2000)
    }

    fun checkTextFieldsAreValid() :Boolean{
        var result = true;
        if (binding.emailEditTxt.text.isEmpty()){
            binding.emailEditTxt.error = "Must not be empty"
            result = false
        }else if (!TextValidator.isEmailAddress(binding.emailEditTxt.text.toString())){
            binding.emailEditTxt.error = "Must be a valid email address"
            result = false
        }
        if (binding.passwordEditTxt.text.isEmpty()){
            binding.passwordEditTxt.error = "Must not be empty"
            result = false
        }
        return result
    }

    companion object TAG {
        const val TAG: String = "LoginFragment"
    }



}