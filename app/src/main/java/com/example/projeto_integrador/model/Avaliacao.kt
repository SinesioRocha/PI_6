package com.example.projeto_integrador.model

import com.google.gson.annotations.SerializedName
import java.sql.RowId

data class Avaliacao(
    @SerializedName("id")
    var id: Int,
    @SerializedName("data")
    var data: String,
    @SerializedName("pressao")
    var pressao: String,
    @SerializedName("paciente_id")
    var paciente: Paciente,
    @SerializedName("TabelaBraden_id")
    var tabela: Int,
    @SerializedName("estagio")
    var estagio: Int
)