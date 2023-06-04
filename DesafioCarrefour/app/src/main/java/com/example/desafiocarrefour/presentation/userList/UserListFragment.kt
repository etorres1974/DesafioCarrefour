package com.example.desafiocarrefour.presentation.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.desafiocarrefour.R
import com.example.desafiocarrefour.databinding.FragmentFirstBinding
import com.example.desafiocarrefour.domain.model.UserListItem
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private val userListViewModel : UserListViewModel by activityViewModel()
    private val adapter = UserListAdapter(::openUserDetails)

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun openUserDetails(user: UserListItem){
        userListViewModel.getUserDetails(user.login)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun setupRecyclerView(){
        binding.userRecyclerView.adapter = adapter
        userListViewModel.usersLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun setupSearchBar(){
        binding.searchView.setOnSearchClickListener {
            //TODO - Search users
        }
    }
}