package br.com.fiap.isgood.fragments.tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.isgood.R
import br.com.fiap.isgood.activities.ProdutosRestauranteActivity
import br.com.fiap.isgood.adapters.ListRestauranteAdapter
import br.com.fiap.isgood.model.dao.RestauranteDAO

class RestauranteFragment: Fragment () {

    lateinit  var recyclerView: RecyclerView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurante,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewRestaurantes);
        configureRecyclerView();
    }

    private fun configureRecyclerView() {
        recyclerView.setLayoutManager(LinearLayoutManager(getActivity()))
        val listRestaurantes = RestauranteDAO.getSampleData()

        val adapter = ListRestauranteAdapter(listRestaurantes, ListRestauranteAdapter.OnClickListener{
            val intentRestaurante = Intent(activity, ProdutosRestauranteActivity::class.java)
            intentRestaurante.putExtra("idRestaurante", it.id)
            startActivity(intentRestaurante)
        });
        recyclerView.adapter = adapter;

    }

    fun filterRestaurantes (text:String) {
        var adapter:ListRestauranteAdapter ;
        adapter= recyclerView.adapter as ListRestauranteAdapter
        adapter.filter.filter(text);
    }
}