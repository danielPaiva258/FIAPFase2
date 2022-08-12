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
import br.com.fiap.isgood.models.Restaurante
import br.com.fiap.isgood.models.Usuario

class RestauranteFragment: Fragment (){

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
        /*
        val res1 = Restaurante.getById (1) //nome="rest1", endereco = "end1")
        val res2 = Restaurante.getById (2) //nome="rest2", endereco = "end2")
        val res3 = Restaurante.getById (3) //nome="rest3", endereco = "end3")

        val listRestaurantes = ArrayList<Restaurante>();
        listRestaurantes.add(res1);
        listRestaurantes.add(res2);
        listRestaurantes.add(res3)
        */
        val listRestaurantes = Restaurante.getSampleArray()

        val adapter = ListRestauranteAdapter(listRestaurantes, ListRestauranteAdapter.OnClickListener{
            val intentRestaurante = Intent(activity, ProdutosRestauranteActivity::class.java)
            intentRestaurante.putExtra("idRestaurante", it.id)
            intentRestaurante.putExtra("idUsuario",
                activity?.intent?.let { it1 -> Usuario.getById(it1.getIntExtra("idUsuario", 0)).id });
            startActivity(intentRestaurante)
        });
        recyclerView.adapter = adapter;
    }
}