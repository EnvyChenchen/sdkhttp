package com.ifun.ifunokhttp

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mRequest=chain.request()

        // startTime
        val startTime=System.currentTimeMillis()
        Log.e("LogInterceptor", "Sending request url== ${mRequest.url} " +
                " connection== ${chain.connection()} " +
                " headers==${mRequest.headers}")

        val mResponse=chain.proceed(mRequest)
        val endTime=System.currentTimeMillis()
        Log.e("LogInterceptor","Received response url== ${mResponse.request.url} " +
                " costTime== ${endTime-startTime}ms"+
                " header== ${mResponse.headers}")

        return mResponse
    }
}
