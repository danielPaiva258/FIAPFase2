package br.com.fiap.isgood.models

class UsuarioAvaliador(
    id: String,
    name: String,
    email: String,
    cep: String
) : Usuario(
    id,
    name,
    email,
    cep
) {
    companion object {
        fun getSampleArray(): ArrayList<UsuarioAvaliador> {
            val arrUsr = Usuario.getSampleArray()
            val ret = ArrayList<UsuarioAvaliador>()

            for (usr in arrUsr) {
                ret.add(
                    UsuarioAvaliador(
                        usr.id,
                        usr.name,
                        usr.email,
                        usr.cep
                    )
                )
            }

            return ret
        }
    }
}