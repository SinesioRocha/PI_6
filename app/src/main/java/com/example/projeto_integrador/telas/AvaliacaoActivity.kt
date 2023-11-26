package com.example.projeto_integrador.telas

import PacienteSpinnerAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto_integrador.R
import com.example.projeto_integrador.model.Paciente
import com.example.projeto_integrador.network.Endpoint
import com.example.projeto_integrador.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AvaliacaoActivity : AppCompatActivity() {

    private lateinit var spinnerPacientes: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao)

        // Usando findViewById para obter a referência ao Spinner
        spinnerPacientes = findViewById(R.id.spinnerPacientes)

        // Chame a função para obter dados
        getData()
    }

    fun btnSim(view: View) {
        val selectedPatient = getSelectedPatient()

        if (selectedPatient != null) {
            val intent = Intent(this, AvaliacaoLesaoActivity::class.java)
            intent.putExtra("PATIENT_OBJECT", selectedPatient)
            startActivity(intent)
        } else {
            Toast.makeText(baseContext, "Select a patient first", Toast.LENGTH_SHORT).show()
        }
    }


    fun btnNao(view: View) {
        navigateToNextActivity(EscalaBraden::class.java)
    }

    private fun navigateToNextActivity(destinationActivity: Class<*>) {
        val selectedPatient = getSelectedPatient()

        if (selectedPatient != null) {
            val intent = Intent(this, destinationActivity)
            intent.putExtra("PATIENT_OBJECT", selectedPatient)
            startActivity(intent)
        } else {
            Toast.makeText(baseContext, "Select a patient first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSelectedPatient(): Paciente? {
        val selectedPosition = spinnerPacientes.selectedItemPosition

        // Log the selected position for debugging
        Log.d("AvaliacaoActivity", "Selected Position: $selectedPosition")

        val adapter = spinnerPacientes.adapter as? PacienteSpinnerAdapter
        val paciente = adapter?.getPacienteAtPosition(selectedPosition)

        if (paciente != null) {
            // Log the selected patient for debugging
            Log.d("AvaliacaoActivity", "Selected Patient: $paciente")
        } else {
            // Log an error if the adapter data is null or the selected position is invalid
            Log.e("AvaliacaoActivity", "Error: Adapter data is null or selected position is invalid")
        }

        return paciente
    }

    fun voltarTela(view: View) {
        finish()
    }

    private fun getData() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://192.168.0.192:8080")
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Paciente>> {
            override fun onFailure(call: Call<List<Paciente>>, t: Throwable) {
                Toast.makeText(baseContext, "Failed to retrieve patients: ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Paciente>>, response: Response<List<Paciente>>) {
                if (response.isSuccessful) {
                    val pacientes = response.body()

                    if (pacientes != null) {
                        // Create an adapter with the names
                        val adapter = PacienteSpinnerAdapter(
                            baseContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            pacientes
                        )

                        // Log the size of the data for debugging
                        Log.d("AvaliacaoActivity", "Spinner Adapter Data Size: ${pacientes.size}")

                        // Set the adapter for the Spinner
                        spinnerPacientes.adapter = adapter

                        // Set an item selected listener to handle item selection
                        spinnerPacientes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                // Handle the selected patient
                                val selectedPatient = pacientes[position]

                                // Log the selected patient for debugging
                                Log.d("AvaliacaoActivity", "Selected Patient: $selectedPatient")
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                // Handle case where nothing is selected
                            }
                        }
                    } else {
                        Toast.makeText(baseContext, "No patients found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(baseContext, "Request failed with code: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
