package com.impeccabletech.truecallerassignment.di

import com.impeccabletech.truecallerassignment.network.ApiService
import com.impeccabletech.truecallerassignment.repository.MainRepository
import com.impeccabletech.truecallerassignment.repository.MainRepositoryImpl
import com.impeccabletech.truecallerassignment.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.truecaller.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single<MainRepository> { MainRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }

}