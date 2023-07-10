package com.example.hudlup.onboarding

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Person
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.hudlup.BuildConfig
import com.example.hudlup.R
import com.example.hudlup.databinding.FragmentLoginBinding
import com.example.hudlup.util.TextValidator
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.io.InputStream
import kotlin.math.sign


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

        binding.googleLoginBtn.setOnClickListener {
            lateinit var googleSignInClient: GoogleSignInClient
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(BuildConfig.GOOGLE_FIREBASE_CLOUD_KEY)
                .requestProfile()
                .build()
            googleSignInClient = GoogleSignIn.getClient(requireActivity(),gso)
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken
                val accessToken = account.serverAuthCode
                // Signed in successfully, update UI accordingly
                 val firebaseCredential = GoogleAuthProvider.getCredential(account.idToken,null)
                FirebaseAuth.getInstance().signInWithCredential(firebaseCredential).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        //TODO: Call user data from db
                        Log.d("Auth", "Success: "+task.result.user.toString())
                        val scopes: Set<Scope> = account.grantedScopes
                        for (scope in scopes){
                            Log.d("Auth", "Scopes:$scope")
                        }
//                        SharedPreferenceManager.StoreUserDetailsOnSignUp(account.givenName,account.familyName,null, account.photoUrl)
                        animateSignInBtnSuccessful{
                            //TODO: Grab user data from DB
                            view?.findNavController()?.navigate(R.id.action_loginFragment_to_hub)
                        }
                    } else {
                        Log.d("Auth", "Error:"+task.exception)
                    }
                }
            } catch (e: ApiException) {
                // Sign in failed, update UI accordingly
                Log.d("Auth", "Error:"+e.toString())
                //TODO: Build in crashalytics
                // TODO: Update UI based on feedback
            }
        }
    }
//
//    suspend fun fetchUserBirthdate(): List<Person.Birthday>? {
//        val httpTransport: HttpTransport = GoogleNetHttpTransport.newTrustedTransport()
//        val jsonFactory: JsonFactory = JacksonFactory.getDefaultInstance()
//
//        // Set up the Google credentials
//        val credentials: GoogleCredential = GoogleCredential.getApplicationDefault(httpTransport, jsonFactory)
//
//        // Set up the Google People API service
//        val peopleService: PeopleService = PeopleService.Builder(httpTransport, jsonFactory, credentials)
//            .setApplicationName("YourAppName")
//            .build()
//
//        try {
//            // Make the API call to retrieve the user's profile data, including the birthdate
//            val person: Person = peopleService.people().get("people/me")
//                .setPersonFields("birthdays")
//                .execute()
//
//            return person.birthdays
//        } catch (e: Exception) {
//            // Handle exceptions or errors
//            return null
//        }
//    }
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