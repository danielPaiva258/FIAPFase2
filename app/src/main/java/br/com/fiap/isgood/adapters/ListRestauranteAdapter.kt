package br.com.fiap.isgood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.isgood.R
import br.com.fiap.isgood.models.Restaurante


class ListRestauranteAdapter(private val dataSet: ArrayList<Restaurante>,private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ListRestauranteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView;
        val endereco: TextView;

        init {
            nome = view.findViewById(R.id.textViewNomeRestaurante);
            endereco = view.findViewById(R.id.textViewEnderecoRestaurante);
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_restaurante_item, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nome.text = dataSet[position].nome;
        viewHolder.endereco.text = dataSet[position].endereco;

        viewHolder.itemView.setOnClickListener {
            onClickListener.onClick(dataSet[position])
        }
    }



    override fun getItemCount() = dataSet.size

    class OnClickListener(val funTocall: (restaurante: Restaurante) -> Unit) {
        fun onClick(restaurante: Restaurante) = funTocall(restaurante)

    }
}