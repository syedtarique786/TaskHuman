package com.syed.humantask.retrofit

import android.text.TextUtils
import com.syed.humantask.BuildConfig
import com.syed.humantask.utils.ApplicationController
import com.syed.humantask.utils.Utility
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class AppRetrofit {

    val apis: ApiServices
        get() {
            //Setting Header Interceptor
            val headerInterceptor = Interceptor { chain ->
                val accessToken = BuildConfig.TOKEN
                val request = chain.request().newBuilder()
                if (!TextUtils.isEmpty(accessToken)) request.addHeader("Authorization", accessToken)
                request.addHeader(
                    "osVersion", Utility.getDeviceOS()
                )
                request.addHeader(
                    "appVersion",
                    Utility.getDeviceUniqueId(ApplicationController.getApplicationInstance())
                )
                val request1 = request.build()
                chain.proceed(request1)
            }

            //for logging
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            //setting up client
            val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build()

            //rest adapter
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Utility.getBaseUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiServices::class.java)
        }
}