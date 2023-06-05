package com.example.desafiocarrefour.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.desafiocarrefour.R
import com.example.desafiocarrefour.data.GitHubApiException
import com.example.desafiocarrefour.databinding.ActivityMainBinding
import com.example.desafiocarrefour.presentation.userList.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userListViewModel : UserListViewModel by viewModel()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        userListViewModel.getUsers()
        userListViewModel.error.observe(this){
            val text = throwableToUserMessage(it)
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(this, text, duration)
            toast.show()
        }
    }

    private fun throwableToUserMessage( throwable: Throwable) : String = when(throwable){
        is GitHubApiException.NotFound -> getString(R.string.exception_not_found)
        is GitHubApiException.QueryMissing -> getString(R.string.exception_query_missing)
        is GitHubApiException.LimitRate-> getString(R.string.exception_limit_rate)
        else -> getString(R.string.exception_unknown)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}