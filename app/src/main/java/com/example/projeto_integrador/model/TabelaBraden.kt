package com.example.projeto_integrador.model

import com.google.gson.annotations.SerializedName

data class TabelaBraden (
    @SerializedName("id")
    var id: Int,
    @SerializedName("Sensorial")
    var Sensorial: Int,
    @SerializedName("Umidade")
    var Umidade: Int,
    @SerializedName("Atividade")
    var Atividade: Int,
    @SerializedName("Mobilidade")
    var Mobilidade: Int,
    @SerializedName("Nutricao")
    var Nutricao: Int,
    @SerializedName("Friccao")
    var Friccao: Int,
    @SerializedName("total")
    var total: Int,

    )