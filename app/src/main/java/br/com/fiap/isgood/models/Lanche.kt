package br.com.fiap.isgood.models

data class Lanche(
    val id: Int,
    val nome: String,
    val descricao: String,
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
                3,
                Restaurante.getById(1)
            ))

            ret.add(Lanche(
                2,
                "Hamburguer + Fritas",
                "Hamburguer do dia com batata-frita",
                4,
                Restaurante.getById(2)
            ))

            ret.add(Lanche(
                3,
                "Hambuguer Monstro",
                "Hamburguer big de 350g, no pão brioche, salada e molho especial",
                5,
                Restaurante.getById(3)
            ))

            return ret

        }
    }
}