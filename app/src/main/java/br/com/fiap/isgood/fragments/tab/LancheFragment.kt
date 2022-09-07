package br.com.fiap.isgood.fragments.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.isgood.R
import br.com.fiap.isgood.model.Lanche
import br.com.fiap.isgood.model.dao.LancheDAO
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

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

        val listLanches = LancheDAO.getSampleData()

        val adapter = ListLancheAdapter(listLanches);
        recyclerView.adapter = adapter;
    }

    fun filterLanches (text:String) {
        var adapter: ListLancheAdapter;
        adapter = recyclerView.adapter as ListLancheAdapter;
        adapter.filter.filter(text);
    }

    class ListLancheAdapter (private val lanches: ArrayList<Lanche>) :
        RecyclerView.Adapter<ListLancheAdapter.ViewHolder>(), Filterable {

        private var lanchesFullList: ArrayList<Lanche> = ArrayList();

        init {
            lanchesFullList.addAll(lanches);
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imgLanche: ImageView
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
            Glide.with(viewHolder.imgLanche.context).load(lanches[position].strFotoLanche)
                .into(viewHolder.imgLanche)
            viewHolder.nome.text = lanches[position].nome;
            viewHolder.descricao.text = lanches[position].descricao;
        }

        override fun getItemCount() = lanches.size

        override fun getFilter(): Filter {
            return lanchesFilter;
        }

        private val lanchesFilter: Filter = object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredList: MutableList<Lanche> = java.util.ArrayList()
                if (constraint == null || constraint.length == 0) {
                    filteredList.addAll(lanchesFullList)
                } else {
                    val filterPattern =
                        constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                    for (item in lanchesFullList) {
                        if (item.nome.lowercase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                lanches.clear()
                lanches.addAll(results.values as List<Lanche>)
                notifyDataSetChanged()
            }
        }
    }
}

