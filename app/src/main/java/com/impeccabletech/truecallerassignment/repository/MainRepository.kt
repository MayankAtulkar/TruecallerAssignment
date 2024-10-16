package com.impeccabletech.truecallerassignment.repository

import com.impeccabletech.truecallerassignment.network.ApiService

interface MainRepository {

    suspend fun fetchWebContent() : String?

}

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {
    override suspend fun fetchWebContent(): String? {
        val response = apiService.fetchWebContent()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null // Handle error case
        }
    }
}