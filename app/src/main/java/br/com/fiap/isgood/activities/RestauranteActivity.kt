package br.com.fiap.isgood.activities

import android.os.Bundle
import br.com.fiap.isgood.R
import br.com.fiap.isgood.models.Restaurante
import kotlinx.android.synthetic.main.activity_restaurante.*

class RestauranteActivity() : BaseDrawerActivity() {
    lateinit var restaurante: Restaurante

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante)
        var idRestaurante = intent.getIntExtra("idRestaurante", 99)
        restaurante = Restaurante.getExample(idRestaurante)
        tvNomeLoja.text = restaurante.nome
        tvEndereco.text = restaurante.endereco


    }
}