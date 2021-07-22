package com.viavarejo.desafio.android.tawane.hq.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.viavarejo.desafio.android.tawane.hq.utils.Constants
import com.viavarejo.desafio.android.tawane.hq.network.ApiService
import com.viavarejo.desafio.android.tawane.hq.network.RequestInterceptor
import com.viavarejo.desafio.android.tawane.hq.repository.CharacterRepository
import com.viavarejo.desafio.android.tawane.hq.ui.details.CharacterDetailsViewModel
import com.viavarejo.desafio.android.tawane.hq.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttp(get()) }
    single { provideRetrofit(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get()) }
}

val repositoryModule = module {
    factory { CharacterRepository(get()) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.apply {
        addInterceptor(httpLoggingInterceptor)
        addInterceptor(RequestInterceptor())
    }
    return okHttpClient.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}