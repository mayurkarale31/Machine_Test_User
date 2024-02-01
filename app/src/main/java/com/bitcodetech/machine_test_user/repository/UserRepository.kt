package com.bitcodetech.machine_test_user.repository

import com.bitcodetech.machine_test_user.models.User
import com.bitcodetech.machine_test_user.network.UserApiService

class UserRepository(
    private val userApiService: UserApiService
) {
    suspend fun fetchUsers() : ArrayList<User>{
        return userApiService.fetchUsers()
    }
}