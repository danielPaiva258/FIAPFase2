package br.com.fiap.isgood.models

open class Usuario(
    val id:Int,
    var name : String,
    var email : String,
    var cep : String
) {
    private var password : String? = null

    public fun setPassword(password : String) : Usuario{
        this.password = password
        return this
    }

    private fun autenthicate(pEmail : String, pPassword : String) : Boolean{
        if (email.equals(pEmail) and password.equals(pPassword))
            return true
        return false
    }
    companion object{
        fun getAutenticatedUser(email : String, password: String) : Usuario{
            for (usuario in Usuario.getSampleArray()){
                if (usuario.autenthicate(email, password))
                    return usuario
            }
            throw SecurityException("Usuário não autenticado")
        }

        fun getSampleArray() : ArrayList<Usuario>{
            var ret = ArrayList<Usuario>()
            ret.add(Usuario(
                1,
                "Willian Andrade",
                "williandrade@gmail.com",
                "88034460"
            ).setPassword("teste123"))
            ret.add(Usuario(
                2,
                "Usuario Teste",
                "teste@teste.com.br",
                "88034900"
            ).setPassword("12345"))
            return ret
        }

        fun getById(idUsuario: Int): Usuario {
            for (usuario in getSampleArray()){
                if (usuario.id.equals(idUsuario))
                    return usuario
            }
            throw SecurityException("Usuário não encontrado pelo ID")
        }
    }
}