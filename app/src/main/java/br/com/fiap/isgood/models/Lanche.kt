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
            when (id) {
                1 ->
                    return Lanche(
                        1,
                        "Hamburguer Big 250g",
                        "Pão brioche com hamburguer 250g, salada, catchup e maionese.",
                        3,
                        Restaurante.getExample(1)
                    )
                2 ->
                    return Lanche(
                        2,
                        "Hamburguer + Fritas",
                        "Hamburguer do dia com batata-frita",
                        4,
                        Restaurante.getExample(2)
                    )
                else ->
                    return Lanche(
                        3,
                        "Hambuguer Monstro",
                        "Hamburguer big de 350g, no pão brioche, salada e molho especial",
                        5,
                        Restaurante.getExample(3)

                    )

            }
        }
    }
}