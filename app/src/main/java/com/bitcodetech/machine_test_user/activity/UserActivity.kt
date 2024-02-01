package com.bitcodetech.machine_test_user.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.machine_test_user.adapter.UserAdapter
import com.bitcodetech.machine_test_user.commons.ViewModelFactory
import com.bitcodetech.machine_test_user.databinding.UserActivityBinding
import com.bitcodetech.machine_test_user.models.User
import com.bitcodetech.machine_test_user.network.UserApiService
import com.bitcodetech.machine_test_user.repository.UserRepository
import com.bitcodetech.machine_test_user.viewmodels.UserViewModel

class UserActivity : AppCompatActivity() {

    private lateinit var binding : UserActivityBinding
    private lateinit var userViewModel : UserViewModel
    private lateinit var userAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserActivityBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initAdapter()
        initObserver()
        initListeners()

        userViewModel.fetchUsers()
        setContentView(binding.root)

    }

    private fun initListeners(){
        binding.recyclerUsers.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        userViewModel.fetchUsers()
                    }
                }
            })

        userAdapter.onUserClickListener =
            object  : UserAdapter.OnUserClickListener{
                override fun onUserClick(user: User, position: Int, userAdapter: UserAdapter) {
                    showDetailsActivity(user)
                }
            }
    }

    private fun showDetailsActivity(user : User){
        val intent =  Intent(this@UserActivity, UserDetailsActivity::class.java)
        intent.putExtra("users",user)
        startActivityForResult(intent,1)
    }

    private fun initAdapter(){
        userAdapter = UserAdapter(userViewModel.users)
        binding.recyclerUsers.adapter = userAdapter
    }

    private fun initObserver(){
        userViewModel.userMutabaleLiveData.observe(
            this
        ){
            if (it){
                userAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initViewModels(){
        userViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                UserRepository(
                    UserApiService.getInstance()
                )
            )
        )[UserViewModel::class.java]
    }

    private fun initViews(){
        binding.recyclerUsers.layoutManager=
            LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
    }
}