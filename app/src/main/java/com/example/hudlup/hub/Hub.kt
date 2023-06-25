package com.example.hudlup.hub

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.R
import com.example.hudlup.databinding.FragmentHubBinding
import com.example.hudlup.databinding.FragmentSignUpBinding
import com.example.hudlup.util.SharedPreferenceManager
import com.google.firebase.auth.FirebaseAuth

class Hub : Fragment() {
    private var _binding: FragmentHubBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HubViewModel::class.java)
    }
    companion object {
        fun newInstance() = Hub()
    }

    private lateinit var viewModel: HubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHubBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.logoutBtn?.setOnClickListener {
            //TODO: Abstract this out to wherever logout sits
            FirebaseAuth.getInstance().signOut()
            NavHostFragment.findNavController(this).navigate(R.id.action_hub_to_loginFragment)
            SharedPreferenceManager.clearPreferences()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}