package com.nrchsnl.technicaltest.helper.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.nrchsnl.technicaltest.MainEnvironment.ConstOther.SNACKBAR_TIMER_SHOWING_DEFAULT
import com.nrchsnl.technicaltest.data.remote.model.Result
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.SimpleDateFormat


fun View.showSnackbarDefault(
    message: String,
    duration: Int = 0
) {


    val finalDuration = if (duration == 0) {
        SNACKBAR_TIMER_SHOWING_DEFAULT
    } else {
        duration
    }

    Snackbar.make(this, message, finalDuration).show()
}

fun <T> getExceptionResponse(e: Exception): Result<T> {
    e.printStackTrace()
    when (e) {
        is HttpException -> {
            val code = e.code()
            var msg = e.message()
            val baseDao: Result<T?>?
            try {
                val body = e.response()?.errorBody()
                baseDao = Gson().fromJson<Result<T?>>(body?.string(), Result::class.java)
            } catch (exception: java.lang.Exception) {
                return when (exception) {
                    is UnknownHostException -> Result(null, null, null, null, null, code, "Tidak ada koneksi internet, silahkan coba lagi.", false)
                    is SocketTimeoutException -> Result(null, null, null, null, null, code, "Koneksi timeout, silahkan coba lagi.", false)
                    else -> Result(null, null, null, null, null, code, "Terjadi kesalahan pada server. errorcode: $code", false)
                }
            }

            when (code) {
                500 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi kesalahan pada server"
                }
                504 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi Kesalahan"
                }
                502, 404 -> {
                    msg = baseDao?.statusMessage ?: "Resource tidak dapat ditemukan"
                }
                400 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi Kesalahan"
                }
                422 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi Kesalahan"
                }
            }

            return Result(null, null, null, null, null, code, msg, false)
        }
        is UnknownHostException -> return Result(null, null, null, null, null, -1, "Tidak ada koneksi internet, silahkan coba lagi.", false)
        is SocketTimeoutException -> return Result(null, null, null, null, null, -1, "Koneksi timeout, silahkan coba lagi.", false)
        else -> return return Result(null, null, null, null, null, -1, "Unknown error occured.", false)
    }
}

fun getExceptionResponseDetail(e: Exception): ReturnDetail {
    e.printStackTrace()
    when (e) {
        is HttpException -> {
            val code = e.code()
            var msg = e.message()
            val baseDao: ReturnDetail?
            try {
                val body = e.response()?.errorBody()
                baseDao = Gson().fromJson(body?.string(), ReturnDetail::class.java)
            } catch (exception: java.lang.Exception) {
                return when (exception) {
                    is UnknownHostException -> ReturnDetail(code, "Tidak ada koneksi internet, silahkan coba lagi.", false)
                    is SocketTimeoutException -> ReturnDetail(code, "Koneksi timeout, silahkan coba lagi.", false)
                    else -> ReturnDetail(code, "Terjadi kesalahan pada server. errorcode: $code", false)
                }
            }

            when (code) {
                500 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi kesalahan pada server"
                }
                504 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi Kesalahan"
                }
                502, 404 -> {
                    msg = baseDao?.statusMessage ?: "Resource tidak dapat ditemukan"
                }
                400 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi Kesalahan"
                }
                422 -> {
                    msg = baseDao?.statusMessage ?: "Terjadi Kesalahan"
                }
            }

            return ReturnDetail(code, msg, false)
        }
        is UnknownHostException -> return ReturnDetail(-1, "Tidak ada koneksi internet, silahkan coba lagi.", false)
        is SocketTimeoutException -> return ReturnDetail(-1, "Koneksi timeout, silahkan coba lagi.", false)
        else -> return return ReturnDetail(-1, "Unknown error occured.", false)
    }
}

fun getAPIKey() : String{
    return "78e8426278269a9f15963322ae7afdaa"
}

fun getBaseUrlImage(image: String): String {
    return "https://image.tmdb.org/t/p/original$image"
}

fun getAPIKeyYoutube() : String {
    return "AIzaSyCWqDp6KZqm-uoG5m4gPz12kPpEB91AvTE"
}


fun ImageView.setImageUrl(url: Any){
    Glide.with(this)
        .load(url)
        .into(this)
}


fun Context.navigatorImplicit(
    activityPackage: String,
    className: String,
    extras: Bundle = Bundle(),
    clearStack: Boolean = false,
    option: Bundle? = null) {
    val intent = Intent()
    try {
        intent.setClassName(activityPackage, className).putExtras(extras)

        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        if (option != null) {
            startActivity(intent, option)
        } else {
            startActivity(intent)
        }

    } catch (e: Exception) {
        Toast.makeText(this, "Activity not found", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}

fun changeFormatDate(date : String) : String{
    var parser = SimpleDateFormat("yyyy-MM-dd")
    var formater = SimpleDateFormat("dd MMM yyyy")
    return formater.format(parser.parse(date))
}