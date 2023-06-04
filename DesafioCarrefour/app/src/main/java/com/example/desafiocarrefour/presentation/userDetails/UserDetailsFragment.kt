package com.example.desafiocarrefour.presentation.userDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.desafiocarrefour.R
import com.example.desafiocarrefour.databinding.FragmentSecondBinding
import com.example.desafiocarrefour.domain.model.UserDetails
import com.example.desafiocarrefour.presentation.addTextOrHide
import com.example.desafiocarrefour.presentation.loadImageUrl
import com.example.desafiocarrefour.presentation.userList.UserListViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class UserDetailsFragment : Fragment() {

    private val userListViewModel : UserListViewModel by activityViewModel()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.usersDetailsLiveData.observe(viewLifecycleOwner){ user ->
            setupUserDetailsCardView(user)
        }
    }

    private fun setupUserDetailsCardView(user : UserDetails){
        with(binding) {
            with(user) {
                imageViewUser.loadImageUrl(avatarUrl)
                textViewName.addTextOrHide(name)
                textViewLogin.addTextOrHide(login)
                textViewCompany.addTextOrHide(company)
                textViewLocation.addTextOrHide(location)
                textViewFollowers.addTextOrHide(followers.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}