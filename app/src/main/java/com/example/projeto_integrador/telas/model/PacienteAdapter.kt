import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_integrador.R
import com.example.projeto_integrador.model.Paciente

class PacienteAdapter(private val pacientes: List<Paciente>) :
    RecyclerView.Adapter<PacienteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNome: TextView = itemView.findViewById(R.id.textViewNome)
        val textViewGenero: TextView = itemView.findViewById(R.id.textViewGenero)
        val textViewIdade: TextView = itemView.findViewById(R.id.textViewIdade)

        // Add additional TextView properties here
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_paciente, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = pacientes[position]
        holder.textViewNome.text = "Nome: ${paciente.nome}"
        holder.textViewGenero.text = "Genero: ${paciente.genero}"
        holder.textViewIdade.text = "Idade: ${paciente.idade}"

        // Set additional TextView properties here
    }

    override fun getItemCount(): Int {
        return pacientes.size
    }
}

class PacienteSpinnerAdapter(context: Context, resource: Int, private val pacientes: List<Paciente>) :
    ArrayAdapter<String>(context, resource, pacientes.map { it.nome }) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)

        // Personalize the appearance of the Spinner item if desired
        // Example: view.setBackgroundResource(R.drawable.my_custom_background)

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getDropDownView(position, convertView, parent)
    }

    fun getPacienteAtPosition(position: Int): Paciente {
        return pacientes[position]
    }
}



