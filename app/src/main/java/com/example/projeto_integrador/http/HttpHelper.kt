package com.example.projeto_integrador.http

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Request

class HttpHelper {

    fun post(json: String):String{
        val URL = "http://10.14.247.192/Paciente/incluir"

        val headerHttp = "application/json; charset=utf-8".toMediaTypeOrNull()

        val client = OkHttpClient()

        val body = RequestBody.create(headerHttp, json)

        var request = Request.Builder().url(URL).post(body).build()

        val response = client.newCall(request).execute()

        return response.body?.string() ?: ""
    }
}