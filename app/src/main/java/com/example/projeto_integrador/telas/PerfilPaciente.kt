package com.example.projeto_integrador.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projeto_integrador.R

class PerfilPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_paciente)
    }
    fun voltarTela(view: View) {
        finish()
    }
}