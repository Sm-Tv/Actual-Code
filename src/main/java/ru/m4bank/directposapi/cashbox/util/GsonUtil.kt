package ru.m4bank.directposapi.cashbox.util

import com.google.gson.Gson

//todo move to package
internal object GsonUtil {

    private val gson = Gson()

    fun toJSON(any: Any): String = gson.toJson(any)

    inline fun <reified T> fromJSON(json: String): T = gson.fromJson(json, T::class.java)
}
