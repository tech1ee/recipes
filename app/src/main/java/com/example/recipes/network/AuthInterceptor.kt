package com.example.recipes.network

import com.example.recipes.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (chain.request().header(AUTH_HEADER) == null) {
            val request = chain.request()
                .newBuilder()
                .addHeader(AUTH_HEADER, BuildConfig.API_KEY)
                .method(chain.request().method, chain.request().body)
                .build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request())
    }

    companion object {
        private const val AUTH_HEADER = "x-api-key"
    }
}