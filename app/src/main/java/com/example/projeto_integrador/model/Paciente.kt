package com.example.projeto_integrador.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Paciente(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nome")
    var nome: String,
    @SerializedName("idade")
    var idade: Int,
    @SerializedName("genero")
    var genero: String,
    @SerializedName("altura")
    var altura: Double,
    @SerializedName("peso")
    var peso: Double,
    @SerializedName("acamado")
    var acamado: String,
    @SerializedName("tepInternado")
    var tepInternado: Int,
    @SerializedName("contencao")
    var contencao: String,
    @SerializedName("consciencia")
    var consciencia: String,
    @SerializedName("dependencia")
    var dependencia: String,
    @SerializedName("doencaPreexistentes")
    var doencaPreexistentes: String,
    @SerializedName("incontinencia")
    var incontinencia: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nome)
        parcel.writeInt(idade)
        parcel.writeString(genero)
        parcel.writeDouble(altura)
        parcel.writeDouble(peso)
        parcel.writeString(acamado)
        parcel.writeInt(tepInternado)
        parcel.writeString(contencao)
        parcel.writeString(consciencia)
        parcel.writeString(dependencia)
        parcel.writeString(doencaPreexistentes)
        parcel.writeString(incontinencia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Paciente> {
        override fun createFromParcel(parcel: Parcel): Paciente {
            return Paciente(parcel)
        }

        override fun newArray(size: Int): Array<Paciente?> {
            return arrayOfNulls(size)
        }
    }
}
