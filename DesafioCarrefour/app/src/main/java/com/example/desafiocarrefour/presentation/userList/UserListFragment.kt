package com.example.desafiocarrefour.presentation.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.desafiocarrefour.R
import com.example.desafiocarrefour.databinding.FragmentFirstBinding
import com.example.desafiocarrefour.domain.model.UserListItem
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class UserListFragment : Fragment() {

    private val userListViewModel : UserListViewModel by activityViewModel()
    private val adapter = UserListAdapter(::openUserDetails)
    private val queryAdapter = UserListAdapter(::openUserDetails)

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
        setupSearchBar()
    }

    private fun openUserDetails(user: UserListItem){
        userListViewModel.getUserDetails(user.login)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun setupRecyclerView(){
        binding.userRecyclerView.adapter = adapter
        userListViewModel.usersLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
            binding.textViewEmpty.isVisible =  it.isEmpty()
        }
    }

    private fun setupSearchBar(){
        binding.searchView.rootView.setOnClickListener {
            binding.searchView.onActionViewExpanded()
        }

        binding.pullToRefresh.setOnRefreshListener {
            userListViewModel.getUsers()
            binding.searchView.setQuery("", false)
            binding.userRecyclerView.adapter = adapter
        }
        userListViewModel.usersLivedataLoading.observe(viewLifecycleOwner) {
            binding.pullToRefresh.isRefreshing = it
        }

        binding.searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    userListViewModel.searchUsersByQuery(query)
                    binding.searchView.clearFocus()
                    binding.userRecyclerView.adapter = queryAdapter
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false
        })

        userListViewModel.queryUsersLiveData.observe(viewLifecycleOwner){
            queryAdapter.submitList(it)
            binding.textViewEmpty.isVisible =  it.isEmpty()
        }
    }
}