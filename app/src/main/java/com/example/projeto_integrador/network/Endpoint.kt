package com.example.projeto_integrador.network

import com.example.projeto_integrador.model.Avaliacao
import com.example.projeto_integrador.model.Paciente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoint {

    @GET("/Paciente/listar")
    fun getPosts():Call<List<Paciente>>

    @POST("/Paciente/incluir")
    fun postsPaciente(@Body paciente: Paciente):Call<Void>

        @POST("/Avaliacao/incluir")
    fun postsAvaliacao(@Body avaliacao: Avaliacao):Call<Void>
}