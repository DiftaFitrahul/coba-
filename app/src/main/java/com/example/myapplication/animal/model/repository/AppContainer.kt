package com.example.myapplication.animal.model.repository

import com.example.myapplication.animal.model.dataSource.AnimalDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val animalRepository: AnimalRepository
}

class DefaultAppContainer: AppContainer{

    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AnimalDataSource by lazy {
        retrofit.create(AnimalDataSource::class.java)
    }

    override val animalRepository: AnimalRepository by lazy {
        NetworkAnimalRepository(retrofitService)
    }

}