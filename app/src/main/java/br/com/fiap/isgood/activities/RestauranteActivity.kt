package br.com.fiap.isgood.activities

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ConcatAdapter
import br.com.fiap.isgood.R
import br.com.fiap.isgood.adapters.ListRestauranteSocialMidiaAdapter
import br.com.fiap.isgood.databinding.ActivityRestauranteBinding
import br.com.fiap.isgood.models.Restaurante
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_restaurante.*
import kotlin.math.log

class RestauranteActivity() : BaseDrawerActivity(), OnMapReadyCallback {
    lateinit var restaurante: Restaurante
    lateinit var mapRestaurante: GoogleMap
    private lateinit var binding: ActivityRestauranteBinding

    private lateinit var socialMidiaAdapter: ListRestauranteSocialMidiaAdapter

    val logId = ">>> RestauranteActivity <<<<"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestauranteBinding.inflate(layoutInflater)
        setOriginalContentView(binding.root)

        Log.i(logId, "Mostrando restaurante")
        var idRestaurante = intent.getIntExtra("idRestaurante", 99)
        restaurante = Restaurante.getById(idRestaurante)
        tvNomeLoja.text = restaurante.nome
        tvApresentacao.text = restaurante.apresentacao
        ratingBarRestaurante.rating = restaurante.rating
        Glide.with( this).load(restaurante.strLogoRestaurante).into(ivRestauranteTop)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapRestaurante) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initRecyclerView()

    }

    private fun initRecyclerView(){
        Log.i(logId, "Iniciando a lista de mídia social do restaurante. Total de mídias: ${restaurante.socialLinksArrayList.size}")
        socialMidiaAdapter = ListRestauranteSocialMidiaAdapter()
        binding.rvRestauranteSocialMidiaList.adapter = ConcatAdapter(socialMidiaAdapter)
        socialMidiaAdapter.submitList(restaurante.socialLinksArrayList)
        Log.i(logId, "Foram listados ${binding.rvRestauranteSocialMidiaList.layoutManager?.itemCount}")
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapRestaurante =  googleMap

        val localRestaurante = LatLng(restaurante.latitude, restaurante.longitude)
        val marker = MarkerOptions()
            .position(localRestaurante)
            .title(restaurante.nome)
            .snippet(restaurante.endereco)
            .icon(BitmapDescriptorFactory.defaultMarker())

        mapRestaurante.addMarker(marker)?.showInfoWindow()


        mapRestaurante.moveCamera(CameraUpdateFactory.newLatLngZoom(localRestaurante, 12.5F))

        mapRestaurante.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(p0: Marker): View? {
                return null
            }

            override fun getInfoContents(marker: Marker): View? {
                val title = TextView(applicationContext)
                title.setTextColor(Color.BLACK)
                title.setTypeface(null, Typeface.BOLD)
                title.text = restaurante.nome

                val snippet =TextView(applicationContext)
                snippet.setTextColor(Color.GRAY)
                snippet.text = restaurante.endereco

                val info =LinearLayout(applicationContext)
                info.orientation = LinearLayout.VERTICAL
                info.addView(title)
                info.addView(snippet)

                return info
            }
        })


    }
}