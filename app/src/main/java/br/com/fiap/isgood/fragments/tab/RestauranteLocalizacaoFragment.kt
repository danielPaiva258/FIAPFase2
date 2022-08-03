package br.com.fiap.isgood.fragments.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fiap.isgood.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RestauranteLocalizacaoFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

    }

    private fun setLocation() {
        Toast.makeText(context, "Localização da CELESC será aplicada ao mapa.", Toast.LENGTH_LONG).show()

        var myTitle = "Celesc - ADMC"
        var mySnipet = "Av. Itamarati, 160\nFlorianópolis - SC\nCEP 88034-900"
        var myLocation = LatLng(-27.588584105380217, -48.49859093131555)

        map.addMarker(MarkerOptions().position(myLocation).title(myTitle).snippet(mySnipet)
            .icon(BitmapDescriptorFactory.defaultMarker()))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12.5F))

        Toast.makeText(context, "Localização da CELESC aplicada ao mapa.", Toast.LENGTH_LONG).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setLocation()
        return inflater.inflate(R.layout.fragment_restaurante_localizacao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        setLocation()
    }
}