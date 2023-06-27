package com.example.hudlup.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.R
import com.example.hudlup.databinding.FragmentForgottenPasswordBinding
import com.example.hudlup.databinding.FragmentLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
        setupClickActions()
        _binding?.toolbar?.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupClickActions(){
        binding.resetBtn.setOnClickListener {
            if (binding.emailEditTxt.text.isEmpty()){
                binding.emailEditTxt.setError("You must enter an email address")
            }else {
                binding.para1.setText(R.string.passwordResetComplete)
                binding.para1.visibility = View.VISIBLE
                binding.resetBtn.animateSignInBtnSuccessful("Attempting to reset", 4000)
                Firebase.auth.sendPasswordResetEmail(binding.emailEditTxt.text.toString()).addOnCompleteListener {
                    Thread.sleep(4000)
                    NavHostFragment.findNavController(this).popBackStack()
            }
            }
        }
    }

    companion object TAG {
        const val TAG: String = "ForgottenPasswordFragment"
    }
}