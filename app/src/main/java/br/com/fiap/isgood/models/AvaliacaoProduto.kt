package br.com.fiap.isgood.models

data class AvaliacaoProduto (
    val id:Int,
    val usuarioAvaliador: UsuarioAvaliador,
    val lanche:Lanche,
    val comentario:String,
    val nota:Float
)  {

    companion object {
        fun getSampleArray() : ArrayList<AvaliacaoProduto>{
            val avaliadores = UsuarioAvaliador.getSampleArray()
            val lanches = Lanche.getSampleArray()
            val ret = ArrayList<AvaliacaoProduto>()

            ret.add(
                AvaliacaoProduto(
                    1,
                    avaliadores[0],
                    lanches[0],
                    "Lanche muito bom",
                    4.5F
            ))

            ret.add(
                AvaliacaoProduto(2,
                avaliadores[1],
                lanches[0],
                "Estava bom, mas não tanto. Já experimentei melhores.",
                2.5F)
            )

            ret.add(
                AvaliacaoProduto(3,
                avaliadores[1],
                lanches[1],
                "Péssimo! Nunca comi um lanche tão ruim quanto esse!!!",
                0F)
            )

            return ret
        }

    }
}