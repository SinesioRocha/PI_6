package com.example.projeto_integrador.telas

import PacienteAdapter
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.projeto_integrador.R
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_integrador.model.Paciente
import com.example.projeto_integrador.network.Endpoint
import com.example.projeto_integrador.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPacientes : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pacientes)
        recyclerView = findViewById(R.id.recyclerViewPacientes)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        getData()
    }

    fun getData() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://192.168.0.197:8080")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Paciente>> {
            override fun onFailure(call: Call<List<Paciente>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Paciente>>, response: Response<List<Paciente>>) {
                if (response.isSuccessful) {
                    val pacientes = response.body()

                    if (pacientes != null) {
                        val adapter = PacienteAdapter(pacientes)
                        recyclerView.adapter = adapter
                    } else {
                        Toast.makeText(baseContext, "No patients found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(baseContext, "Request failed with code: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }





    fun perfil(view: View) {
        val intent = Intent(this, PerfilPaciente::class.java)

        // Inicia a segunda tela
        startActivity(intent)
    }

    fun voltarTela(view: View) {
        finish()
    }
}
