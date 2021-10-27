package com.nrchsnl.technicaltest.data

/**
 * Created by deannrchsnl on 25/10/21.
 */
import android.content.Context
import com.nrchsnl.technicaltest.helper.TLSSocketFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object ServiceFactory {
    private fun request(context: Context): Retrofit {
        val mLoggingInterceptor = HttpLoggingInterceptor()
        mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val chainInterceptor = { chain: Interceptor.Chain ->
            chain.proceed(chain.request().newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build())

        }

        var mClient = OkHttpClient()

        try {
            val tlsSocketFactory = TLSSocketFactory()
            if (tlsSocketFactory.trustManager != null) {
                mClient =
                    OkHttpClient.Builder()
                        .addInterceptor(chainInterceptor)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.trustManager!!)
                        .build()
            }
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient)
            .build()
    }

    fun <T> createService(context: Context, service: Class<T>): T {
        return request(context).create(service)
    }
}