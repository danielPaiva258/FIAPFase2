package br.com.fiap.isgood.fragments.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.isgood.R
import br.com.fiap.isgood.models.Lanche
import com.bumptech.glide.Glide

class LancheFragment:Fragment (){

    lateinit  var recyclerView: RecyclerView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lanche,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewLanches);
        configureRecyclerView();
    }

    private fun configureRecyclerView() {
        recyclerView.setLayoutManager(LinearLayoutManager(getActivity()));

        /*val lanche1 = Lanche.getExemple (1) // nome="lanche1", endereco = "lanche1")
        val lanche2 = Lanche.getExemple (2) // nome="lanche2", endereco = "lanche2")
        val lanche3 = Lanche.getExemple (3) // nome="lanche3", endereco = "lanche3")

        val listLanches = ArrayList<Lanche>();
        listLanches.add(lanche1);
        listLanches.add(lanche2);
        listLanches.add(lanche3);*/
        val listLanches = Lanche.getSampleArray()

        val adapter = ListLancheAdapter(listLanches);
        recyclerView.adapter = adapter;
    }

    class ListLancheAdapter (private val dataSet: ArrayList<Lanche>) :
        RecyclerView.Adapter<ListLancheAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imgLanche : ImageView
            val nome: TextView;
            val descricao: TextView;
            init {
                imgLanche = view.findViewById(R.id.ivLancheMin)
                nome = view.findViewById(R.id.textViewNomeLanche);
                descricao = view.findViewById(R.id.textViewDescricaoLanche);
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.list_lanche_item, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            Glide.with(viewHolder.imgLanche.context).load(dataSet[position].strFotoLanche).into(viewHolder.imgLanche)
            viewHolder.nome.text = dataSet[position].nome;
            viewHolder.descricao.text = dataSet[position].descricao;
        }

        override fun getItemCount() = dataSet.size
    }
}

