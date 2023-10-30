package com.example.projeto_integrador.telas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto_integrador.R
import kotlinx.coroutines.*
import io.ktor.client.*
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class AvaliacaoActivity : AppCompatActivity() {

    lateinit var spinnerPacientes: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao)
        spinnerPacientes = findViewById(R.id.spinnerPacientes)
        setupSpinners()

    }

    fun btnSim(view: View) {
        // Cria um Intent para iniciar a segunda tela
        val intent = Intent(this, AvaliacaoLesaoActivity::class.java)

        // Inicia a segunda tela
        startActivity(intent)
    }

    fun btnNao(view: View) {
        // Cria um Intent para iniciar a segunda tela
        val intent = Intent(this, EscalaBraden::class.java)

        // Inicia a segunda tela
        startActivity(intent)
    }

    fun voltarTela(view: View) {
        finish()
    }

    private fun setupSpinners(){
        val pacientesArray = arrayOf("--SELECIONE--")
        spinnerPacientes = findViewById(R.id.spinnerPacientes)
        spinnerPacientes.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,pacientesArray)
    }
}
