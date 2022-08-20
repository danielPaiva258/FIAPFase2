package br.com.fiap.isgood.models

import android.content.res.Resources
import br.com.fiap.isgood.R
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
    val socialLinksArrayList: ArrayList<SocialLinks>,
) {
    data class SocialLinks(
        val kind : Int,
        val toastText : String,
        val publicUri : String,
    ) {
        companion object{
            val KIND_FACEBOOK = R.drawable.img_vector_logo_facebook
            val KIND_INSTAGRAM = R.drawable.img_vector_logo_instagram_gray
            val KIND_YOUTUBE = R.drawable.img_vector_logo_youtube
            val KIND_WHATSAPP = R.drawable.img_vector_logo_whatsapp
            val KIND_LINKTREE = R.drawable.img_vector_logo_linktree
            val KIND_SITE = R.drawable.img_vector_logo_site
            val KIND_IFOOD = R.drawable.img_vector_logo_ifood

            fun getAppString(kind : Int) : String{
                when (kind) {
                    KIND_WHATSAPP ->
                        return "com.whatsapp"
                    KIND_FACEBOOK ->
                        return "com.facebook"
                    else ->
                        return "com.google.chrome"
                }
            }
        }
    }
    fun getLatLng(): LatLng {
        if (latitude.equals(null) or longitude.equals(null))
            throw NullPointerException("Latitude ou longitude não declaradas.")
        return LatLng(latitude, longitude)

    }

    companion object {

        fun getSampleArray(): ArrayList<Restaurante> {
            val ret = ArrayList<Restaurante>()
            var sl1 = ArrayList<SocialLinks>()
            sl1.add(SocialLinks(SocialLinks.KIND_FACEBOOK, "FB Rabodigalo", "https://www.facebook.com/rabodigalo/"))
            sl1.add(SocialLinks(SocialLinks.KIND_INSTAGRAM, "Instagram Rabodigalo", "https://www.instagram.com/rabodigalo/"))
            sl1.add(SocialLinks(SocialLinks.KIND_LINKTREE, "LinkTree Rabodigalo", "https://linktr.ee/rabodigalo"))
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
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnzs1Ay-Zt6Zn7gAW3wSiNO6_gjsf-pBabbg&usqp=CAU",
                sl1))
            var sl2 = ArrayList<SocialLinks>()
            sl2.add(SocialLinks(SocialLinks.KIND_INSTAGRAM, "Instagram Mr. Hoppy", "https://www.instagram.com/mrhoppysantamonica/"))
            sl2.add(SocialLinks(SocialLinks.KIND_FACEBOOK, "FB Mr. Hoppy", "https://www.facebook.com/mrhoppysantamonica"))
            sl2.add(SocialLinks(SocialLinks.KIND_SITE, "House Mr. Hoppy", "https://mrhoppy.com.br/"))
            sl2.add(SocialLinks(SocialLinks.KIND_IFOOD, "Pedir Mr. Hoppy no iFood", "https://www.ifood.com.br/delivery/florianopolis-sc/mr-hoppy-florianopolis-santa-monica/58e9bf2f-f8f8-4b91-a512-a339377a2654"))
            sl2.add(SocialLinks(SocialLinks.KIND_WHATSAPP, "Chama no whats", "https://api.whatsapp.com/send?phone=5511913691939"))
            sl2.add(SocialLinks(SocialLinks.KIND_YOUTUBE, "Fake Youtube", "https://www.youtube.com/channel/UCNKNGIeuFmkugKg5Nen6KCw"))
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
                    "https://mrhoppy.com.br/wp-content/uploads/2021/03/logo.png",
                    sl2
                )
            )

            var sl3 = ArrayList<SocialLinks>()
            sl3.add(SocialLinks(SocialLinks.KIND_FACEBOOK, "Face do Afonso", "https://www.facebook.com/afonsoburger/"))
            sl3.add(SocialLinks(SocialLinks.KIND_INSTAGRAM, "Segue o Afonso no Insta", "https://www.instagram.com/afonso_burguer/"))

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
                    "https://assets.untappd.com/venuelogos/venue_4943833_1ca95ec1_bg_176.png?v=1",
                    sl3
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