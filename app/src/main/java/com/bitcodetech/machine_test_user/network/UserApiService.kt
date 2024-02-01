package com.bitcodetech.machine_test_user.network

import com.bitcodetech.machine_test_user.models.User
import com.bitcodetech.machine_test_user.models.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun fetchUsers() : ArrayList<User>

    companion object{
        private var userApiService : UserApiService? = null

        fun getInstance() : UserApiService{
            if(userApiService == null){
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                    userApiService = retrofit.create(UserApiService::class.java)
            }
            return userApiService!!
        }
    }
}