package com.ifun.ifunokhttp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.ifun.ifunokhttp.ApiDns
import com.ifun.ifunokhttp.LogInterceptor
import com.ifun.ifunokhttp.MyApp
import com.ifun.ifunokhttp.R
import com.ifun.ifunokhttp.TrustAllHostnameVerifier
import com.ifun.ifunokhttp.TrustAllManager
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

class MainActivity : Activity() {
    var tvContent: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvContent = findViewById<TextView>(R.id.tv_content)
        findViewById<Button>(R.id.btn_net).setOnClickListener {
//            Thread({ connect() }).run()
            connect()
        }

    }


    fun connect() {
        val client = OkHttpClient.Builder()
//            .cache(Cache(File(MyApp.app!!.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
//            .addInterceptor(LogInterceptor())
//            .dns(ApiDns())
//            .sslSocketFactory(createSSLSocketFactory()!!, TrustAllManager())
//            .hostnameVerifier(TrustAllHostnameVerifier())
            .connectTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        val body = FormBody.Builder()
            .add("username", "qwertyuiop")
            .add("password", "qwertyuiop")
            .build()
//        val body = FormBody.Builder()
//            .add("userName", "beijing")
//            .add("password", "123456")
//            .build()
//        val header = Headers.Builder().add("auth-token", "MTU5Mjg1MDg3NDcwNw11.26==").build()
        val request = Request.Builder()
            .url("http://1.14.20.9/game/register/qwertyuiop?username=envy&password=qwertyuiop")
//            .url("http://1.14.20.9/game/login/qwertyuiop")
//            .url("https://api.devio.org/as/user/login")
//            .post(body)
//            .headers(header)
            .build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: okio.IOException) {
                Log.e("MainActivity", "onFailure:${e.message}")
                runOnUiThread {
                    tvContent!!.text = e.message
                }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("MainActivity", "onResponse:${response.code}")
                if (response.isSuccessful) {
                    if (response.body == null){
                        Log.e("MainActivity", "response.body is null")
                        return
                    }
                    val responseStr = response.body!!.string()
                    runOnUiThread {
                        tvContent!!.text = responseStr
                    }
                }

            }

        })
    }

    private fun createSSLSocketFactory(): SSLSocketFactory? {
        var sSLSocketFactory: SSLSocketFactory? = null
        try {
            val sc: SSLContext = SSLContext.getInstance("TLS")
            sc.init(
                null, arrayOf<TrustManager>(TrustAllManager()),
                SecureRandom()
            )
            sSLSocketFactory = sc.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sSLSocketFactory
    }
}