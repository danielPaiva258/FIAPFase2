package br.com.fiap.isgood.utils

import br.com.fiap.isgood.model.Restaurante
import br.com.fiap.isgood.model.dao.RestauranteDAO
import br.com.fiap.isgood.model.dto.EmpresasDTO

class RestauranteUtils {
    companion object {
        fun convertEmpresasDtoTORestaurantes(empresaDTOlist: List<EmpresasDTO>): ArrayList<Restaurante> {
            var restaurantesListResult: ArrayList<Restaurante> = ArrayList<Restaurante>();
            if (empresaDTOlist.isNotEmpty()) {
                for (empresa in empresaDTOlist) {
                    val socialLinks: ArrayList<Restaurante.SocialLinks> =
                        ArrayList<Restaurante.SocialLinks>();

                    if (empresa.url_facebook != null) {
                        socialLinks.add(
                            Restaurante.SocialLinks(
                                Restaurante.SocialLinks.KIND_FACEBOOK,
                                "FB " + empresa.nome,
                                empresa.url_facebook
                            )
                        )
                    }

                    if (empresa.url_instagram != null) {
                        socialLinks.add(
                            Restaurante.SocialLinks(
                                Restaurante.SocialLinks.KIND_INSTAGRAM,
                                "Instagram " + empresa.nome,
                                empresa.url_instagram
                            )
                        )
                    }

                    if (empresa.url_twitter != null) {
                        socialLinks.add(
                            Restaurante.SocialLinks(
                                Restaurante.SocialLinks.KIND_SITE,
                                "TW " + empresa.nome,
                                empresa.url_twitter
                            )
                        )
                    }

                    val restaurante: Restaurante = Restaurante(
                        empresa.id.toString(),
                        empresa.nome,
                        empresa.apresentacao,
                        empresa.endereco + " " + empresa.cep,
                        empresa.rating.toFloat(),
                        empresa.latitude,
                        empresa.longitude,
                        empresa.logo,
                        socialLinks
                    );

                    restaurantesListResult.add(restaurante);
                }
            }

            return restaurantesListResult;
        }
    }
}