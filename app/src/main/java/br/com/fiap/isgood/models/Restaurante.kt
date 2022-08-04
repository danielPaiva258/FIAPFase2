package br.com.fiap.isgood.models

import com.google.android.gms.maps.model.LatLng

data class Restaurante(
    var id: Int,
    val nome: String,
    val endereco: String,
    val rating : Int,
    val latitude: Double,
    val longitude: Double,
) {
    fun getLatLng(): LatLng {
        if (latitude.equals(null) or longitude.equals(null))
            throw NullPointerException("Latitude ou longitude não declaradas.")
        return LatLng(latitude, longitude)

    }

    companion object {
        public fun getExample(id: Int): Restaurante {
            when (id) {
                1 ->
                    return Restaurante(
                        1,
                        "Rabodigalo",
                        "R. Clodorico Moreira, 23 - Santa Monica, \nFlorianópolis - SC, 88035-012",
                        4,
                        -27.59066677191646,
                        -48.51348197402769
                    )
                2 ->
                    return Restaurante(
                        2,
                        "Mr. Hoppy - Florianópolis - Santa Mônica",
                        "Av. Me. Benvenuta, 1037 - Santa Monica, \nFlorianópolis - SC, 88035-000",
                        3,
                        -27.59047354972215,
                        -48.51134759819293
                    )
                else ->
                    return Restaurante(
                        3,
                        "Afonso Burger Bar - Santa Mônica",
                        "Av. Me. Benvenuta, 1074 - Santa Monica,\nFlorianópolis - SC, 88035-000",
                        5,
                        -27.590681084689262,
                        -48.51080929289238
                    )
            }
        }
    }
}