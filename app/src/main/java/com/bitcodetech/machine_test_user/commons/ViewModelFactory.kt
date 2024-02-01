package com.bitcodetech.machine_test_user.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.machine_test_user.repository.UserRepository
import com.bitcodetech.machine_test_user.viewmodels.UserViewModel

class ViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}