package ru.vladislav.cifraapplication.data.retrofit.services

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vladislav.cifraapplication.data.retrofit.api.CifraApi

class RetrofitService(private val gson: Gson, private val okHttpClient: OkHttpClient) {

    private val baseURL = "https://mb-ci.cifra.app/api/v1/"

    var instance: CifraApi? = null
        get() {
            if (field == null)
                field = getApi()
            return field
        }

    private fun getApi(): CifraApi? {
        return try {
            val retrofit = getRetrofit()
            retrofit.create(CifraApi::class.java)

        } catch (e: Exception) {
            null
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(baseURL)
            .build()
    }
}