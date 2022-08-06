package br.com.fiap.isgood.activities

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import br.com.fiap.isgood.R
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

class RestauranteActivity() : BaseDrawerActivity(), OnMapReadyCallback {
    lateinit var restaurante: Restaurante
    lateinit var mapRestaurante: GoogleMap
    lateinit var infoMarker : Marker

    val logId = ">>> RestauranteActivity <<<<"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante)
        Log.i(logId, "Mostrando restaurante")
        var idRestaurante = intent.getIntExtra("idRestaurante", 99)
        restaurante = Restaurante.getById(idRestaurante)
        tvNomeLoja.text = restaurante.nome
        tvApresentacao.text = restaurante.apresentacao
        ratingBarRestaurante.rating = restaurante.rating
        Glide.with( this).load(restaurante.strLogoRestaurante).into(ivRestauranteTop)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapRestaurante) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.e("GOOGLE MAP", "Entrou aqui...")
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