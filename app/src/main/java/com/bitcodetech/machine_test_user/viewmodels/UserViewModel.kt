package com.bitcodetech.machine_test_user.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.machine_test_user.models.User
import com.bitcodetech.machine_test_user.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val userMutabaleLiveData = MutableLiveData<Boolean>()
    val users = ArrayList<User>()

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.fetchUsers()

            withContext(Dispatchers.Main) {
                this@UserViewModel.users.addAll(user)
                userMutabaleLiveData.postValue(true)
            }
        }
    }
}