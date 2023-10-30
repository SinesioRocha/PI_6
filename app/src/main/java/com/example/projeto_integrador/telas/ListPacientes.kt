package com.example.projeto_integrador.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projeto_integrador.R

class ListPacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pacientes)
    }

    fun perfil(view: View){
        val intent = Intent(this, PerfilPaciente::class.java)

        // Inicia a segunda tela
        startActivity(intent)
    }
    fun voltarTela(view: View) {
        finish()
    }
}