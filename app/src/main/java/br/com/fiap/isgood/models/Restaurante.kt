package br.com.fiap.isgood.models

import com.google.android.gms.maps.model.LatLng

data class Restaurante(
    var id: Int,
    val nome: String,
    val apresentacao:String,
    val endereco: String,
    val rating : Float,
    val latitude: Double,
    val longitude: Double,
    val strLogoRestaurante : String,
) {
    fun getLatLng(): LatLng {
        if (latitude.equals(null) or longitude.equals(null))
            throw NullPointerException("Latitude ou longitude não declaradas.")
        return LatLng(latitude, longitude)

    }

    companion object {

        fun getSampleArray(): ArrayList<Restaurante> {
            val ret = ArrayList<Restaurante>()
            ret.add(Restaurante(
                1,
                "Rabodigalo",
                "contemporâneo,\n" +
                        "autoral,\n" +
                        "repleto de bons momentos.\n" +
                        "\uD83D\uDCCD S.Mônica\n" +
                        "\uD83D\uDCCDCampeche\n",
                "R. Clodorico Moreira, 23 - Santa Monica, \nFlorianópolis - SC, 88035-012",
                4F,
                -27.59066677191646,
                -48.51348197402769,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnzs1Ay-Zt6Zn7gAW3wSiNO6_gjsf-pBabbg&usqp=CAU"
            ))
            ret.add(
                Restaurante(
                    2,
                    "Mr. Hoppy - Florianópolis - Santa Mônica",
                    "A REDE QUE MAIS VENDE\n" +
                            "BURGER E CHOPE ARTESANAL\n" +
                            "NO BRASIL!",
                    "Av. Me. Benvenuta, 1037 - Santa Monica, \nFlorianópolis - SC, 88035-000",
                    3F,
                    -27.59047354972215,
                    -48.51134759819293,
                    "https://mrhoppy.com.br/wp-content/uploads/2021/03/logo.png"
                )
            )

            ret.add(
                Restaurante(
                    3,
                    "Afonso Burger Bar - Santa Mônica",
                    "HAMBURGUERIA\n" +
                            "Funcionamos de segunda a sábado 07:00 às 23:00\n" +
                            "88 996602244 \uD83D\uDCF2. \uD83D\uDCB3ACEITAMOS CARTÃO DE CREDITO E PIX!!\n",
                    "Av. Me. Benvenuta, 1074 - Santa Monica,\nFlorianópolis - SC, 88035-000",
                    5F,
                    -27.590681084689262,
                    -48.51080929289238,
                    "https://assets.untappd.com/venuelogos/venue_4943833_1ca95ec1_bg_176.png?v=1"
                )
            )
            return ret
        }
        fun getById(idRestaurante: Int): Restaurante {
            for (restaurante in getSampleArray())
                if (restaurante.id.equals(idRestaurante))
                    return restaurante
            throw Exception("Não foi encontrado restaurante com o ID $idRestaurante")
        }

    }
}