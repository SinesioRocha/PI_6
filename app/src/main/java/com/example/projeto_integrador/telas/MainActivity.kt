package com.example.projeto_integrador.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.projeto_integrador.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnCadastro = findViewById<Button>(R.id.btn_cadastrar_paciente)
        val btnAvaliacai = findViewById<Button>(R.id.btn_nova_avaliacao)

    }

    fun btnCadastro(v: View){
        // Cria um Intent para iniciar a segunda tela
        val intent = Intent(this, CadastroActivity::class.java)

        // Inicia a segunda tela
        startActivity(intent)
    }

    fun btnAvalicao(v: View){
            // Cria um Intent para iniciar a segunda tela
            val intent = Intent(this, AvaliacaoActivity::class.java)

            // Inicia a segunda tela
            startActivity(intent)
        }

    fun btnHistorico(v: View){
        // Cria um Intent para iniciar a segunda tela
        val intent = Intent(this, ListPacientes::class.java)

        // Inicia a segunda tela
        startActivity(intent)
    }
}