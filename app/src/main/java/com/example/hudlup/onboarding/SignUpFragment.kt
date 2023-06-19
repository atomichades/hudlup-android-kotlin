package com.example.hudlup.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.R
import com.example.hudlup.databinding.FragmentSignUpBinding
import com.example.hudlup.util.TextValidator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

private var _binding: FragmentSignUpBinding? = null
private val binding get() = _binding!!
class SignUpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            if (checkTextFieldsAreFull()){
                Firebase.auth.createUserWithEmailAndPassword(binding.emailEditTxt.toString(), binding.password2EditTxt.text.toString()).addOnCompleteListener {
                    task -> {
                        if (task.isSuccessful){
                            //TODO: Move to next activity
                        } else {
                            //TODO: show error message
                            binding.para1.setTextAppearance(R.style.error)
                            binding.para1.setText(task.result.toString())
                        }
                    }
                }
            }
        }
    }

    fun checkTextFieldsAreFull() : Boolean{
        var result = false
        if (binding.firstnameEditTxt.text.isEmpty()||TextValidator.hasSpecialCharacters(binding.firstnameEditTxt.text.toString())){
            binding.firstnameEditTxt.setError("You must enter a valid firstname")
        } else if (binding.lastnameEditTxt.text.isEmpty()||TextValidator.hasSpecialCharacters(binding.lastnameEditTxt.text.toString())){
            binding.lastnameEditTxt.setError("You must enter a valid lastname")
        } else if (binding.ageEditTxt.text.isEmpty()){
            binding.ageEditTxt.setError("You must enter your age")
        } else if (binding.emailEditTxt.text.isEmpty()){
            binding.emailEditTxt.setError("You must enter your email address")
        }else if (TextValidator.isEmailAddress(binding.emailEditTxt.text.toString())){
            binding.emailEditTxt.setError("This does not look like a valid email")
        }else {
            result = true
        }
        return result
    }

    companion object TAG {
        const val TAG: String = "SignUpFragment"
    }
}