package br.com.fiap.isgood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.isgood.R
import br.com.fiap.isgood.models.Restaurante
import com.bumptech.glide.Glide


class ListRestauranteAdapter(private val dataSet: ArrayList<Restaurante>,private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ListRestauranteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView
        val endereco: TextView
        val ivRestauranteMin : ImageView

        init {
            nome = view.findViewById(R.id.textViewNomeRestaurante)
            endereco = view.findViewById(R.id.textViewEnderecoRestaurante)
            ivRestauranteMin = view.findViewById(R.id.ivRestauranteMin)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_restaurante_item, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nome.text = dataSet[position].nome
        viewHolder.endereco.text = dataSet[position].endereco
        Glide.with(viewHolder.ivRestauranteMin.context).load(dataSet[position].strLogoRestaurante).into(viewHolder.ivRestauranteMin)

        viewHolder.itemView.setOnClickListener {
            onClickListener.onClick(dataSet[position])
        }
    }



    override fun getItemCount() = dataSet.size

    class OnClickListener(val funTocall: (restaurante: Restaurante) -> Unit) {
        fun onClick(restaurante: Restaurante) = funTocall(restaurante)

    }
}