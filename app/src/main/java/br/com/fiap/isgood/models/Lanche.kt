package br.com.fiap.isgood.models

import android.net.Uri

data class Lanche(
    val id: Int,
    val nome: String,
    val descricao: String,
    val strFotoLanche:String,
    val rating:Int,
    val restaurante: Restaurante
) {
    companion object {
        fun getExemple(id: Int): Lanche {
            val allLanches = getSampleArray()
            for (lanche in allLanches){
                if (lanche.id.equals(id))
                    return lanche
            }
            throw SecurityException("Lanche com id $id não foi encontrado.")
        }

        fun getSampleArray(): ArrayList<Lanche> {
            var ret = ArrayList<Lanche>()
            ret.add(Lanche(
                1,
                "Hamburguer Big 250g",
                "Pão brioche com hamburguer 250g, salada, catchup e maionese.",
                "https://img.itdg.com.br/tdg/images/recipes/000/161/709/342919/342919_original.jpg?mode=crop&width=710&height=400",
                3,
                Restaurante.getById(1)
            ))

            ret.add(Lanche(
                2,
                "Hamburguer + Fritas",
                "Hamburguer do dia com batata-frita",
                "https://i.pinimg.com/736x/d8/68/dc/d868dcc36646a649dc1898668711ef6b.jpg",
                4,
                Restaurante.getById(2)
            ))

            ret.add(Lanche(
                3,
                "Hambuguer Monstro",
                "Hamburguer big de 350g, no pão brioche, salada e molho especial",
                "https://guiachef.com.br/wp-content/uploads/2019/07/10-ex%C3%B3ticos-hamb%C3%BArgueres-do-mundo-Vegan.jpg",
                5,
                Restaurante.getById(3)
            ))

            return ret

        }
    }
}