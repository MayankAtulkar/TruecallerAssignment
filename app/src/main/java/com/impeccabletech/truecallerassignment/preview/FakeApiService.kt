package com.impeccabletech.truecallerassignment.preview

import com.impeccabletech.truecallerassignment.network.ApiService
import retrofit2.Response

class FakeApiService: ApiService {
    override suspend fun fetchWebContent(): Response<String> {
        return Response.success("Mock response for Truecaller content")
    }
}