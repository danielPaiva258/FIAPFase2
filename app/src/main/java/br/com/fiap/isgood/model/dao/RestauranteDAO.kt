package br.com.fiap.isgood.model.dao

import br.com.fiap.isgood.model.Restaurante

object RestauranteDAO : GenericDAO<Restaurante, String>() {
    override fun getById(objId: String): Restaurante {
        for (restaurante in getSampleData())
            if (restaurante.id.equals(objId))
                return restaurante
        throw Exception("Não foi encontrado restaurante com o ID $objId")
    }

    override fun getByFilter(filter: String?): ArrayList<Restaurante> {
        val searchResult = arrayListOf<Restaurante>()
        for (restaurante in getSampleData())
            if (restaurante.nome.contains(filter ?: ""))
                searchResult.add(restaurante)
        return searchResult
    }

    override fun update(obj: Restaurante): Restaurante {
        delete(getById(obj.id))
        return add(obj)
    }

    override fun getSampleData(): ArrayList<Restaurante> {
        defaultList.removeAll(defaultList)
        if (defaultList.size == 0) {
            val sl1 = ArrayList<Restaurante.SocialLinks>()
            sl1.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_FACEBOOK,
                    "FB Rabodigalo",
                    "https://www.facebook.com/rabodigalo/"
                )
            )
            sl1.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_INSTAGRAM,
                    "Instagram Rabodigalo",
                    "https://www.instagram.com/rabodigalo/"
                )
            )
            sl1.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_LINKTREE,
                    "LinkTree Rabodigalo",
                    "https://linktr.ee/rabodigalo"
                )
            )
            defaultList.add(
                Restaurante(
                    "1",
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
                    sl1
                )
            )
            val sl2 = ArrayList<Restaurante.SocialLinks>()
            sl2.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_INSTAGRAM,
                    "Instagram Mr. Hoppy",
                    "https://www.instagram.com/mrhoppysantamonica/"
                )
            )
            sl2.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_FACEBOOK,
                    "FB Mr. Hoppy",
                    "https://www.facebook.com/mrhoppysantamonica"
                )
            )
            sl2.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_SITE,
                    "House Mr. Hoppy",
                    "https://mrhoppy.com.br/"
                )
            )
            sl2.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_IFOOD,
                    "Pedir Mr. Hoppy no iFood",
                    "https://www.ifood.com.br/delivery/florianopolis-sc/mr-hoppy-florianopolis-santa-monica/58e9bf2f-f8f8-4b91-a512-a339377a2654"
                )
            )
            sl2.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_WHATSAPP,
                    "Chama no whats",
                    "https://api.whatsapp.com/send?phone=5511913691939"
                )
            )
            sl2.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_YOUTUBE,
                    "Fake Youtube",
                    "https://www.youtube.com/channel/UCNKNGIeuFmkugKg5Nen6KCw"
                )
            )
            defaultList.add(
                Restaurante(
                    "2",
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

            val sl3 = ArrayList<Restaurante.SocialLinks>()
            sl3.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_FACEBOOK,
                    "Face do Afonso",
                    "https://www.facebook.com/afonsoburger/"
                )
            )
            sl3.add(
                Restaurante.SocialLinks(
                    Restaurante.SocialLinks.KIND_INSTAGRAM,
                    "Segue o Afonso no Insta",
                    "https://www.instagram.com/afonso_burguer/"
                )
            )

            defaultList.add(
                Restaurante(
                    "3",
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
        }
        return defaultList
    }
}
