package com.example.projeto_integrador.telas

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto_integrador.R
import com.example.projeto_integrador.model.Avaliacao
import com.example.projeto_integrador.model.Paciente
import com.example.projeto_integrador.network.Endpoint
import com.example.projeto_integrador.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AvaliacaoLesaoActivity : AppCompatActivity() {

    private lateinit var pressao: String
    private lateinit var paciente: Paciente

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao_lesao)

        paciente = intent.getParcelableExtra("PATIENT_OBJECT") ?: Paciente(-1, "", 0, "", 0.0, 0.0, "", 0, "", "", "", "", "")

        // Se o ID do paciente for inválido, exibe uma mensagem e encerra a atividade
        if (paciente.id == -1) {
            Toast.makeText(this, "No patient data found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Get the current date and time
        val currentDate = LocalDate.now()

        // Format the date and time as a string
        pressao = currentDate.format(DateTimeFormatter.ISO_DATE)

        // Restante do seu código...
    }

    fun Estagio1(view: View) {
        salvarAvaliacao(1)
        val intent = Intent(this, ResultadoEstagio1::class.java)
        startActivity(intent)
    }

    fun Estagio2(view: View) {
        salvarAvaliacao(2)
        val intent = Intent(this, ResultadoEstagio2::class.java)
        startActivity(intent)
    }

    fun Estagio3(view: View) {
        salvarAvaliacao(3)
        val intent = Intent(this, ResultadoEstagio3::class.java)
        startActivity(intent)
    }

    fun Estagio4(view: View) {
        salvarAvaliacao(4)
        val intent = Intent(this, ResultadoEstagio4::class.java)
        startActivity(intent)
    }

    fun voltarTela(view: View) {
        finish()
    }

    private fun salvarAvaliacao(estagio: Int) {
        // Verifica se o objeto Paciente é válido
        if (::paciente.isInitialized && paciente.id != -1) {
            // Cria um objeto Avaliacao com os dados necessários
            val avaliacao = Avaliacao(
                id = 0,  // Supondo que isso seja gerado pelo servidor
                data = pressao,
                pressao = "SIM",  // Substitua pelo valor real
                paciente = paciente,
                tabela = 1,  // Substitua pelo valor real
                estagio = 1
            )

            // Chama a função para salvar a Avaliacao
            criarAvaliacao(avaliacao)
        } else {
            Toast.makeText(this, "No patient data found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    private fun criarAvaliacao(avaliacao: Avaliacao) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://192.168.0.192:8080")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val call = endpoint.postsAvaliacao(avaliacao)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Paciente criado com sucesso
                    exibirMensagem("Avaliação criada com sucesso")
                } else {
                    // Erro no servidor ou na solicitação
                    val mensagemErro = "Erro: ${response.code()}"
                    exibirMensagem(mensagemErro)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Falha na solicitação
                exibirMensagem("Falha na solicitação: ${t.message}")
            }
        })
    }

    private fun exibirMensagem(mensagem: String) {
        // Você pode exibir a mensagem em um TextView ou como um Toast
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
