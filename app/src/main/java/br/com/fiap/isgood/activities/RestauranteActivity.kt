package br.com.fiap.isgood.activities

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import br.com.fiap.isgood.R
import br.com.fiap.isgood.models.Restaurante
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_restaurante.*

class RestauranteActivity() : BaseDrawerActivity() {
    lateinit var restaurante: Restaurante
    val logId = "RestauranteActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante)
        Log.i(logId, "Mostrando restaurante")
        var idRestaurante = intent.getIntExtra("idRestaurante", 99)
        restaurante = Restaurante.getById(idRestaurante)
        tvNomeLoja.text = restaurante.nome
        tvEndereco.text = restaurante.endereco
        Glide.with( this).load(restaurante.srcImageLogo).into(ivRestauranteTop)
    }
}